package com.yx.mapper.mysqlmapper;

import com.yx.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper2 {
    List<Student> selectAll();
    void saveList(List<Student> list) ;
    void deleteAll(@Param("tableName") String tableName);
}
