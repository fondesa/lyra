package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ByteArrayCoderTest {
    @Rule
    public final CoderRule<ByteArrayCoder> coderRule = new CoderRule<ByteArrayCoder>() {
        @Override
        protected ByteArrayCoder initCoder() {
            return new ByteArrayCoder();
        }
    };

    @Test
    public void testSerializeByteArray() {
        byte[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getByteArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeByteArray() {
        byte[] expected = generateArrayAndFill();
        coderRule.bundle.putByteArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private byte[] generateArrayAndFill() {
        byte[] array = new byte[300];
        Arrays.fill(array, (byte) 2);
        return array;
    }
}