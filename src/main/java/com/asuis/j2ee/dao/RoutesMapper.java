package com.asuis.j2ee.dao;

import com.asuis.j2ee.model.Routes;

public interface RoutesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table routes
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table routes
     *
     * @mbg.generated
     */
    int insert(Routes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table routes
     *
     * @mbg.generated
     */
    int insertSelective(Routes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table routes
     *
     * @mbg.generated
     */
    Routes selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table routes
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Routes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table routes
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Routes record);
}