package z21.autotask.views.list;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.Animal;
import z21.autotask.DataService;

@Route(value = "/animalForm", layout = MainLayout.class)
public class AnimalFormView extends FormLayout {

    private DataService dataService;

    @Autowired
    public AnimalFormView(DataService dataService) {
        this.dataService = dataService;

        TextField idTF = new TextField("Animal ID:");
        TextField nameTF = new TextField("Animal name:");
        TextField colorTF = new TextField("Animal color:");
        TextField legCountTF = new TextField("Number of legs:");
        Button addButton = new Button("Add");

        addButton.addClickListener(click -> {
            int animalId = Integer.parseInt(idTF.getValue());
            int numberOfLegs = Integer.parseInt(legCountTF.getValue());
            dataService.addAnimal(animalId, nameTF.getValue(), colorTF.getValue(), numberOfLegs);

            Notification.show("Succesfully added new Animal to database!");
        });
        add(idTF,nameTF,colorTF, legCountTF, addButton);
    }
}
