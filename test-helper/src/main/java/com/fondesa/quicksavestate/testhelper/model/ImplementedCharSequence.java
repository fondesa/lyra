package com.fondesa.quicksavestate.testhelper.model;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ImplementedCharSequence implements CharSequence {
    @Override
    public int length() {
        return 5;
    }

    @Override
    public char charAt(int index) {
        return 'x';
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return "test";
    }
}