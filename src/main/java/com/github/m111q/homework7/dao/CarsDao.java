package com.github.m111q.homework7.dao;

import com.github.m111q.homework7.model.Car;

import java.util.List;

public interface CarsDao {

    List<Car> findAll();

    long saveCarAndReturnId(Car car);

    List<Car> findByDateRange(int startDate, int endDate);

    List<Car> findByColor(String color);

    void removeById(long id);
}
