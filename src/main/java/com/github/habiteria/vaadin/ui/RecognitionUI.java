package com.github.habiteria.vaadin.ui;

import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;

import static com.github.habiteria.vaadin.ui.Messages.message;

/**
 * @author Alex Ivchenko
 */
@SpringUI(path = "/login")
public class RecognitionUI extends BaseUI {
    @Override
    protected MenuBar initMenu() {
        MenuBar menu = new MenuBar();
        menu.addItem(message("label.signIn"), e -> onSignInClicked());
        menu.addItem(message("label.signUp"), e -> onSignUpClicked());
        return menu;
    }

    private void onSignUpClicked() {
        getNavigator().navigateTo("signUp");
    }

    private void onSignInClicked() {
        getNavigator().navigateTo("signIn");
    }

    @Override
    protected void defaultNavigate() {
        getNavigator().navigateTo("signIn");
    }
}
