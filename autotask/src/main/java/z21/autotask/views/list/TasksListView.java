package z21.autotask.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.dom.ElementFactory;
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
import java.util.function.Consumer;

import javax.annotation.security.RolesAllowed;

@RolesAllowed("ROLE_ADMIN")
@PageTitle("List of all tasks")
@Route(value = "/tasks", layout = MainLayout.class)
public class TasksListView extends VerticalLayout {
    private final DataService dataService;
    Grid<Task> grid = new Grid<>(Task.class, false);
    Dialog dialogEmployees = new Dialog();
    Dialog dialogAnimals = new Dialog();

    TaskFilter taskFilter;

    @Autowired
    public TasksListView(DataService dataService) {
        this.dataService = dataService;
        addClassName("list-view");
        setSizeFull();
        configureDialogs();
        configureGrid();

        add(getToolbar(taskFilter), grid);
    }

    @Transactional
    void configureGrid() {
        grid.addClassNames("tasks-grid");
        grid.setSizeFull();

        grid.addColumn(Task::getTaskId).setHeader("ID").setSortable(true);
        grid.addColumn(Task::getDescription).setHeader("Name").setSortable(true);
        grid.addColumn(Task::getDateStart).setHeader("Date of start").setSortable(true);
        grid.addColumn(Task::getDeadline).setHeader("Deadline").setSortable(true);
        grid.addColumn(Task::getDateEnd).setHeader("Date of end").setSortable(true);
        grid.addColumn(Task::getPriority).setHeader("Priority").setSortable(true);
        grid.addColumn(Task -> Task.getStatus().getDescription()).setHeader("Status").setSortable(true);
        grid.addColumn(Task -> Task.getLocation().getName()).setHeader("Location").setSortable(true);
        grid.addColumn(Task -> Task.getType().getName()).setHeader("Type").setSortable(true);

        grid.addColumn(new ComponentRenderer<>(task -> {
            Button buttonEmp = new Button("Show employees", e -> {
                updateEmployeeDialog(task);
                dialogEmployees.open();
                } );
            return buttonEmp;

            })).setHeader("Employees");

        grid.addColumn(new ComponentRenderer<>(task -> {
            Button buttonAnm = new Button("Show animals", e ->{
            updateAnimalDialog(task);
            dialogAnimals.open();
            } );
            return buttonAnm;
        })).setHeader("Animals");

        List<Task> listOfTasks = dataService.getAllTasks();
        GridListDataView<Task> dataView = grid.setItems(listOfTasks);
        taskFilter = new TaskFilter(dataView);

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar(TaskFilter taskFilter) {
        Component filterText = createFilter("Filter by description...", taskFilter::setlDescription);

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

    void configureDialogs() {
        dialogEmployees.setHeaderTitle("List of assigned employees");
        dialogAnimals.setHeaderTitle("List of assigned animals");

        dialogEmployees.setMinWidth("680px");
        dialogAnimals.setMinWidth("880px");

        VerticalLayout dialogEmployeeLayout = getEmployeesCards(dataService.getAllEmployees());
        VerticalLayout dialogAnimalLayout = getAnimalsList(dataService.getAllAnimals());

        dialogEmployees.add(dialogEmployeeLayout);
        dialogAnimals.add(dialogAnimalLayout);

        dialogEmployees.getFooter().add(new Button("Close", e -> dialogEmployees.close()));
        dialogAnimals.getFooter().add(new Button("Close", e -> dialogAnimals.close()));
    }

    private void updateEmployeeDialog(Task task) {
        dialogEmployees.removeAll();
        List<Employee> employees = dataService.getEmployeesByTask(task);
        if (employees.get(0) == null)
            dialogEmployees.setHeaderTitle("There are no assigned employees");
        else{
            dialogEmployees.setHeaderTitle("List of assigned employees");
            dialogEmployees.add(getEmployeesCards(employees));}
    }
    private void updateAnimalDialog(Task task) {
        dialogAnimals.removeAll();
        List<Animal> animals = dataService.getAnimalsByTask(task);
        if (animals.isEmpty() || animals.get(0) == null)
            dialogAnimals.setHeaderTitle("There are no assigned animals");
        else{
            dialogAnimals.setHeaderTitle("List of assigned animals");
            dialogAnimals.add(getAnimalsList(animals));}
    }
    private VerticalLayout getAnimalsList(List<Animal> animals) {
        VerticalLayout verticalLayoutAnimals = new VerticalLayout();
        Grid<Animal> grid = new Grid<>(Animal.class, false);

        grid.addColumn(Animal::getAnimalId).setHeader("ID");
        grid.addColumn(Animal::getName).setHeader("Name");
        grid.addColumn(Animal -> Animal.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).setHeader("Date of birth");
        grid.addColumn(Animal::getWeight).setHeader("Weight");
        grid.addColumn(Animal -> Animal.getLocation().getName()).setHeader("Location");
        grid.addColumn(Animal -> Animal.getSpecies().getName()).setHeader("Species");

        grid.setItems(animals);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        verticalLayoutAnimals.add(grid);
        return verticalLayoutAnimals;
    }

    private VerticalLayout getEmployeesCards(List<Employee> employees)
    {
        VerticalLayout verticalLayoutEmployees = new VerticalLayout();
        for(Employee employee : employees)
        {
            verticalLayoutEmployees.add(createEmployeeCardComponent(employee));
        }
        return verticalLayoutEmployees;
    }
    private HorizontalLayout createEmployeeCardComponent(Employee person)
    {
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setMargin(true);

        Avatar avatar = new Avatar(person.getFullName());
        avatar.setImage(person.getPictureRoute());

        avatar.setHeight("64px");
        avatar.setWidth("64px");

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setPadding(false);
        infoLayout.getElement().appendChild(
                ElementFactory.createStrong(person.getFullName()));
        infoLayout.add(new Div(new Text(person.getProfession())));


        VerticalLayout contactLayout = new VerticalLayout();
        contactLayout.setSpacing(false);
        contactLayout.setPadding(false);
        if(person.getUser() != null) {
            contactLayout.add(new Div(new Text(person.getEmail())));
            infoLayout.add(new Details("Contact information", contactLayout));
        }


        cardLayout.add(avatar, infoLayout);
        return cardLayout;
    }

    private static Component createFilter(String labelText,
                                          Consumer<String> filterChangeConsumer) {
        TextField textField = new TextField();
        textField.setPlaceholder(labelText);
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
    private static class TaskFilter {
        private final GridListDataView<Task> dataView;
        private String description;

        public TaskFilter(GridListDataView<Task> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::test);
        }
        public void setlDescription(String description) {
            this.description = description;
            this.dataView.refreshAll();
        }
        public boolean test(Task task) {
            boolean matchesDescription = matches(task.getDescription(), description);
            return matchesDescription;
        }
        private boolean matches(String value, String searchTerm) {
            return searchTerm == null || searchTerm.isEmpty()
                    || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }

}

