package com.fondesa.quicksavestate.coder.base;

import com.fondesa.quicksavestate.coder.base.rule.CoderRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static com.fondesa.quicksavestate.coder.base.constants.Constants.COMMON_KEY;
import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
@RunWith(RobolectricTestRunner.class)
public class CharSequenceArrayCoderTest {
    @Rule
    public final CoderRule<CharSequenceArrayCoder> coderRule = new CoderRule<>(CharSequenceArrayCoder.class);

    @Test
    public void testSerializeCharSequenceArray() {
        CharSequence[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getCharSequenceArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeCharSequenceArray() {
        CharSequence[] expected = generateArrayAndFill();
        coderRule.bundle.putCharSequenceArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private CharSequence[] generateArrayAndFill() {
        CharSequence[] array = new CharSequence[300];
        Arrays.fill(array, "test");
        return array;
    }
}