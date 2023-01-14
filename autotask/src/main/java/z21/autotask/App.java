package z21.autotask;

import com.vaadin.flow.theme.lumo.Lumo;
import java.lang.String;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;


@SpringBootApplication
@Theme(value = "my_theme", variant = Lumo.DARK)
@PWA(name = "Autotask - manage your work", shortName = "Autotask", offlineResources = {})
@NpmPackage(value = "line-awesome", version = "1.3.0")
public class App implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}