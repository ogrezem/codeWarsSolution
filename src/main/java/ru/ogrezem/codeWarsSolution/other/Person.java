package ru.ogrezem.codeWarsSolution.other;

public class Person {

    private String name;
    private BirthDay birthDay;
    private double height;
    private double weight;
    private Sex sex;

    public Person(String name, Sex sex, BirthDay birthDay) {
        this.name = name;
        this.birthDay = birthDay;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public BirthDay getBirthDay() {
        return birthDay;
    }
}
