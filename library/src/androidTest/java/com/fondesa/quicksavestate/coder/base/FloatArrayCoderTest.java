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
public class FloatArrayCoderTest {
    @Rule
    public final CoderRule<FloatArrayCoder> coderRule = new CoderRule<FloatArrayCoder>() {
        @Override
        protected FloatArrayCoder initCoder() {
            return new FloatArrayCoder();
        }
    };

    @Test
    public void testSerializeFloatArray() {
        float[] expected = generateArrayAndFill();
        coderRule.coder.serialize(coderRule.bundle, COMMON_KEY, expected);
        assertEquals(expected, coderRule.bundle.getFloatArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeFloatArray() {
        float[] expected = generateArrayAndFill();
        coderRule.bundle.putFloatArray(COMMON_KEY, expected);
        assertEquals(expected, coderRule.coder.deserialize(coderRule.bundle, COMMON_KEY));
    }

    private float[] generateArrayAndFill() {
        float[] array = new float[300];
        Arrays.fill(array, 9.0f);
        return array;
    }
}