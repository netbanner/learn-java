package com.example.dao;

import com.example.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeckillDao {

    /**
     * 减少库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);


    Seckill queryById(@Param("seckillId") long seckillId);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
}
