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
public class StringArrayCoderTest {
    @Rule
    public final CoderRule<StringArrayCoder> coderRule = new CoderRule<>(StringArrayCoder.class);

    @Test
    public void testSerializeStringArray() {
        String[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getStringArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeStringArray() {
        String[] expected = generateArrayAndFill();
        coderRule.bundle.putStringArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private String[] generateArrayAndFill() {
        String[] array = new String[300];
        Arrays.fill(array, "test");
        return array;
    }
}