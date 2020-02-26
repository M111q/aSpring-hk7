package com.github.m111q.homework7.api;

import com.github.m111q.homework7.model.Car;
import com.github.m111q.homework7.service.CarService;
import com.github.m111q.homework7.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cars")
public class CarApi {

    private CarService carService;

    public CarApi() {
    }

    @Autowired
    public CarApi(CarServiceImpl carServiceImpl) {
        this.carService = carServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List carList = carService.getAllCars();
        if (!carList.isEmpty()) {
            return new ResponseEntity<>(carList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @RequestMapping("/year")
    public ResponseEntity<List<Car>> getCarsByProductionYear(@RequestParam int fromYear, @RequestParam int toYear) {
        List carList = carService.getByDateRange(fromYear, toYear);
        if (!carList.isEmpty()) {
            return new ResponseEntity<>(carList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Long id = carService.addCar(car);
        car.setId(id);
        return new ResponseEntity(car, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@PathVariable long id) {
        carService.removeCarFromList(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping
    @RequestMapping("/color")
    public ResponseEntity<List<Car>> getCarsByColor(@RequestParam String color) {
        List<Car> searchedCars = carService.getCarsByColor(color);
        if (!searchedCars.isEmpty()) {
            return new ResponseEntity<>(searchedCars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
