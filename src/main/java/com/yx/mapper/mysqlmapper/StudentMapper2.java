package com.yx.mapper.mysqlmapper;

import com.yx.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper2 {
    List<Student> selectAll();
    void saveList(List<Student> list) ;
    void deleteAll(@Param("tableName") String tableName);


    List<Map<String,Object>> selectMap(@Param("tableName") String tableName);
}
