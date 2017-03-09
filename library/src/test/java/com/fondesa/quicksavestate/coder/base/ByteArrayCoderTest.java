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
    public final CoderRule<ByteArrayCoder> mCoderRule = new CoderRule<>(ByteArrayCoder.class);

    @Test
    public void testSerializeByteArray() {
        byte[] expected = generateArrayAndFill();
        mCoderRule.coder().serialize(mCoderRule.bundle(), mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.bundle().getByteArray(mCoderRule.randomKey()));
    }

    @Test
    public void testDeserializeByteArray() {
        byte[] expected = generateArrayAndFill();
        mCoderRule.bundle().putByteArray(mCoderRule.randomKey(), expected);
        assertEquals(expected, mCoderRule.coder().deserialize(mCoderRule.bundle(), mCoderRule.randomKey()));
    }

    private byte[] generateArrayAndFill() {
        byte[] array = new byte[300];
        Arrays.fill(array, (byte) 2);
        return array;
    }
}