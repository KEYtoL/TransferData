package com.yx.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.yx.service.StudentService;
import com.yx.service.StudentServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Proxy;

/**
 * (Student)表控制层
 *
 * @author makejava
 * @since 2020-07-29 15:29:18
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping("/insert")
    public String  insert(){
        StudentServiceProxy studentServiceProxy = new StudentServiceProxy(studentService);
        StudentService o = (StudentService) Proxy.newProxyInstance(StudentService.class.getClassLoader(), new Class[]{StudentService.class}, studentServiceProxy);
        return   o.insertTestData();
    }

    @RequestMapping("/transfer/{tableName}")
    public String  transfer(@PathVariable("tableName") String tableName){
        StudentServiceProxy studentServiceProxy = new StudentServiceProxy(studentService);
        StudentService o = (StudentService) Proxy.newProxyInstance(StudentService.class.getClassLoader(), new Class[]{StudentService.class}, studentServiceProxy);
        String s ="";
        try {
           s = o.selectAll(tableName);
        } catch (Exception e) {
            s="sssssss";
        }
        return  s;
    }
}