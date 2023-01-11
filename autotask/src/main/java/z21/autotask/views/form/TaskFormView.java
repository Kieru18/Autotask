package z21.autotask.views.form;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H1;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.util.Date;
import java.util.Set;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.entities.Animal;
import z21.autotask.entities.Employee;
import z21.autotask.entities.Location;
import z21.autotask.entities.TaskType;
import z21.autotask.entities.TaskStatus;
import z21.autotask.service.DataService;
import z21.autotask.views.MainLayout;

@PermitAll
@Route(value = "/TaskForm", layout = MainLayout.class)
public class TaskFormView extends VerticalLayout {
    private final DataService dataService;
    private final MultiSelectComboBox<Employee> MSCBwho;
    private final MultiSelectComboBox<Animal> MSCBanimals;
    private final ComboBox<TaskType> CBtaskGroup;
    private final ComboBox<Location> CBwhere;
    private final DateTimePicker DTPwhen;
    private final TextArea TADescription;
    HorizontalLayout buttons;


    @Autowired
    public TaskFormView(DataService dataService) {
        this.dataService = dataService;

        FormLayout taskForm = new FormLayout();

        MSCBwho = prepareWhoMultiSelectComboBox();
        MSCBanimals = prepareAnimalsMultiSelectComboBox();
        CBtaskGroup = prepareTaskGroupComboBox();
        CBwhere = prepareWhereComboBox();
        DTPwhen = prepareWhenDateTimePicker();
        TADescription = prepareDescriptionTextArea();
        buttons = prepareButtons();

        taskForm.add(MSCBwho, CBtaskGroup,MSCBanimals, CBwhere, TADescription, DTPwhen, buttons);

        H1 title = new H1("Task Generator");
        add(title, taskForm);
        setWidth("auto");
        setMargin(true);
        setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        setAlignItems(FlexComponent.Alignment.STRETCH);
    }

    private MultiSelectComboBox<Employee> prepareWhoMultiSelectComboBox(){
        MultiSelectComboBox<Employee> MSCBwho = new MultiSelectComboBox<>("Employees");
        MSCBwho.setItems(dataService.getAllEmployees());
        MSCBwho.setItemLabelGenerator(Employee::getFullName);
        return MSCBwho;
    }

    private MultiSelectComboBox<Animal> prepareAnimalsMultiSelectComboBox(){

        MultiSelectComboBox<Animal> MSCBanimals = new MultiSelectComboBox<>("Animals");
        MSCBanimals.setItems(dataService.getAllAnimals());
        MSCBanimals.setItemLabelGenerator(Animal::getName);
        return MSCBanimals;
    }

    private ComboBox<TaskType> prepareTaskGroupComboBox(){
        ComboBox<TaskType> CBtaskGroup = new ComboBox<>("Task Group");
        CBtaskGroup.setItems(dataService.getAllTaskTypes());
        CBtaskGroup.setItemLabelGenerator(TaskType::getName);
        return CBtaskGroup;
    }

    private ComboBox<Location> prepareWhereComboBox(){
        ComboBox<Location> CBwhere = new ComboBox<>("Location");
        CBwhere.setItems(dataService.getAllLocations());
        CBwhere.setItemLabelGenerator(Location::getName);
        return CBwhere;
    }

    private DateTimePicker prepareWhenDateTimePicker() {
        DateTimePicker DTPwhen = new DateTimePicker();
        DTPwhen.setLabel("Deadline");
        DTPwhen.setStep(Duration.ofMinutes(30));
        DTPwhen.setValue(LocalDateTime.now());
        return DTPwhen;
    }

    private TextArea prepareDescriptionTextArea() {
        int charLimit = 1000;
        TextArea TADescription = new TextArea("Details");
        TADescription.setWidthFull();
        TADescription.setMaxLength(charLimit);
        TADescription.setValueChangeMode(ValueChangeMode.EAGER);
        TADescription.addValueChangeListener(e -> e.getSource().setHelperText(e.getValue().length() + "/" + charLimit));
        return TADescription;
    }

    private HorizontalLayout prepareButtons() {
        Button BSubmit = new Button("Submit");
        Button BClear = new Button("Clear");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(BSubmit, BClear);

        BSubmit.addClickListener(click -> {
            Set<Animal> selectedAnimals = MSCBanimals.getSelectedItems();
            Set<Employee> selectedEmployees = MSCBwho.getSelectedItems();
            Location selectedLocation = CBwhere.getValue();
            TaskType selectedTaskGroup = CBtaskGroup.getValue();
            Date startOfTaskTime = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());       
            Date selectedDeadline = Date.from(DTPwhen.getValue().atZone(ZoneId.systemDefault()).toInstant());
            String description = TADescription.getValue();

            TaskStatus newStatus = dataService.getAwaiting().get(0);

            Integer selectedPriority = null;
            Integer priority = selectedTaskGroup.getBasePriority();
            if (selectedPriority != null) {
                priority = selectedPriority;
            }

            dataService.addTask(description, startOfTaskTime, null, selectedDeadline, priority, selectedLocation.getLocationId(), newStatus.getStatusId(), selectedTaskGroup.getTypeId(), 
                                selectedEmployees, selectedAnimals);

            Notification.show("Task added succesfully!");
        });

        BClear.addClickListener(click -> {
            MSCBanimals.clear();
            MSCBwho.clear();
            CBwhere.clear();
            CBtaskGroup.clear();
            DTPwhen.setValue(LocalDateTime.now());
            TADescription.clear();

            Notification.show("All info cleared.");
        });
        return buttons;
    }

}
