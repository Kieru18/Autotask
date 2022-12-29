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
        VerticalLayout links = new VerticalLayout();
        RouterLink listView = new RouterLink("AnimalList", ListView.class);
        RouterLink AnimalForm = new RouterLink("AnimalForm", AnimalFormView.class);
        RouterLink TaskGenerator = new RouterLink("Task Generator", TaskFormView.class);
        listView.setHighlightCondition(HighlightConditions.sameLocation());
        AnimalForm.setHighlightCondition(HighlightConditions.sameLocation());
        TaskGenerator.setHighlightCondition(HighlightConditions.sameLocation());
        links.add(listView, AnimalForm, TaskGenerator);
        addToDrawer(links);
    }
}
