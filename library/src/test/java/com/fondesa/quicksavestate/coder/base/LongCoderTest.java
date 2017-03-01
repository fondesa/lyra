package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class LongCoderTest {
    @Rule
    public final CoderRule<LongCoder> coderRule = new CoderRule<>(LongCoder.class);

    @Test
    public void testSerializeLongPrimitive() {
        long expectedValue = 9L;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getLong(coderRule.randomKey));
    }

    @Test
    public void testSerializeLongObject() {
        Long expectedValue = 9L;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, ((Long) coderRule.bundle.getLong(coderRule.randomKey)));
    }

    @Test
    public void testDeserializeLongPrimitive() {
        long expectedValue = 9L;
        coderRule.bundle.putLong(coderRule.randomKey, expectedValue);
        assertEquals((Long) expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    @Test
    public void testDeserializeLongObject() {
        Long expectedValue = 9L;
        coderRule.bundle.putLong(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}