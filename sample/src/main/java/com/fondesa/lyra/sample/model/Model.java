package com.fondesa.lyra.sample.model;

import java.io.Serializable;

public class Model implements Serializable {
    private static final long serialVersionUID = 83186996445369715L;

    public int id;
    public String value;

    public Model(int id, String value) {
        this.id = id;
        this.value = value;
    }
}