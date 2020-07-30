package com.yx.service.impl;

import ch.qos.logback.classic.db.names.TableName;
import com.yx.entity.Student;
import com.yx.mapper.mysqlmapper.StudentMapper2;
import com.yx.mapper.sqlservermapper.StudentMapper1;
import com.yx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional(transactionManager="sqlserverTransactionManager")
    public String selectAll(String tableName) {
        try {
            List<Student> lists = studentMapper2.selectAll();
            insertNewData(lists);
            studentMapper2.deleteAll(tableName);
            if (1==1){
                throw  new RuntimeException("123");
            }
            return "成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "失败";
        }
    }

    @Override
    public void insertNewData(List<Student> lists) {
        studentMapper1.saveList(lists);
    }
}
