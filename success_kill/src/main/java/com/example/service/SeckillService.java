package com.example.service;

import com.example.dto.Exposer;
import com.example.dto.SeckillExecution;
import com.example.entity.Seckill;
import com.example.exception.RepeatKillException;
import com.example.exception.SeckillCloseException;
import com.example.exception.SeckillException;

import java.util.List;

public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckill);

    Exposer exportSeckillUrl(long seckillId);


    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;


    SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
