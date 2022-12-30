package z21.autotask.views.list;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;

@Route(value = "/Employees", layout = MainLayout.class)
public class EmployeeView extends Div {

    public EmployeeView() {
        Tab available = new Tab(VaadinIcon.CHECK.create(), new Span("Available"));
        Tab cos = new Tab();
        Tab unavailable = new Tab(VaadinIcon.CLOSE.create(), new Span("Unavailable"));
        Tab all = new Tab(VaadinIcon.USERS.create(), new Span("All"));

        // Set the icon on top
        for (Tab tab : new Tab[] { available, unavailable, all }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }
        VerticalLayout availableEmployees = getEmployeesCards(getAvailableEmployees());
        VerticalLayout unavailableEmployees = getEmployeesCards(getUnavailableEmployees());
        VerticalLayout allEmployees = getEmployeesCards(getAllEmployees());

        available.add(availableEmployees);
        unavailable.add(unavailableEmployees);
        all.add(allEmployees);

        Tabs tabs = new Tabs(all, available, unavailable);
        tabs.setSelectedTab(all);
        add(tabs);
    }
    private VerticalLayout getEmployeesCards(ArrayList<String> employees)
    {
        VerticalLayout verticalLayoutEmployees = new VerticalLayout();
        for(String employee : employees)
        {
            verticalLayoutEmployees.add(createEmployeeCardComponent(employee));
        }
        return verticalLayoutEmployees;
    }
    private HorizontalLayout createEmployeeCardComponent(String person)
    {
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setMargin(true);

//        Avatar avatar = new Avatar(person.getFullName(),
//                person.getPictureUrl());
        Avatar avatar = new Avatar(person); // TODO change String person to Employee person
        avatar.setHeight("64px");
        avatar.setWidth("64px");

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setPadding(false);
//        infoLayout.getElement().appendChild(
//                ElementFactory.createStrong(person.getFullName()));
        infoLayout.getElement().appendChild(
                ElementFactory.createStrong(person + "Name"));
        // infoLayout.add(new Div(new Text(person.getProfession())));
        infoLayout.add(new Div(new Text(person + "Position")));

        VerticalLayout contactLayout = new VerticalLayout();
        contactLayout.setSpacing(false);
        contactLayout.setPadding(false);
        // contactLayout.add(new Div(new Text(person.getEmail())));
        contactLayout.add(new Div(new Text(person + "Email")));
        // contactLayout.add(new Div(new Text(person.getAddress().getPhone())));
        contactLayout.add(new Div(new Text(person + "Contact Details")));
        infoLayout.add(new Details("Contact information", contactLayout));

        cardLayout.add(avatar, infoLayout);
        return cardLayout;
    }

    private ArrayList<String> getAvailableEmployees()
    {
        String[] ava_employees = new String[] {"Krysia Nowak","Jacek Placek","Malina Przytyła","Jan Ogórek"};
        return new ArrayList<>(Arrays.asList(ava_employees));
    }
    private ArrayList<String> getUnavailableEmployees()
    {
        String[] unava_employees = new String[] {"Iwo Franz","Kuba Niejadek","Piotr Grzeczny","Lidia Pałąk"};
        return new ArrayList<>(Arrays.asList(unava_employees));
    }
    private ArrayList<String> getAllEmployees()
    {
        String[] all_employees = new String[] {"Krysia Nowak","Jacek Placek","Malina Przytyła","Jan Ogórek","Iwo Franz","Kuba Niejadek","Piotr Grzeczny","Lidia Pałąk"};
        return new ArrayList<>(Arrays.asList(all_employees));
    }
}
