package com.zwy.autotax.controller;

import com.zwy.autotax.dao.CityMapper;
import com.zwy.autotax.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author HUZHAOYANG
 * @Date 2019/7/21 11:20
 * @Version 1.0
 */
@RestController
public class TestController {

    @Autowired
    CityMapper cityMapper;

    @RequestMapping("/search")
    public City queryCity(@RequestParam Integer id){
        return cityMapper.selectById(id);
    }
}
