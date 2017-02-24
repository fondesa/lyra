package com.fondesa.quicksavestate.testmodel;

import java.io.Serializable;

/**
 * Created by antoniolig on 24/02/17.
 */
public class ImplementedSerializable implements Serializable {
    private static final long serialVersionUID = -1315746751902301329L;

    public String firstParam;
    public String secondParam;
    public int thirdParam;

    public static ImplementedSerializable getDefault() {
        return new ImplementedSerializable("first", "second", 3);
    }

    public ImplementedSerializable(String firstParam, String secondParam, int thirdParam) {
        this.firstParam = firstParam;
        this.secondParam = secondParam;
        this.thirdParam = thirdParam;
    }
}