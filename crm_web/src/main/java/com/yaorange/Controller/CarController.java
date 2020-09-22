package com.yaorange.Controller;

import com.yaorange.entity.Car;
import com.yaorange.entity.House;
import com.yaorange.service.CarService;
import com.yaorange.service.HouseService;
import com.yaorange.utils.Pagination;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassNamehouseController
 * @Descripiotn //TODO
 * @Author luokun
 * @Date 2020/8/23 21:08
 * @Version 1.0
 **/
@RestController
@RequestMapping("/car")
public class CarController {
    @Resource(name = "carService")
    private CarService carService;

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestBody Integer[] ids) {
        carService.deleteCar(ids);
        return "ok";
    }

    @GetMapping("/page/{id}/{current}/{pageSize}")
    public Pagination page(@PathVariable Integer id, @PathVariable Integer current, @PathVariable Integer pageSize) {
        Pagination pagination = carService.page(id, current, pageSize);
        return pagination;
    }


    @PostMapping("/addCar")
    public String addCar(@RequestBody Car car) {
        carService.addCar(car);
        return "ok";
    }

    @PostMapping("/updateCar")
    public String updateCar(@RequestBody Car car) {
        carService.updateCar(car);
        return "ok";
    }
}
