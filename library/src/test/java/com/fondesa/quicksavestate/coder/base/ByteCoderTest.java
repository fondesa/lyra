package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.testhelper.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class ByteCoderTest {
    @Rule
    public final CoderRule<ByteCoder> coderRule = new CoderRule<>(ByteCoder.class);

    @Test
    public void testSerializeBytePrimitive() {
        byte expectedValue = 2;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getByte(coderRule.randomKey));
    }

    @Test
    public void testSerializeByteObject() {
        Byte expectedValue = 2;
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, ((Byte) coderRule.bundle.getByte(coderRule.randomKey)));
    }

    @Test
    public void testDeserializeBytePrimitive() {
        byte expectedValue = 2;
        coderRule.bundle.putByte(coderRule.randomKey, expectedValue);
        assertEquals((Byte) expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    @Test
    public void testDeserializeByteObject() {
        Byte expectedValue = 2;
        coderRule.bundle.putByte(coderRule.randomKey, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }
}