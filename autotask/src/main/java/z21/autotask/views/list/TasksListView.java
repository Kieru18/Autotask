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

import z21.autotask.entities.Task;
import z21.autotask.service.DataService;

import java.util.List;

import javax.annotation.security.PermitAll;

@PermitAll
@PageTitle("List of all tasks")
@Route(value = "/tasks", layout = MainLayout.class)
public class TasksListView extends VerticalLayout {
    private final DataService dataService;
    Grid<Task> grid = new Grid<>(Task.class);
    TextField filterText = new TextField();


    @Autowired
    public TasksListView(DataService dataService) {
        this.dataService = dataService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    @Transactional
    void configureGrid() {
        grid.addClassNames("tasks-grid");
        grid.setSizeFull();

        List<Task> listOfTasks = dataService.getAllTasks();

        grid.setItems(listOfTasks);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addTaskButton = new Button("Add task");

        addTaskButton.addClickListener(click ->{
                addTaskButton.getUI().ifPresent(ui ->
                            ui.navigate("taskForm"));
            Notification.show("Switching to task form.");
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addTaskButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}

