package z21.autotask.views.list;

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

@Route("")
public class MainLayout extends AppLayout {

    private Tabs getLinkTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.TASKS, "Task List", AnimalListView.class), // TODO change AnimalFormView.class to main view - TasksView.class
                createTab(VaadinIcon.USERS, "Employees", EmployeeView.class),
                createTab(VaadinIcon.FORM, "Task Form", TaskFormView.class),
                createTab(VaadinIcon.HAMMER, "Add Animal", AnimalFormView.class),
                createTab(VaadinIcon.TWITTER, "Animals", AnimalListView.class));
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
