package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.html.H1;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
// import z21.autotask.DataService;

@Route(value = "/TaskForm", layout = MainLayout.class)
public class TaskFormView extends VerticalLayout {
    // private DataService dataService;

    @Autowired
    public TaskFormView() {
        // this.dataService = dataService;

        FormLayout taskForm = new FormLayout();

        ComboBox<String> CBwho = prepareWhoComboBox();



        ComboBox<String> CBtaskGroup = new ComboBox<>("What to do");    // TD change ComboBox<String> to ComboBox<TaskGroup>
        ComboBox<String> CBwhere = new ComboBox<>("Where");             // TD change ComboBox<String> to ComboBox<Location>

        DateTimePicker DTPwhen = new DateTimePicker();
        DTPwhen.setLabel("When");
        DTPwhen.setStep(Duration.ofMinutes(30));
        DTPwhen.setValue(LocalDateTime.now());

        int charLimit = 1000;
        
        TextArea TADescription = new TextArea("Description");
        TADescription.setWidthFull();
        TADescription.setMaxLength(charLimit);
        TADescription.setValueChangeMode(ValueChangeMode.EAGER);
        TADescription.addValueChangeListener(e -> e.getSource().setHelperText(e.getValue().length() + "/" + charLimit));

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
        taskForm.add(CBwho, CBtaskGroup, CBwhere, TADescription, DTPwhen, buttons);

        H1 title = new H1("Task Generator");
        add(title, taskForm);
    }

    private ComboBox<String> prepareWhoComboBox(){
        ArrayList<String> employees = new ArrayList<String>();
        employees.add("Pracownik1");
        ComboBox<String> CBwho = new ComboBox<>("Who");                 // TD change ComboBox<String> to ComboBox<Employee>
        CBwho.setAllowCustomValue(true);
        CBwho.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            employees.add(customValue);
            CBwho.setItems(employees);
            CBwho.setValue(customValue);});
        return CBwho;
    }

}
