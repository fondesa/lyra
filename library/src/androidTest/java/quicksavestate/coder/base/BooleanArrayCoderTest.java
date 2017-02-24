package quicksavestate.coder.base;

import android.os.Bundle;

import com.fondesa.quicksavestate.coder.base.BooleanArrayCoder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by antoniolig on 24/02/17.
 */
public class BooleanArrayCoderTest {
    private static final String COMMON_KEY = "x";

    private BooleanArrayCoder coder;
    private Bundle bundle;

    @Before
    public void initCoder() {
        coder = new BooleanArrayCoder();
        bundle = new Bundle();
    }

    @After
    public void releaseCoder() {
        coder = null;
    }

    @Test
    public void testSerializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        coder.serialize(bundle, COMMON_KEY, expected);
        assertEquals(expected, bundle.getBooleanArray(COMMON_KEY));
    }

    @Test
    public void testDeserializeBooleanArray() {
        boolean[] expected = generateArrayAndFill();
        bundle.putBooleanArray(COMMON_KEY, expected);
        assertEquals(expected, coder.deserialize(bundle, COMMON_KEY));
    }

    private boolean[] generateArrayAndFill() {
        boolean[] array = new boolean[300];
        Arrays.fill(array, true);
        return array;
    }
}