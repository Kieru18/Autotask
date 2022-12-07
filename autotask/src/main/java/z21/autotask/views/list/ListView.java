package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import z21.autotask.Animal;
import z21.autotask.DataService;

import java.util.List;

@PageTitle("List of all animals")
@Route(value = "/animals")
public class ListView extends VerticalLayout {
    private final DataService dataService;
    Grid<Animal> grid = new Grid<>(Animal.class);
    TextField filterText = new TextField();


    @Autowired
    public ListView(DataService dataService) {
        this.dataService = dataService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("animals-grid");
        grid.setSizeFull();

        List<Animal> listOfAnimals = dataService.getAll();
        grid.setItems(listOfAnimals);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addAnimalButton = new Button("Add animal");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAnimalButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}

