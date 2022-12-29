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

import org.springframework.beans.factory.annotation.Autowired;
// import z21.autotask.DataService;

@Route(value = "/TaskForm", layout = MainLayout.class)
public class TaskFormView extends VerticalLayout {
    // private DataService dataService;
    private MultiSelectComboBox<String> MSCBwho;
    private MultiSelectComboBox<String> MSCBanimals;
    private ComboBox<String> CBtaskGroup;
    private ComboBox<String> CBwhere;
    private DateTimePicker DTPwhen;
    private TextArea TADescription;
    HorizontalLayout buttons;


    @Autowired
    public TaskFormView() {
        // this.dataService = dataService;

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
    }

    private MultiSelectComboBox<String> prepareWhoMultiSelectComboBox(){
        ArrayList<String> employees = new ArrayList<>();
        employees.add("Pracownik1");
        MultiSelectComboBox<String> MSCBwho = new MultiSelectComboBox<>("Who");                 // TD change ComboBox<String> to ComboBox<Employee>
        MSCBwho.setAllowCustomValue(true);
        MSCBwho.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            employees.add(customValue);
            MSCBwho.setItems(employees);
            MSCBwho.setValue(customValue);});
        return MSCBwho;
    }

    private MultiSelectComboBox<String> prepareAnimalsMultiSelectComboBox()
    {
        ArrayList<String> animals = getAniamalArrayList();
        MultiSelectComboBox<String> MSCBanimals = new MultiSelectComboBox<>("Animals"); // TODO change String to Animal class
        MSCBanimals.setItems(animals); // TODO change animals  to data provider
                                        /*
                                        *                   MSCBanimals.setItems(DataService.getAnimals());
                                                            MSCBanimals.setItemLabelGenerator(Animal::getName);
                                        * */
        return MSCBanimals;
    }

    private ArrayList<String> getAniamalArrayList()
    {
        String[] animal_names = new String[] {"Koza1","MiśPolarnyJacek","MałpkaStefan","JeżRysiek"};
        return new ArrayList<>(Arrays.asList(animal_names));
    }

    private ComboBox<String> prepareTaskGroupComboBox(){
        ArrayList<String> taskGroups = new ArrayList<>();
        taskGroups.add("Karmienie");
        ComboBox<String> CBtaskGroup = new ComboBox<>("What to do");                 // TD change ComboBox<String> to ComboBox<TaskGroup>
        CBtaskGroup.setAllowCustomValue(true);
        CBtaskGroup.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            taskGroups.add(customValue);
            CBtaskGroup.setItems(taskGroups);
            CBtaskGroup.setValue(customValue);});
        return CBtaskGroup;
    }
    private ComboBox<String> prepareWhereComboBox(){
        ArrayList<String> locations = new ArrayList<>();
        locations.add("Wybieg dla Słonia");
        ComboBox<String> CBwhere = new ComboBox<>("Where");
        CBwhere.setItems(locations);
        CBwhere.setAllowCustomValue(true);
        CBwhere.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            locations.add(customValue);
            CBwhere.setItems(locations);
            CBwhere.setValue(customValue);});
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
            // TD collect all data from form components, validate each input and if correct make Task class object and send to database
            Notification.show("Not implemented: Submit");
        });
        BClear.addClickListener(click -> {
            // TD clear TADescription, CBwho, CBtaskGroup, CBwhere. DTPwhen set to default
            Notification.show("Not implemented: Clear");
        });
        return buttons;
    }
}
