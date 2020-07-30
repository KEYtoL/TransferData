package com.yx.service;

import com.yx.entity.Student;

import java.util.List;

/**
 * (Student)表服务接口
 *
 * @author makejava
 * @since 2020-07-29 15:29:18
 */
public interface StudentService {

    String insertTestData();

    String selectAll(String tableName);

    void insertNewData(List<Student> lists);
}