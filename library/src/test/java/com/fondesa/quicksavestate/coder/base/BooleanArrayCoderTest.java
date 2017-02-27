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
public class BooleanArrayCoderTest {
    @Rule
    public final CoderRule<BooleanArrayCoder> coderRule = new CoderRule<>(BooleanArrayCoder.class);

    @Test
    public void testSerializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getBooleanArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        coderRule.bundle.putBooleanArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private boolean[] generateArrayAndFill() {
        boolean[] array = new boolean[300];
        Arrays.fill(array, true);
        return array;
    }
}