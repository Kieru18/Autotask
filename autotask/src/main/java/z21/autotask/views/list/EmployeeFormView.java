package z21.autotask.views.list;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.entities.EmpStatus;
import z21.autotask.entities.Position;
import z21.autotask.entities.User;
import z21.autotask.service.DataService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "/employeeForm", layout = MainLayout.class)
public class EmployeeFormView extends FormLayout {

    private final DataService dataService;
    private final ComboBox<Position> CBposition;
    private final ComboBox<EmpStatus> CBempStatus;

    private final ComboBox<String> CBgender;
    private final DatePicker DTPdateOfBirth;

    private final TextField firstNameTF;
    private final TextField lastNameTF;



    @Autowired
    public EmployeeFormView(DataService dataService) {
        this.dataService = dataService;

        firstNameTF = new TextField("First name:");
        lastNameTF = new TextField("Last name:");
        CBgender = prepareGenderComboBox();
        CBposition = preparePositionsComboBox();
        CBempStatus = prepareEmpStatusComboBox();
        DTPdateOfBirth = prepareDatePicker();

        Button addButton = new Button("Add");

        addButton.addClickListener(click -> {
            String firstName = firstNameTF.getValue();
            String lastName = lastNameTF.getValue();
            
            User user = new User();
            Integer userId = user.getUserId();

            Integer positionId = CBposition.getValue().getPositionId();
            Integer empStatusId = CBempStatus.getValue().getStatusId();
            char gender = CBgender.getValue().charAt(0);
            Date birthDate = Date.from(DTPdateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dataService.addEmployee(firstName, lastName,  gender, birthDate, positionId, empStatusId, userId);

            Notification.show("Successfully added new Animal to database!");
        });
        add(firstNameTF,lastNameTF, CBgender, CBposition,CBempStatus, DTPdateOfBirth, addButton);
    }

    private ComboBox<Position> preparePositionsComboBox(){
        ComboBox<Position> CBposition = new ComboBox<>("Position");
        CBposition.setItems(dataService.getAllPositions());
        return CBposition;
    }

    private ComboBox<EmpStatus> prepareEmpStatusComboBox(){
        ComboBox<EmpStatus> CBempStatus = new ComboBox<>("Employee Status");
        CBempStatus.setItems(dataService.getAllEmpStatuses());
        return CBempStatus;
    }

    private ComboBox<String> prepareGenderComboBox(){
        ComboBox<String> CBspecies = new ComboBox<>("Gender");
        String[] genders = new String[]{"Women", "Men", "Unknown"};
        CBspecies.setItems(genders);
        return CBspecies;
    }

    private DatePicker prepareDatePicker(){
        DatePicker DTPwhen = new DatePicker();
        DTPwhen.setLabel("Date of birth");
        DTPwhen.setValue(LocalDate.now());
        return DTPwhen;
    }

}
