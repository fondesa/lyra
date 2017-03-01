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
public class IntArrayCoderTest {
    @Rule
    public final CoderRule<IntArrayCoder> coderRule = new CoderRule<>(IntArrayCoder.class);

    @Test
    public void testSerializeIntArray() {
        int[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getIntArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeIntArray() {
        int[] expected = generateArrayAndFill();
        coderRule.bundle.putIntArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private int[] generateArrayAndFill() {
        int[] array = new int[300];
        Arrays.fill(array, 9);
        return array;
    }
}