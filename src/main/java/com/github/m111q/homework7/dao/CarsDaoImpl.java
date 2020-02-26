package com.github.m111q.homework7.dao;

import com.github.m111q.homework7.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarsDaoImpl implements CarsDao {

    private static final String TABLE_NAME = "cars";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        return convertMapsToObjectsList(jdbcTemplate.queryForList(sql));
    }

    @Override
    public long saveCarAndReturnId(Car car) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(TABLE_NAME)
                .usingGeneratedKeyColumns("car_id");
        return simpleJdbcInsert.executeAndReturnKey(car.toMap()).longValue();
    }

    @Override
    public List<Car> findByDateRange(int startDate, int endDate) {
        //String sql = "SELECT * FROM " + TABLE_NAME + "WHERE production_year BETWEEN ? AND ? ORDER BY production_year";
        //List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, startDate, endDate);
        String sql = "SELECT * FROM cars WHERE production_year BETWEEN " + startDate + " AND " + endDate +
                " ORDER BY production_year";//TODO
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return convertMapsToObjectsList(maps);
    }

    @Override
    public List<Car> findByColor(String color) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE color = ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, color);
        return convertMapsToObjectsList(maps);
    }

    @Override
    public void removeById(long id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE car_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private List<Car> convertMapsToObjectsList(List<Map<String, Object>> maps) {
        List<Car> cars = new ArrayList<>();

        maps.stream().forEach(e -> cars.add(new Car(
                Long.parseLong(String.valueOf(e.get("car_id"))),
                String.valueOf(e.get("mark")),
                String.valueOf(e.get("model")),
                String.valueOf(e.get("color")),
                Integer.valueOf(String.valueOf(e.get("production_year")))
        )));
        return cars;
    }
}
