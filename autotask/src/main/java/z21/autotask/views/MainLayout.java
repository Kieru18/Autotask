package z21.autotask.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import z21.autotask.views.form.AnimalFormView;
import z21.autotask.views.form.EmployeeFormView;
import z21.autotask.views.form.TaskFormView;
import z21.autotask.views.form.TaskTypeFormView;
import z21.autotask.views.list.AnimalsListView;
import z21.autotask.views.list.EmployeesListView;
import z21.autotask.views.list.TasksListView;

@Route("")
public class MainLayout extends AppLayout {

    // TODO implement login window to main view so its the first thing not logged in user sees
    // TODO also implement map view as a default view for the users
    // TODO implement restriction of views for certain users (if possible)
    // TODO implement returning to main view when the logo gets clicked

    private Tabs getLinkTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.TASKS, "Tasks List", TasksListView.class),
                createTab(VaadinIcon.FORM, "Add Task", TaskFormView.class),
                createTab(VaadinIcon.USERS, "Employees List", EmployeesListView.class),
                createTab(VaadinIcon.TWITTER, "Animals List", AnimalsListView.class),
                createTab(VaadinIcon.PLUS_CIRCLE, "Add Animal", AnimalFormView.class),
                createTab(VaadinIcon.PLUS_CIRCLE, "Add Employee", EmployeeFormView.class),
                createTab(VaadinIcon.PLUS_CIRCLE, "Add New Type of Tasks", TaskTypeFormView.class));

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> link) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink Rlink = new RouterLink(link);
        Rlink.add(icon, new Span(viewName));
        Rlink.setTabIndex(-1);
        return new Tab(Rlink);
    }

    public MainLayout() {
        H1 title = new H1("AutoTask");
        DrawerToggle linksDT = new DrawerToggle();
        HorizontalLayout header = new HorizontalLayout(linksDT, title);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(title);
        header.setWidthFull();
        addToNavbar(header);

        createNavigationDrawer();
    }

    private void createNavigationDrawer() {
        addToDrawer(getLinkTabs());
    }
}
