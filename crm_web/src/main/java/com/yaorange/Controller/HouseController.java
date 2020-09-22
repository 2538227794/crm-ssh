package com.yaorange.Controller;

import com.yaorange.entity.House;
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
@RequestMapping("/house")
public class HouseController {
    @Resource(name = "houseService")
    private HouseService houseService;

    @PostMapping("/deleteHouse")
    public String deleteHouse(@RequestBody Integer[] ids) {
        houseService.deletehouse(ids);
        return "ok";
    }

    @GetMapping("/page/{id}/{current}/{pageSize}")
    public Pagination page(@PathVariable Integer id, @PathVariable Integer current, @PathVariable Integer pageSize) {
        Pagination pagination = houseService.page(id, current, pageSize);
        return pagination;
    }


    @PostMapping("/addHouse")
    public String addHouse(@RequestBody House house) {
        houseService.addHouse(house);
        return "ok";
    }

    @PostMapping("/updateHouse")
    public String updateHouse(@RequestBody House house) {
        houseService.updateHouse(house);
        return "ok";
    }
}
