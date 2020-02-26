package com.github.m111q.homework7.service;

import com.github.m111q.homework7.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    long addCar(Car car);

    List<Car> getByDateRange(int startDate, int endDate);

    List<Car> getCarsByColor(String color);

    void removeCarFromList(long id);
}
