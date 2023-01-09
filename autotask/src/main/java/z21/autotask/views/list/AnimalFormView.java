package z21.autotask.views.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.entities.*;
import z21.autotask.service.DataService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "/animalForm", layout = MainLayout.class)
public class AnimalFormView extends VerticalLayout {

    private final DataService dataService;
    private final ComboBox<Species> CBspecies;
    private final ComboBox<Location> CBlocation;
    private final DatePicker DTPdateOfBirth;
    private final TextField nameTF;
    private final TextField weightTF;

    HorizontalLayout buttons;

    @Autowired
    public AnimalFormView(DataService dataService) {
        this.dataService = dataService;

        FormLayout animalForm = new FormLayout();

        nameTF = new TextField("Animal name:");
        weightTF = new TextField("Weight:");
        CBspecies = prepareSpeciesComboBox();
        CBlocation = prepareLocationComboBox();
        DTPdateOfBirth = prepareDatePicker();
        buttons = prepareButtons();

        animalForm.add(nameTF, weightTF, CBspecies, CBlocation, DTPdateOfBirth, buttons);

        H1 title = new H1("Add Animal");
        add(title, animalForm);
        setMargin(true);
    }

    private HorizontalLayout prepareButtons() {
        Button addButton = new Button("Add");
        Button BClear = new Button("Clear");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(addButton, BClear);

        addButton.addClickListener(click -> {
            String name = nameTF.getValue();
            Float weight = Float.parseFloat(weightTF.getValue());
            Integer locationId = CBlocation.getValue().getLocationId();
            Integer speciesId = CBspecies.getValue().getSpeciesId();
            Date birthDate = Date.from(DTPdateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            dataService.addAnimal(name, locationId, speciesId, weight, birthDate);

            Notification.show("Successfully added new Animal to database!");
        });

        BClear.addClickListener(click -> {
            nameTF.clear();
            weightTF.clear();
            CBlocation.clear();
            CBspecies.clear();
            DTPdateOfBirth.setValue(LocalDate.now());

            Notification.show("All info cleared.");
        });
        return buttons;
    }

    private ComboBox<Location> prepareLocationComboBox(){
        ComboBox<Location> CBlocation = new ComboBox<>("Animal Habitat");
        CBlocation.setItems(dataService.getAllLocations());
        return CBlocation;
    }

    private ComboBox<Species> prepareSpeciesComboBox(){
        ComboBox<Species> CBspecies = new ComboBox<>("Animal Species");
        CBspecies.setItems(dataService.getAllSpecies());
        return CBspecies;
    }

    private DatePicker prepareDatePicker(){
        DatePicker DTPwhen = new DatePicker();
        DTPwhen.setLabel("Date of birth");
        DTPwhen.setValue(LocalDate.now());
        return DTPwhen;
    }

}
