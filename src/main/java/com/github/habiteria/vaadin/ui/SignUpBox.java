package com.github.habiteria.vaadin.ui;

import com.github.habiteria.vaadin.ui.beans.SignUpBean;
import com.github.habiteria.vaadin.service.WebUserAuthService;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static com.github.habiteria.vaadin.ui.Messages.message;

/**
 * @author Alex Ivchenko
 */
@SpringComponent
@UIScope
@Slf4j
public final class SignUpBox extends VerticalLayout {
    private WebUserAuthService webUserAuthService;

    private TextField username;
    private TextField email;
    private TextField firstName;
    private TextField lastName;
    private PasswordField password;
    private PasswordField confirmPassword;
    private Button signUp;

    private Binder<SignUpBean> binder;

    @Autowired
    public void setWebUserAuthService(WebUserAuthService webUserAuthService) {
        this.webUserAuthService = webUserAuthService;
    }

    @PostConstruct
    private void initialize() {
        config();
        initializeFields();
        initializeSignUpButton();
        initializeBinder();
        applyAllComponents();
        username.focus();
    }

    private void config() {
        setWidth("400px");
        setSpacing(true);
        setMargin(true);
    }

    private void initializeFields() {
        username = new TextField(message("label.user.username"));
        email = new TextField(message("label.user.email"));
        firstName = new TextField(message("label.user.firstName"));
        lastName = new TextField(message("label.user.lastName"));
        password = new PasswordField(message("label.user.password"));
        confirmPassword = new PasswordField(message("label.user.confirmPass"));

    }

    private void initializeSignUpButton() {
        signUp = new Button(message("label.signUp"));
        signUp.setEnabled(false);
        signUp.addClickListener(
                event -> registerNewUser(binder.getBean()));
    }

    private void registerNewUser(SignUpBean signUpForm) {
        try {
            webUserAuthService.signUp(signUpForm);
        } catch (UsernameExistsException e) {
            log.warn("username " + signUpForm.getUsername() + " exists");
        }
    }

    private void initializeBinder() {
        binder = new BeanValidationBinder<>(SignUpBean.class);
        binder.setBean(new SignUpBean());
        binder.bind(username, "username");
        binder.bind(password, "password");
        binder.bind(firstName, "firstName");
        binder.bind(lastName, "lastName");
        binder.bind(email, "email");
        binder.bind(password, "password");
        binder.bind(confirmPassword, "confirmPassword");
        binder.addStatusChangeListener(event -> signUp.setEnabled(binder.isValid()));
    }

    private void applyAllComponents() {
        addComponents(username, email, firstName, lastName, password, confirmPassword, signUp);
        forEach(component -> component.setWidth("100%"));
    }
}