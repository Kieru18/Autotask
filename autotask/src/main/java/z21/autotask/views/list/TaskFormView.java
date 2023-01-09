package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H1;
import java.time.LocalDateTime;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.entities.Animal;
import z21.autotask.entities.Employee;
import z21.autotask.entities.Location;
import z21.autotask.entities.TaskType;
import z21.autotask.service.DataService;

@Route(value = "/taskForm", layout = MainLayout.class)
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
        setMargin(true);
    }

    private MultiSelectComboBox<Employee> prepareWhoMultiSelectComboBox(){
        MultiSelectComboBox<Employee> MSCBwho = new MultiSelectComboBox<>("Who");
        MSCBwho.setItems(dataService.getAllEmployees());
        return MSCBwho;
    }

    private MultiSelectComboBox<Animal> prepareAnimalsMultiSelectComboBox(){

        MultiSelectComboBox<Animal> MSCBanimals = new MultiSelectComboBox<>("Animals");
        MSCBanimals.setItems(dataService.getAllAnimals());

//        MSCBanimals.addSelectionListener(e-> {
//            Optional<Animal> recentlySelectedValue = e.getFirstSelectedItem();
//
//            // TODO get associated location of selected animal and update this function
//            if(recentlySelectedValue.isPresent())
//            {
//                CBwhere.setValue(recentlySelectedValue.get().getLocation());
//            }
//        });

        return MSCBanimals;
    }

    private ComboBox<TaskType> prepareTaskGroupComboBox(){
        ComboBox<TaskType> CBtaskGroup = new ComboBox<>("Task Group");
        CBtaskGroup.setItems(dataService.getAllTaskTypes());
        return CBtaskGroup;
    }

    private ComboBox<Location> prepareWhereComboBox(){
        ComboBox<Location> CBwhere = new ComboBox<>("Where");
        CBwhere.setItems(dataService.getAllLocations());
        return CBwhere;
    }

    private DateTimePicker prepareWhenDateTimePicker() {
        DateTimePicker DTPwhen = new DateTimePicker();
        DTPwhen.setLabel("When");
        DTPwhen.setStep(Duration.ofMinutes(30));
        DTPwhen.setValue(LocalDateTime.now());
        return DTPwhen;
    }

    private TextArea prepareDescriptionTextArea() {
        int charLimit = 1000;
        TextArea TADescription = new TextArea("Description");
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
            // TODO collect all data from form components, validate each input and if correct make Task class object and send to database
            Set<Animal> selectedAnimals = MSCBanimals.getSelectedItems();
            Set<Employee> selectedEmployees = MSCBwho.getSelectedItems();
            Location selectedLocation = CBwhere.getValue();
            TaskType selectedTaskGroup = CBtaskGroup.getValue();
            LocalDateTime startOfTaskTime = LocalDateTime.now();        // TODO change LocalDateTime to class that best suits DataBase
            LocalDateTime selectedDeadline = DTPwhen.getValue();        // TODO change LocalDateTime to class that best suits DataBase
            String description = TADescription.getValue();
            // TODO make Task object and try to insert it into database, send information for employees to update their task list
            // dataService.addTask(description, startOfTaskTime, null, selectedDeadline, );

            // temporary notification
            String joined_animals = String.join(selectedAnimals.toString());
            String joined_employees = String.join(selectedEmployees.toString());
            Notification.show("Employees: " + joined_employees + " \n have to do: "+ selectedTaskGroup + "\n near: " + selectedLocation + "\n Selected Animals:" + joined_animals+"\nStart: "+ startOfTaskTime.toString() + " and deadline is: "+selectedDeadline);
        });

        BClear.addClickListener(click -> {
            MSCBanimals.clear();
            MSCBwho.clear();
            CBwhere.clear();
            CBtaskGroup.clear();
            DTPwhen.setValue(LocalDateTime.now());
            TADescription.clear();

            Notification.show("All info's cleared!");
        });
        return buttons;
    }


    //    private ArrayList<String> getAniamalArrayList()
//    {
//        String[] animal_names = new String[] {"Koza1","MiśPolarnyJacek","MałpkaStefan","JeżRysiek"};
//        return new ArrayList<>(Arrays.asList(animal_names));
//    }
//    private ArrayList<String> getEmployeesArrayList()
//    {
//        String[] employee_names = new String[] {"Jacek Kochanowski","Katarzyna Dąb","Krystian Fach","Aleksandra Chuligan"};
//        return new ArrayList<>(Arrays.asList(employee_names));
//    }
//    private ArrayList<String> getTaskGroupArrayList()
//    {
//        String[] taskGroups = new String[] {"Sprzątanie Toalet","Karmienie","Zastrzyk","Obsługa Kasy", "Uzupełnianie Wody"};
//        return new ArrayList<>(Arrays.asList(taskGroups));
//    }    private ArrayList<String> getLocationArrayList()
//    {
//        String[] locations = new String[] {"Wybieg Słonia","Kawiarenka","Kasy", "Toalety"};
//        return new ArrayList<>(Arrays.asList(locations));
//    }

}
