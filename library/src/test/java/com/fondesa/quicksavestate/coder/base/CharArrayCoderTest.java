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
public class CharArrayCoderTest {
    @Rule
    public final CoderRule<CharArrayCoder> coderRule = new CoderRule<>(CharArrayCoder.class);

    @Test
    public void testSerializeCharArray() {
        char[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, coderRule.randomKey, expected);
        assertEquals(expected, coderRule.bundle.getCharArray(coderRule.randomKey));
    }

    @Test
    public void testDeserializeCharArray() {
        char[] expected = generateArrayAndFill();
        coderRule.bundle.putCharArray(coderRule.randomKey, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, coderRule.randomKey));
    }

    private char[] generateArrayAndFill() {
        char[] array = new char[300];
        Arrays.fill(array, 'x');
        return array;
    }
}