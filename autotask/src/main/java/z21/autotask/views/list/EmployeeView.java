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
import com.vaadin.flow.server.VaadinService;
import org.springframework.beans.factory.annotation.Autowired;
import z21.autotask.entities.Employee;
import z21.autotask.service.DataService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "/Employees", layout = MainLayout.class)
public class EmployeeView extends Div {


    private final DataService dataService;
    private final Tab available;
    private final Tab unavailable;
    private final Tab all;
    private final VerticalLayout content = new VerticalLayout();

    private final VerticalLayout contentAvailable;
    private final VerticalLayout contentUnavailable;
    private final VerticalLayout contentAll;
    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(all)) {
            content.add(contentAll);
        } else if (tab.equals(available)) {
            content.add(contentAvailable);
        } else {
            content.add(contentUnavailable);
        }
    }
    @Autowired
    public EmployeeView(DataService dataService) {
        this.dataService = dataService;
        available = new Tab(VaadinIcon.CHECK.create(), new Span("Available"));
        unavailable = new Tab(VaadinIcon.CLOSE.create(), new Span("Unavailable"));
        all = new Tab(VaadinIcon.USERS.create(), new Span("All"));

        // Set the icon on top
        for (Tab tab : new Tab[] { available, unavailable, all }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        contentAvailable = getEmployeesCards(getAvailableEmployees());
        contentUnavailable = getEmployeesCards(getUnavailableEmployees());
        contentAll = getEmployeesCards(getAllEmployees());

        Tabs tabs = new Tabs(all, available, unavailable);
        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab()));
        tabs.setSelectedTab(all);
        setContent(tabs.getSelectedTab());
        add(tabs, content);
    }
    private VerticalLayout getEmployeesCards(List<Employee> employees)
    {
        VerticalLayout verticalLayoutEmployees = new VerticalLayout();
        for(Employee employee : employees)
        {
            verticalLayoutEmployees.add(createEmployeeCardComponent(employee));
        }
        return verticalLayoutEmployees;
    }
    private HorizontalLayout createEmployeeCardComponent(Employee person)
    {
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setMargin(true);

        Avatar avatar = new Avatar(person.getFullName());
        avatar.setImage(person.getPictureUrl());

        avatar.setHeight("64px");
        avatar.setWidth("64px");

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setPadding(false);
        infoLayout.getElement().appendChild(
                ElementFactory.createStrong(person.getFullName()));
        infoLayout.add(new Div(new Text(person.getProfession())));


        VerticalLayout contactLayout = new VerticalLayout();
        contactLayout.setSpacing(false);
        contactLayout.setPadding(false);
        contactLayout.add(new Div(new Text(person.getEmail())));
        infoLayout.add(new Details("Contact information", contactLayout));

        cardLayout.add(avatar, infoLayout);
        return cardLayout;
    }

    private List<Employee> getAvailableEmployees()
    {
        List<Employee> availableEmployees = dataService.getAllEmployees();
        return availableEmployees;
    }
    private List<Employee> getUnavailableEmployees()
    {
        List<Employee> unavailableEmployees = dataService.getUnavailableEmployees();
        return unavailableEmployees;
    }
    private List<Employee> getAllEmployees()
    {
        List<Employee> allEmployees = dataService.getAvailableEmployees();
        return allEmployees;
    }
}
