package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;
import com.fondesa.quicksavestate.testmodel.PersonSerializable;

import org.junit.Rule;
import org.junit.Test;

import java.io.Serializable;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class SerializableCoderTest {
    @Rule
    public CoderRule<SerializableCoder> coderRule = new CoderRule<SerializableCoder>() {
        @Override
        protected SerializableCoder initCoder() {
            return new SerializableCoder();
        }
    };

    @Test
    public void testSerializeSerializable() {
        Serializable expectedValue = generateSerializable();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSerializable(COMMON_KEY));
    }

    @Test
    public void testDeserializeSerializable() {
        Serializable expectedValue = generateSerializable();
        coderRule.bundle.putSerializable(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
    
    private Serializable generateSerializable() {
        return new PersonSerializable("George", "White", 30);
    }
}