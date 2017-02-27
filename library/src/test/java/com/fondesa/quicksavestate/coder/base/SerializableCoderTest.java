package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.testhelper.model.ImplementedSerializable;
import com.fondesa.quicksavestate.testhelper.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.Serializable;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class SerializableCoderTest {
    @Rule
    public final CoderRule<SerializableCoder> coderRule = new CoderRule<>(SerializableCoder.class);

    @Test
    public void testSerializeSerializable() {
        Serializable expectedValue = ImplementedSerializable.getDefault();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getSerializable(coderRule.randomKey));
    }

    @Test
    public void testDeserializeSerializable() {
        Serializable expectedValue = ImplementedSerializable.getDefault();
        coderRule.bundle.putSerializable(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}