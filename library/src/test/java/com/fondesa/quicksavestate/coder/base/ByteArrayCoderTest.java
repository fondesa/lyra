package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class ByteArrayCoderTest {
    @Rule
    public final CoderRule<ByteArrayCoder> coderRule = new CoderRule<>(ByteArrayCoder.class);

    @Test
    public void testSerializeByteArray() {
        byte[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getByteArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeByteArray() {
        byte[] expected = generateArrayAndFill();
        coderRule.bundle.putByteArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private byte[] generateArrayAndFill() {
        byte[] array = new byte[300];
        Arrays.fill(array, (byte) 2);
        return array;
    }
}