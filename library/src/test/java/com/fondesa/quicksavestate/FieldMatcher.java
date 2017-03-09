package com.fondesa.quicksavestate;

import android.support.annotation.NonNull;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.lang.reflect.Field;

/**
 * Created by antoniolig on 05/03/17.
 */
public class FieldMatcher extends BaseMatcher<Field[]> {
    private String[] mFieldsName;
    private boolean mMatchExactly;

    public static FieldMatcher containsNames(@NonNull String... fieldsName) {
        return new FieldMatcher(fieldsName, false);
    }

    public static FieldMatcher withNames(@NonNull String... fieldsName) {
        return new FieldMatcher(fieldsName, true);
    }

    private FieldMatcher(@NonNull String[] fieldsName, boolean matchExactly) {
        mFieldsName = fieldsName;
        mMatchExactly = matchExactly;
    }

    @Override
    public boolean matches(Object item) {
        if (item == null || !(item instanceof Field[]))
            return false;

        Field[] fields = (Field[]) item;
        int contained = 0;
        for (String fieldName : mFieldsName) {
            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    contained++;
                    break;
                }
            }
        }

        if (mMatchExactly) {
            return contained == fields.length;
        } else {
            return contained == mFieldsName.length;
        }
    }

    @Override
    public void describeTo(Description description) {
        /* empty */
    }
}