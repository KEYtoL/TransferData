package com.yx.mapper.sqlservermapper;

import com.yx.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper1 {
    int saveList(List<Student> list) ;
}
