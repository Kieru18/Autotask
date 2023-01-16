package z21.autotask.views;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import z21.autotask.service.MailService;

@Route("login") 
@PageTitle("Login | Autotask")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {
	private MailService mailService;
	private final LoginForm login = new LoginForm(); 

	Dialog dialogMail = new Dialog();

	public LoginView(@Autowired MailService mailService) {
		this.mailService = mailService;

		addClassName("login-view");
		setSizeFull(); 
		setAlignItems(Alignment.CENTER);
		setJustifyContentMode(JustifyContentMode.CENTER);

		login.setAction("login"); 
		
		login.addForgotPasswordListener(e-> {
			dialogMail.setHeaderTitle("Add user");
			dialogMail.setMinWidth("700px");

			VerticalLayout addUserLayout = MailForm();
			dialogMail.removeAll();
			dialogMail.add(addUserLayout);
			dialogMail.open();
        });

		add(new H1("Autotask"), login);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		// inform the user about an authentication error
		if(beforeEnterEvent.getLocation()  
        .getQueryParameters()
        .getParameters()
        .containsKey("error")) {
            login.setError(true);
        }
	}

	private VerticalLayout MailForm() {
        VerticalLayout addMail = new VerticalLayout();
        FormLayout mailForm = new FormLayout();

        Button addButton = new Button("Send");
        Button BClear = new Button("Clear");
        HorizontalLayout buttonsView = new HorizontalLayout();
        buttonsView.add(addButton, BClear);

        TextField mailTF = new TextField("E-mail:");

        addButton.addClickListener(click -> {
			String mail = mailTF.getValue();
			String subject = "Password reset";
			String body = "Contact your administrator to reset your password. ";

            this.mailService.sendEmail(mail, subject, body);

            Notification.show("E-mail has been sent.");

            dialogMail.close();
        });

        BClear.addClickListener(click -> {
            mailTF.clear();
            Notification.show("E-mail cleared.");
        });

        mailForm.add(mailTF, buttonsView);
        addMail.add(mailForm);
        return addMail;
    }
}
