package com.github.m111q.homework7.service;

import com.github.m111q.homework7.dao.CarsDao;
import com.github.m111q.homework7.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    private CarsDao carsDao;

    @Autowired
    public CarServiceImpl(CarsDao carsDao) {
        this.carsDao = carsDao;

        logger.info("Start CarService");
    }


    @Override
    public List<Car> getAllCars() {
        return carsDao.findAll();
    }

    @Override
    public long addCar(Car car) {
        return carsDao.saveCarAndReturnId(car);
    }


    @Override
    public List<Car> getByDateRange(int startDate, int endDate) {
        return carsDao.findByDateRange(startDate, endDate);
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return carsDao.findByColor(color);
    }

    @Override
    public void removeCarFromList(long id) {
        carsDao.removeById(id);
    }

}