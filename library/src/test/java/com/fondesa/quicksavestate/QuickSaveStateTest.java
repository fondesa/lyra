package com.fondesa.quicksavestate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by antoniolig on 22/02/17.
 */
public class QuickSaveStateTest {
    String[] strings;

    @Before
    public void setUp() {
        strings = new String[100000000];
        Arrays.fill(strings, "test");
    }

    @After
    public void tearDown() {
        strings = null;
    }

    @Test
    public void testForLoop() {
        for (int i = 0; i < strings.length; i++) {

        }
    }

    @Test
    public void testForEach() {
        for (String string : strings) {

        }
    }
}
