package com.github.habiteria.exceptions.client.fields;

import com.github.habiteria.exceptions.client.Code;
import lombok.Getter;

/**
 * @author Alex Ivchenko
 */
@Getter
public class FieldCode implements Code {
    private final String type;
    private final String field;
    private final String constraint;

    public FieldCode(Class type, String field, String constraint) {
        this.type = type.getSimpleName();
        this.field = field;
        this.constraint = constraint;
    }

    @Override
    public String text() {
        return String.format("%s.%s.%s", type, field, constraint);
    }
}
