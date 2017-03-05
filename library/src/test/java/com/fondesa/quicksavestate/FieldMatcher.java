package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.lang.reflect.Field;

/**
 * Created by antoniolig on 05/03/17.
 */
public class FieldMatcher extends BaseMatcher<Field[]> {
    private String[] fieldsName;
    private boolean exactly;

    public static FieldMatcher containsNames(@NonNull String... fieldsName) {
        return new FieldMatcher(fieldsName, false);
    }

    public static FieldMatcher withNames(@NonNull String... fieldsName) {
        return new FieldMatcher(fieldsName, true);
    }

    private FieldMatcher(@NonNull String[] fieldsName, boolean exactly) {
        this.fieldsName = fieldsName;
        this.exactly = exactly;
    }

    @Override
    public boolean matches(Object item) {
        if (item == null || !(item instanceof Field[]))
            return false;

        Field[] fields = (Field[]) item;
        int contained = 0;
        for (String fieldName : fieldsName) {
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    contained++;
                    break;
                }
            }
        }

        if (exactly) {
            return contained == fields.length;
        } else {
            return contained == fieldsName.length;
        }
    }

    @Override
    public void describeTo(Description description) {
        /* empty */
    }
}