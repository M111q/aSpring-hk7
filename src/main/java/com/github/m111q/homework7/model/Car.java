package com.github.m111q.homework7.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Car {

    private long id;
    private String mark;
    private String model;
    private String color;
    private int productionYear;

    public Car() {
    }

    public Car(long id, String mark, String model, String color, int productionYear) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public Car(String mark, String model, String color, int productionYear) {
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                productionYear == car.productionYear &&
                mark.equals(car.mark) &&
                model.equals(car.model) &&
                color.equals(car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, model, color, productionYear);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                '}';
    }

    public Map<String, Object> toMap() {
        Map<String, Object> values = new HashMap<>();
        values.put("mark", mark);
        values.put("model", model);
        values.put("color", color);
        values.put("production_year", productionYear);
        return values;
    }
}