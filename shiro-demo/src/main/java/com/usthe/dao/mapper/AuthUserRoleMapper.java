package com.usthe.dao.mapper;

import com.usthe.entity.bo.AuthUserRole;

public interface AuthUserRoleMapper {


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_user_role
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_user_role
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int insert(AuthUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_user_role
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int insertSelective(AuthUserRole record);



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_user_role
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    AuthUserRole selectByPrimaryKey(Integer id);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_user_role
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int updateByPrimaryKeySelective(AuthUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table auth_user_role
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    int updateByPrimaryKey(AuthUserRole record);
}