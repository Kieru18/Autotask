package z21.autotask.views.list;

import javax.annotation.security.PermitAll;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
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

import z21.autotask.service.SecurityService;

@PermitAll
@Route("")
public class MainLayout extends AppLayout {

    // TODO implement login window to main view so its the first thing not logged in user sees
    // TODO also implement map view as a default view for the users
    // TODO implement restriction of views for certain users (if possible)
    // TODO implement returning to main view when the logo gets clicked

    private final SecurityService securityService;
    

    private Tabs getLinkTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.TASKS, "Tasks List", AnimalListView.class), // TODO add here and implement TasksListView.class
                createTab(VaadinIcon.FORM, "Add Task", TaskFormView.class),
                createTab(VaadinIcon.USERS, "Employees List", EmployeesView.class), // TODO change name to EmployeeListView.class
                createTab(VaadinIcon.TWITTER, "Animals List", AnimalListView.class),
                createTab(VaadinIcon.PLUS_CIRCLE, "Add Animal", AnimalFormView.class),
                createTab(VaadinIcon.PLUS_CIRCLE, "Add Employee", AnimalFormView.class),     // TODO add here and implement EmployeeFormView.class
                createTab(VaadinIcon.PLUS_CIRCLE, "Add New Type of Tasks", AnimalFormView.class)); // TODO add here and implement TaskTypeFormView.class

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

    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;

        H1 title = new H1("AutoTask");
        DrawerToggle linksDT = new DrawerToggle();

        Button logout = new Button("Log out", e -> securityService.logout());
  
        HorizontalLayout header = new HorizontalLayout(linksDT, title, logout);

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
