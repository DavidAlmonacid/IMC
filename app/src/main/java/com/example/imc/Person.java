package com.example.imc;

import java.io.Serializable;

public class Person implements Serializable {
    private double weight;
    private double height;
    private int age;
    private String gender;

    public Person(double weight, double height, int age, String gender) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
