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
public class ByteCoderTest {
    @Rule
    public final CoderRule<ByteCoder> mCoderRule = new CoderRule<>(ByteCoder.class);

    @Test
    public void testSerializeBytePrimitive() {
        byte expectedValue = 2;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.bundle().getByte(mCoderRule.randomKey()));
    }

    @Test
    public void testSerializeByteObject() {
        Byte expectedValue = 2;
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, ((Byte) mCoderRule.bundle().getByte(mCoderRule.randomKey())));
    }

    @Test
    public void testDeserializeBytePrimitive() {
        byte expectedValue = 2;
        mCoderRule.bundle().putByte(mCoderRule.randomKey(), expectedValue);
        assertEquals((Byte) expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeByteObject() {
        Byte expectedValue = 2;
        mCoderRule.bundle().putByte(mCoderRule.randomKey(), expectedValue);
        assertEquals(expectedValue, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }
}