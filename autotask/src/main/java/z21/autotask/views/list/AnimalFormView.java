package z21.autotask.views.list;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.DataService;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

@Route(value = "/animalForm", layout = MainLayout.class)
public class AnimalFormView extends FormLayout {


    @Autowired
    public AnimalFormView(DataService dataService) {

        Locale polishLocale = new Locale("pl", "PL");

        TextField nameTF = new TextField("Animal name:");
        TextField locationIdTF = new TextField("Animal habitat ID:");
        TextField speciesIdTF = new TextField("Species: ");
        TextField weightTF = new TextField("Weight:");
        DatePicker birthDateTF = new DatePicker("Select a date:");
        birthDateTF.setLocale(polishLocale);
        Button addButton = new Button("Add");

        addButton.addClickListener(click -> {

            int locationId = Integer.parseInt(locationIdTF.getValue());
            int speciesId = Integer.parseInt(speciesIdTF.getValue());
            Float weight = Float.parseFloat(weightTF.getValue());
            Date birthDate = Date.from(birthDateTF.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());


            dataService.addAnimal(nameTF.getValue(), locationId, speciesId, weight, birthDate);

            Notification.show("Successfully added new Animal to database!");
        });
        add(nameTF, locationIdTF, speciesIdTF, weightTF, birthDateTF);
    }
}
