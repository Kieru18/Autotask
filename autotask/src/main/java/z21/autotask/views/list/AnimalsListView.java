package z21.autotask.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.Animal;
import z21.autotask.service.DataService;
import z21.autotask.views.MainLayout;
import z21.autotask.views.form.AnimalFormView;

import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("List of all animals")
@Route(value = "/animalsList", layout = MainLayout.class)
public class AnimalsListView extends VerticalLayout {
    private final DataService dataService;
    AnimalFilter animalFilter;
    Grid<Animal> grid = new Grid<>(Animal.class, false);

    @Autowired
    public AnimalsListView(DataService dataService) {
        this.dataService = dataService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(animalFilter), grid);
    }

    @Transactional
    void configureGrid() {
        grid.addClassNames("animals-grid");
        grid.setSizeFull();

        grid.addColumn(Animal::getAnimalId).setHeader("ID").setSortable(true);
        grid.addColumn(Animal::getName).setHeader("Name").setSortable(true);
        grid.addColumn(Animal -> Animal.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).setHeader("Date of birth").setSortable(true);
        grid.addColumn(Animal::getWeight).setHeader("Weight").setSortable(true);
        grid.addColumn(Animal -> Animal.getLocation().getName()).setHeader("Location").setSortable(true);
        grid.addColumn(Animal -> Animal.getSpecies().getName()).setHeader("Species").setSortable(true);

        List<Animal> listOfAnimals = dataService.getAllAnimals();
        GridListDataView<Animal> dataView = grid.setItems(listOfAnimals);
        animalFilter = new AnimalFilter(dataView);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private static Component createFilter(String labelText,
                                          Consumer<String> filterChangeConsumer) {
        TextField textField = new TextField();
        textField.setPlaceholder("Filter by name...");;
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");

        return layout;
    }
    private static class AnimalFilter {
        private final GridListDataView<Animal> dataView;
        private String name;

        public AnimalFilter(GridListDataView<Animal> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::test);
        }
        public void setlName(String name) {
            this.name = name;
            this.dataView.refreshAll();
        }
        public boolean test(Animal animal) {
            boolean matchesName = matches(animal.getName(), name);
            return matchesName;
        }
        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty()
                    || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }

    private HorizontalLayout getToolbar(AnimalFilter animalFilter) {
        Component filterText = createFilter("Filter by name...", animalFilter::setlName);

        Button addAnimalButton = new Button("Add animal");
        addAnimalButton.addClickListener(click ->{
            addAnimalButton.getUI().ifPresent(ui ->
                    ui.navigate(AnimalFormView.class));

            Notification.show("Switching tab to animal form.");
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                toolbar.add(addAnimalButton);
            }}

        toolbar.addClassName("toolbar");
        return toolbar;
    }
}

