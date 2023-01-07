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
import java.util.ArrayList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.service.DataService;

@Route(value = "/TaskForm", layout = MainLayout.class)
public class TaskFormView extends VerticalLayout {
    private DataService dataService;
    private MultiSelectComboBox<String> MSCBwho;
    private MultiSelectComboBox<String> MSCBanimals;
    private ComboBox<String> CBtaskGroup;
    private ComboBox<String> CBwhere;
    private DateTimePicker DTPwhen;
    private TextArea TADescription;
    HorizontalLayout buttons;


    @Autowired
    public TaskFormView() {
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

    private MultiSelectComboBox<String> prepareWhoMultiSelectComboBox(){
        ArrayList<String> employees = getEmployeesArrayList();
        MultiSelectComboBox<String> MSCBwho = new MultiSelectComboBox<>("Who");  // TODO change String to Animal class
        MSCBwho.setItems(employees);                                                  // TODO change animals  to data provider
        return MSCBwho;
    }

    private MultiSelectComboBox<String> prepareAnimalsMultiSelectComboBox()
    {
        ArrayList<String> animals = getAniamalArrayList();
        MultiSelectComboBox<String> MSCBanimals = new MultiSelectComboBox<>("Animals"); // TODO change String to Animal class
        MSCBanimals.setItems(animals);  // TODO change animals  to data provider
        MSCBanimals.addSelectionListener(e-> {
            Optional<String> recentlySelectedValue = e.getFirstSelectedItem();
            // TODO get associated locaction of selected animal and update this function
            if(recentlySelectedValue.isPresent())
            {
                CBwhere.setValue("Lokalizacja Pierwszego zwierzaczka: " + recentlySelectedValue.get());
            }
        });
        return MSCBanimals;
    }

    private ArrayList<String> getAniamalArrayList()
    {
        String[] animal_names = new String[] {"Koza1","MiśPolarnyJacek","MałpkaStefan","JeżRysiek"};
        return new ArrayList<>(Arrays.asList(animal_names));
    }
    private ArrayList<String> getEmployeesArrayList()
    {
        String[] employee_names = new String[] {"Jacek Kochanowski","Katarzyna Dąb","Krystian Fach","Aleksandra Chuligan"};
        return new ArrayList<>(Arrays.asList(employee_names));
    }
    private ArrayList<String> getTaskGroupArrayList()
    {
        String[] taskGroups = new String[] {"Sprzątanie Toalet","Karmienie","Zastrzyk","Obsługa Kasy", "Uzupełnianie Wody"};
        return new ArrayList<>(Arrays.asList(taskGroups));
    }    private ArrayList<String> getLocationArrayList()
    {
        String[] locations = new String[] {"Wybieg Słonia","Kawiarenka","Kasy", "Toalety"};
        return new ArrayList<>(Arrays.asList(locations));
    }


    private ComboBox<String> prepareTaskGroupComboBox(){
        ArrayList<String> taskGroups = getTaskGroupArrayList();
        ComboBox<String> CBtaskGroup = new ComboBox<>("Task Group");                 // TD change ComboBox<String> to ComboBox<TaskGroup>
        CBtaskGroup.setItems(taskGroups);
        return CBtaskGroup;
    }
    private ComboBox<String> prepareWhereComboBox(){
        ArrayList<String> locations = getLocationArrayList();
        ComboBox<String> CBwhere = new ComboBox<>("Where");
        CBwhere.setItems(locations);
        return CBwhere;
    }
    private DateTimePicker prepareWhenDateTimePicker()
    {
        DateTimePicker DTPwhen = new DateTimePicker();
        DTPwhen.setLabel("When");
        DTPwhen.setStep(Duration.ofMinutes(30));
        DTPwhen.setValue(LocalDateTime.now());
        return DTPwhen;
    }
    private TextArea prepareDescriptionTextArea()
    {
        int charLimit = 1000;
        TextArea TADescription = new TextArea("Description");
        TADescription.setWidthFull();
        TADescription.setMaxLength(charLimit);
        TADescription.setValueChangeMode(ValueChangeMode.EAGER);
        TADescription.addValueChangeListener(e -> e.getSource().setHelperText(e.getValue().length() + "/" + charLimit));
        return TADescription;
    }

    private HorizontalLayout prepareButtons()
    {
        Button BSubmit = new Button("Submit");
        Button BClear = new Button("Clear");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(BSubmit, BClear);

        BSubmit.addClickListener(click -> {
            // TODO collect all data from form components, validate each input and if correct make Task class object and send to database
            Set<String> selectedAnimals = MSCBanimals.getSelectedItems(); // TODO change String to Animal
            Set<String> selectedEmployees = MSCBwho.getSelectedItems(); // TODO change String to Employee
            String selectedLocation = CBwhere.getValue();               // TODO change String to Location
            String selectedTaskGroup = CBtaskGroup.getValue();          // TODO change String to TaskGroup
            LocalDateTime startOfTaskTime = LocalDateTime.now();        // TODO change LocalDateTime to class that best suits DataBase
            LocalDateTime selectedDeadline = DTPwhen.getValue();        // TODO change LocalDateTime to class that best suits DataBase
            String description = TADescription.getValue();              // THAT IS GOOOD DO NOT CHANGE
            // TODO make Task object and try to insert it into database, send information for employees to update their task list

            // temporary notification
            String joined_animals = String.join(",", selectedAnimals);
            String joined_employees = String.join(",", selectedEmployees);
            Notification.show("Employees: " + joined_employees + " \n have to do: "+ selectedTaskGroup + "\n near: " + selectedLocation + "\n Selected Animals:" + joined_animals+"\nStart: "+ startOfTaskTime.toString() + " and deadline is: "+selectedDeadline);
        });
        BClear.addClickListener(click -> {
            // TODO clear TADescription, CBwho, CBtaskGroup, CBwhere. DTPwhen set to default
            MSCBanimals.clear();
            MSCBwho.clear();
            CBwhere.clear();
            CBtaskGroup.clear();
            DTPwhen.setValue(LocalDateTime.now());
            TADescription.clear();

            Notification.show("All components should be cleared :)");
        });
        return buttons;
    }
}
