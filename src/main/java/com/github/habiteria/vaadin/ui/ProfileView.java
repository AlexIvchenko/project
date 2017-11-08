package com.github.habiteria.vaadin.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * @author Alex Ivchenko
 */
@SpringView(name = "profile")
public final class ProfileView extends VerticalLayout implements View {
    @PostConstruct
    public void initialize() {
        addComponent(new Label("Hello!"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
