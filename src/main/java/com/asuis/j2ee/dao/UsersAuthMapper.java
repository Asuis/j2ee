package com.asuis.j2ee.dao;

import com.asuis.j2ee.model.UsersAuth;

public interface UsersAuthMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_auth
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_auth
     *
     * @mbg.generated
     */
    int insert(UsersAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_auth
     *
     * @mbg.generated
     */
    int insertSelective(UsersAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_auth
     *
     * @mbg.generated
     */
    UsersAuth selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_auth
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UsersAuth record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table users_auth
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UsersAuth record);
}