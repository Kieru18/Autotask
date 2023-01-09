package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.Animal;
import z21.autotask.service.DataService;

import java.util.List;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("List of all animals")
@Route(value = "/animals", layout = MainLayout.class)
public class AnimalsListView extends VerticalLayout {
    private final DataService dataService;
    Grid<Animal> grid = new Grid<>(Animal.class);
    TextField filterText = new TextField();


    @Autowired
    public AnimalsListView(DataService dataService) {
        this.dataService = dataService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    @Transactional
    void configureGrid() {
        grid.addClassNames("animals-grid");
        grid.setSizeFull();

        List<Animal> listOfAnimals = dataService.getAllAnimals();

        grid.setItems(listOfAnimals);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addAnimalButton = new Button("Add animal");
        addAnimalButton.addClickListener(click ->{
            addAnimalButton.getUI().ifPresent(ui ->
                    ui.navigate("animalForm"));

            Notification.show("Switching tab to animal form.");
        });


        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAnimalButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}

