package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import z21.autotask.entities.Animal;
import z21.autotask.entities.Employee;
import z21.autotask.entities.Task;
import z21.autotask.service.DataService;
import z21.autotask.views.MainLayout;

import java.time.ZoneId;
import java.util.List;

@PageTitle("List of all tasks")
@Route(value = "/tasks", layout = MainLayout.class)
public class TasksListView extends VerticalLayout {
    private final DataService dataService;
    Grid<Task> grid = new Grid<>(Task.class, false);
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

        grid.addColumn(Task::getTaskId).setHeader("ID");
        grid.addColumn(Task -> Task.getType().getName()).setHeader("Type");
        grid.addColumn(Task::getDescription).setHeader("Name");
        grid.addColumn(Task::getDateStart).setHeader("Date of start");
        grid.addColumn(Task::getDeadline).setHeader("Deadline");
        grid.addColumn(Task::getDateEnd).setHeader("Date of end");
        grid.addColumn(Task::getPriority).setHeader("Priority");
        grid.addColumn(Task -> Task.getStatus().getDescription()).setHeader("Status");
        grid.addColumn(Task -> Task.getLocation().getName()).setHeader("Location");

        grid.addColumn(new ComponentRenderer<>(Task -> {
            VirtualList<Employee> list = new VirtualList<>();
            list.setItems(dataService.getAllEmployees());
            return list ;
            })).setHeader("Employees");

        
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

