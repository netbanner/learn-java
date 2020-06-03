package com.example.service;

import com.example.beans.Config;
import com.example.dao.ConfigDao;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);

    @Autowired
    ConfigDao configDao;

    public Collection<Config> getAll(){

        return  Lists.newArrayList(configDao.findAll());
    }

    public long add(Config config){

        config  = configDao.save(config);

        return  config.getId();
    }



}
