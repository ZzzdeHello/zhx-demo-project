package annotation.demo;

import java.lang.annotation.*;


public class Car {
    String name;
    int size;

    public Car() {
    }

    public Car(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}