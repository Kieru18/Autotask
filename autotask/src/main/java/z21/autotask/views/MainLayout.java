package z21.autotask.views;

import java.util.Collection;

import javax.annotation.security.PermitAll;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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

import z21.autotask.views.form.AnimalFormView;
import z21.autotask.views.form.EmployeeFormView;
import z21.autotask.views.form.TaskFormView;
import z21.autotask.views.form.TaskTypeFormView;
import z21.autotask.views.list.AnimalsListView;
import z21.autotask.views.list.EmployeesListView;
import z21.autotask.views.list.MyTasksListView;
import z21.autotask.views.list.TasksListView;
import z21.autotask.security.SecurityService;

@PermitAll
@Route("")
public class MainLayout extends AppLayout{
    private SecurityService securityService;
    
    private Tabs getLinkTabs() {
        Tabs tabs = new Tabs();

        tabs.add(
            createTab(VaadinIcon.USER, "My tasks", MyTasksListView.class),
            createTab(VaadinIcon.FORM, "Add Task", TaskFormView.class),
            createTab(VaadinIcon.USERS, "Employees List", EmployeesListView.class),
            createTab(VaadinIcon.TWITTER, "Animals List", AnimalsListView.class));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals("ROLE_ADMIN")) {
                tabs.add(
                    createTab(VaadinIcon.TASKS, "All Tasks", TasksListView.class),
                    createTab(VaadinIcon.PLUS_CIRCLE, "Add Animal", AnimalFormView.class),
                    createTab(VaadinIcon.PLUS_CIRCLE, "Add Employee", EmployeeFormView.class),
                    createTab(VaadinIcon.PLUS_CIRCLE, "Add New Type of Tasks", TaskTypeFormView.class));
            }
        }

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

    public MainLayout(@Autowired SecurityService securityService) {
        this.securityService = securityService;

        H1 title = new H1("AutoTask");
        title.addClickListener(click ->{
            title.getUI().ifPresent(ui ->
                    ui.navigate(MyTasksListView.class));
        });
        DrawerToggle linksDT = new DrawerToggle();
        linksDT.addClassNames("drawer-toggle");

        Button logout = new Button("Log out", e -> securityService.logout());
        logout.addClassNames("logout-button");
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

//    @Override
//    public void beforeEnter(BeforeEnterEvent event) {
//        event.forwardTo("/myTasks");
//    }
}
