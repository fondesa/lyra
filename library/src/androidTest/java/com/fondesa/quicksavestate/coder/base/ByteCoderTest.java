package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 22/02/17.
 */
public class ByteCoderTest {
    @Rule
    public final CoderRule<ByteCoder> coderRule = new CoderRule<ByteCoder>() {
        @Override
        protected ByteCoder initCoder() {
            return new ByteCoder();
        }
    };
    
    @Test
    public void testSerializeBytePrimitive() {
        byte expectedValue = 2;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.bundle.getByte(COMMON_KEY));
    }

    @Test
    public void testSerializeByteObject() {
        Byte expectedValue = 2;
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expectedValue);
        assertEquals(expectedValue, ((Byte) coderRule.bundle.getByte(COMMON_KEY)));
    }

    @Test
    public void testDeserializeBytePrimitive() {
        byte expectedValue = 2;
        coderRule.bundle.putByte(COMMON_KEY, expectedValue);
        assertEquals((Byte) expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    @Test
    public void testDeserializeByteObject() {
        Byte expectedValue = 2;
        coderRule.bundle.putByte(COMMON_KEY, expectedValue);
        assertEquals(expectedValue, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }
}