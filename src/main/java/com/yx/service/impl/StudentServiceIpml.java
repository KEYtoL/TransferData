package com.yx.service.impl;

import com.yx.entity.Student;
import com.yx.mapper.mysqlmapper.StudentMapper2;
import com.yx.mapper.sqlservermapper.StudentMapper1;
import com.yx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/7/29
 */
@Service
public class StudentServiceIpml implements StudentService {


    @Autowired
    StudentMapper1 studentMapper1 ;
    @Autowired
    StudentMapper2 studentMapper2;
    @Override
    @Transactional()
    public String insertTestData() {
        try {
            List<Student> lists = new ArrayList<>();

            for (int i =0; i <10 ; i++) {
                Student student = new Student();
                student.setAge("age:"+i);
                student.setId("id:"+i);
                student.setName("name:"+i);
                lists.add(student);
            }
            studentMapper2.saveList(lists);
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @Override
    @Transactional
    public String selectAll(String tableName) throws RuntimeException{
            List<Student> lists = studentMapper2.selectAll();
            insertNewData(lists);
            studentMapper2.deleteAll(tableName);
            if (1==1){
                throw  new RuntimeException("123");
            }
            return "成功";
    }

    @Override
    public void insertNewData(List<Student> lists) {
        studentMapper1.saveList(lists);
    }

    @Override
    @Transactional
    public void selectAll2(String tableName) {
        List<Map<String, Object>> maps = studentMapper2.selectMap(tableName);
        studentMapper1.saveListMap(tableName,maps);
        studentMapper2.deleteAll(tableName);
    }
}
