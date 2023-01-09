package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.service.DataService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Route(value = "/taskTypeForm", layout = MainLayout.class)
public class TaskTypeFormView extends VerticalLayout {

    private final DataService dataService;
    private final TextField nameTF;
    private final TextArea TADescription;
    private final ComboBox<Integer> CBbasePriority;
    private final DateTimePicker DTPfrequency;
    HorizontalLayout buttons;
    
    @Autowired
    public TaskTypeFormView(DataService dataService) {
        this.dataService = dataService;

        FormLayout taskTypeForm = new FormLayout();
        nameTF = new TextField("Name:");
        TADescription = prepareDescriptionTextArea();
        CBbasePriority = preparePriorityComboBox();
        DTPfrequency = prepareDateTimePicker();
        buttons = prepareButtons();

        taskTypeForm.add(nameTF, TADescription, CBbasePriority, DTPfrequency, buttons);

        H1 title = new H1("Add new type of task");
        add(title, taskTypeForm);
        setMargin(true);

    }

    private HorizontalLayout prepareButtons() {
        Button addButton = new Button("Add");
        Button BClear = new Button("Clear");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(addButton, BClear);

        addButton.addClickListener(click -> {
            String name = nameTF.getValue();
            String description  = TADescription.getValue();
            Integer basePriority = CBbasePriority.getValue();
            Date frequency = Date.from(DTPfrequency.getValue().atZone(ZoneId.systemDefault()).toInstant());

            dataService.addTaskType(name, description, basePriority, frequency);

            Notification.show("Successfully added new Animal to database!");
        });

        BClear.addClickListener(click -> {
            nameTF.clear();
            TADescription.clear();
            CBbasePriority.clear();
            DTPfrequency.setValue(LocalDateTime.now());

            Notification.show("Form cleared!");
        });
        return buttons;
    }

    private DateTimePicker prepareDateTimePicker() {
        DateTimePicker DTPwhen = new DateTimePicker();
        DTPwhen.setLabel("Frequency");
        DTPwhen.setStep(Duration.ofMinutes(30));
        DTPwhen.setValue(LocalDateTime.now());
        return DTPwhen;
    }

    private ComboBox<Integer> preparePriorityComboBox() {
        ComboBox<Integer> CBspecies = new ComboBox<>("Base Priority");
        Integer[] priorities = new Integer[]{1, 2, 3, 4, 5};
        CBspecies.setItems(priorities);
        return CBspecies;
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

}
