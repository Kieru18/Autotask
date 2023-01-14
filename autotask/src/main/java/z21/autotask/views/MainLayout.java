package z21.autotask.views;

import java.util.Collection;
import java.util.List;

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
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.details.Details;
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
import z21.autotask.entities.Employee;
import z21.autotask.entities.User;
import z21.autotask.security.SecurityService;
import z21.autotask.service.DataService;

@PermitAll
public class MainLayout extends AppLayout{
    private SecurityService securityService;
    private DataService dataService;
    
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

    public MainLayout(@Autowired SecurityService securityService, @Autowired DataService dataService) {
        this.securityService = securityService;
        this.dataService = dataService;

        H1 title = new H1("AutoTask");
        title.addClickListener(click ->{
            title.getUI().ifPresent(ui ->
                    ui.navigate(MyTasksListView.class));
        });
        DrawerToggle linksDT = new DrawerToggle();
        linksDT.addClassNames("drawer-toggle");

        Button logout = new Button("Log out", e -> securityService.logout());
        logout.addClassNames("logout-button");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Employee> employees = dataService.getEmployeeByUserLogin(authentication.getName());
        Employee employee = employees.get(0);
        HorizontalLayout userInfo = createUserCardComponent(employee);
  
        HorizontalLayout header = new HorizontalLayout(linksDT, title, userInfo, logout);
        

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(title);
        header.setWidthFull();

        addToNavbar(header);

        createNavigationDrawer();

    }

    private void createNavigationDrawer() {
        addToDrawer(getLinkTabs());
    }

    private HorizontalLayout createUserCardComponent(Employee employee)
    {
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setMargin(true);

        Avatar avatar = new Avatar(employee.getFullName());
        avatar.setImage(employee.getPictureRoute());

        avatar.setHeight("64px");
        avatar.setWidth("64px");

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setPadding(false);
        infoLayout.getElement().appendChild(
                ElementFactory.createStrong(employee.getFullName()));
        infoLayout.add(new Div(new Text(employee.getProfession())));


        VerticalLayout contactLayout = new VerticalLayout();
        contactLayout.setSpacing(false);
        contactLayout.setPadding(false);
        
        cardLayout.add(avatar, infoLayout);
        return cardLayout;
    }


}
