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
public class CharArrayCoderTest {
    @Rule
    public final CoderRule<CharArrayCoder> coderRule = new CoderRule<CharArrayCoder>() {
        @Override
        protected CharArrayCoder initCoder() {
            return new CharArrayCoder();
        }
    };

    @Test
    public void testSerializeCharArray() {
        char[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getCharArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeCharArray() {
        char[] expected = generateArrayAndFill();
        coderRule.bundle.putCharArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private char[] generateArrayAndFill() {
        char[] array = new char[300];
        Arrays.fill(array, 'x');
        return array;
    }
}