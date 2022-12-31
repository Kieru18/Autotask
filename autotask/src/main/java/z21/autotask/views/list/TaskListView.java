package z21.autotask.views.list;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.web.method.support.CompositeUriComponentsContributor;

import java.util.ArrayList;
import java.util.Arrays;

@Route(value = "/TaskList", layout = MainLayout.class)
public class TaskListView extends HorizontalLayout {

    // private ArrayList<ArrayList<Button>> buttonMap;
    private final Tab pending;
    private final Tab completed;
    private final VerticalLayout content = new VerticalLayout();

    private final VerticalLayout contentPending;
    private final VerticalLayout contentCompleted;
    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(pending)) {
            content.add(contentPending);}
        else {
            content.add(contentCompleted);
        }
    }
    public TaskListView() {
        pending = new Tab(VaadinIcon.STOPWATCH.create(), new Span("Pending"));
        completed = new Tab(VaadinIcon.CHECK.create(), new Span("Completed"));
        // Set the icon on top
        for (Tab tab : new Tab[] {pending, completed}) {
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }
        contentPending = getAccordion(getPendingTasks());
        contentCompleted = getAccordion(getCompletedTasks());

        Tabs tabs = new Tabs(pending, completed);
        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab()));
        tabs.setSelectedTab(pending);
        setContent(tabs.getSelectedTab());

        Div tabs_and_task_accordion = new Div();
        tabs_and_task_accordion.add(tabs,content);
        // add(tabs, content);

        add(tabs_and_task_accordion, getButtonMapLayout(getDescribedLocalizations()));
    }
    private ArrayList<ArrayList<String>> getDescribedLocalizations()
    {
        ArrayList<ArrayList<String>> map = new ArrayList<>();
        int p = 0;
        for(int x =0; x< 12; x++)
        {
            ArrayList<String> row = new ArrayList<>();
            for(int y=0; y < 12; y++)
            {
                p = 12*y+x;
                row.add(Integer.toString(p));
            }
            map.add(row);
        }
        return  map;
    }
    private VerticalLayout getButtonMapLayout(ArrayList<ArrayList<String>> describedLocations)
    {
        VerticalLayout buttonMapLayout = new VerticalLayout();
        for(ArrayList<String> rows : describedLocations)
        {
            ArrayList<Button> button_row = new ArrayList<>();
            HorizontalLayout button_row_layout = new HorizontalLayout();
            for(String location_summary : rows)
            {
                Button localization = new Button(location_summary);
                button_row.add(localization);
                button_row_layout.add(localization);
            }
            buttonMapLayout.add(button_row_layout);
            // buttonMap.add(button_row);
        }
        return buttonMapLayout;
    }
    private VerticalLayout getAccordion(ArrayList<String> tasks)
    {
        int task_counter = 1;
        VerticalLayout verticalLayoutTasks = new VerticalLayout();
        Accordion accordion = new Accordion();
        for(String task : tasks)
        {
            AccordionPanel cos = accordion.add((Integer.toString(task_counter)+". "+ task), createTaskDetailsLayout(task));
            cos.addThemeVariants(DetailsVariant.FILLED);
            task_counter+=1;
        }
        accordion.close();
        verticalLayoutTasks.add(accordion);
        return verticalLayoutTasks;
    }
    private VerticalLayout createTaskDetailsLayout(String task)
    {
        Span name = new Span(task+ " Task name");
        Span location = new Span(task+ " Task location");
        Span deadline = new Span(task+ " Task deadline");

        VerticalLayout taskDetails = new VerticalLayout(name,location, deadline);
        taskDetails.setSpacing(false);
        taskDetails.setPadding(false);
        return taskDetails;
    }

    private ArrayList<String> getPendingTasks()
    {
        String[] pending_tasks = new String[] {"Sprzątanie toalety 1","Karmienie Małp","Umycie stołów w kawiarni","Eutanazja cierpiącego wężą"};
        return new ArrayList<>(Arrays.asList(pending_tasks));
    }
    private ArrayList<String> getCompletedTasks()
    {
        String[] completed_tasks = new String[] {"Mycie okien w toalecie","Przytulas z niedziedziem polarnym","Siedzenie na krześle","Tworzenie babeczek"};
        return new ArrayList<>(Arrays.asList(completed_tasks));
    }
}
