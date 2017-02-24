package com.fondesa.quicksavestate.testmodel;

import java.io.Serializable;

/**
 * Created by antoniolig on 24/02/17.
 */
public class PersonSerializable implements Serializable {
    private static final long serialVersionUID = -1315746751902301329L;

    public String name;
    public String surname;
    public int age;

    public PersonSerializable(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}