package com.usthe.dao.mapper;

import com.usthe.entity.bo.AuthOperationLog;

public interface AuthOperationLogMapper {

    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_operation_log
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int insert(AuthOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_operation_log
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int insertSelective(AuthOperationLog record);



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_operation_log
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    AuthOperationLog selectByPrimaryKey(Integer id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_operation_log
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int updateByPrimaryKeySelective(AuthOperationLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_operation_log
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int updateByPrimaryKey(AuthOperationLog record);
}