package com.sky.controller.admin;


import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置店铺的营业状态为：{}",status==1?"营业中":"打烊中");
        redisTemplate.opsForValue().set("SHOP STATUS",status);
        return Result.success();
    }

    @GetMapping("/status")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get("SHOP STATUS");
        log.info("获取到店铺的营业状态为：{}",status==1?"营业中":"打烊中");
        return Result.success(status);
    }
}
