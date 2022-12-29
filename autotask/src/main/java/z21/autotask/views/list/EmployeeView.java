package z21.autotask.views.list;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Arrays;

@Route(value = "/Employees", layout = MainLayout.class)
public class EmployeeView extends Div {

    public EmployeeView() {
        Tab available = new Tab(VaadinIcon.CHECK.create(), new Span("Available"));
        Tab unavailable = new Tab(VaadinIcon.CLOSE.create(), new Span("Unavailable"));

        // Set the icon on top
        for (Tab tab : new Tab[] { available, unavailable }) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }
        VerticalLayout availableEmployees = new VerticalLayout();
        VerticalLayout unavailableEmployees = new VerticalLayout();



        Tabs tabs = new Tabs(available, unavailable);
        add(tabs);
    }


    private ArrayList<String> getAvailableEmployees()
    {
        String[] ava_employees = new String[] {"Krysia Nowak","Jacek Placek","Malina Przytyła","Jan Ogórek"};
        return new ArrayList<>(Arrays.asList(ava_employees));
    }
}
