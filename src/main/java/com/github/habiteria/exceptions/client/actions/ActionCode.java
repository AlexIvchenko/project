package com.github.habiteria.exceptions.client.actions;

import com.github.habiteria.exceptions.client.Code;
import lombok.Getter;

/**
 * @author Alex Ivchenko
 */
@Getter
public class ActionCode implements Code {
    private final String type;
    private final String given;
    private final String when;

    public ActionCode(Class type, String given, String when) {
        this.type = type.getSimpleName();
        this.given = given;
        this.when = when;
    }

    @Override
    public String text() {
        return String.format("%s.%s.%s", type, given, when);
    }
}
