package z21.autotask.views.list;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {



    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        H1 title = new H1("AutoTask");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), title);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(title);
        header.setWidthFull();
        addToNavbar(header);
    }

    private void createHeader() {
        RouterLink listView = new RouterLink("AnimalList", ListView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(listView), new RouterLink("AnimalForm", AnimalFormView.class));
        addToDrawer(new VerticalLayout(listView), new RouterLink("Task Generator", TaskFormView.class));

    }
}
