package com.moon.suanfa.hash;

public class User {

    public int age;


    public User(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        int age = ((User) o).age;
        return this.age == age;
    }

    @Override
    public int hashCode() {
        return age / 10;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                '}';
    }
}
