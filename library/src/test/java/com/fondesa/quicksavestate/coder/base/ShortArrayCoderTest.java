package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.testhelper.rule.CoderRule;

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
public class ShortArrayCoderTest {
    @Rule
    public final CoderRule<ShortArrayCoder> coderRule = new CoderRule<>(ShortArrayCoder.class);

    @Test
    public void testSerializeShortArray() {
        short[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getShortArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeShortArray() {
        short[] expected = generateArrayAndFill();
        coderRule.bundle.putShortArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private short[] generateArrayAndFill() {
        short[] array = new short[300];
        Arrays.fill(array, (short) 9);
        return array;
    }
}