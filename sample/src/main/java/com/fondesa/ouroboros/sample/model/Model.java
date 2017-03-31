package com.fondesa.ouroboros.sample.model;

import java.io.Serializable;

/**
 * Created by antoniolig on 18/02/17.
 */
public class Model implements Serializable {
    private static final long serialVersionUID = 83186996445369715L;

    public int id;
    public String value;

    public Model(int id, String value) {
        this.id = id;
        this.value = value;
    }
}