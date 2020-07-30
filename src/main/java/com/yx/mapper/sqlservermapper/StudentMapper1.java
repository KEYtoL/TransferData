package com.yx.mapper.sqlservermapper;

import com.yx.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper1 {
    int saveList(List<Student> list) ;

    int saveListMap(@Param("tableName") String tableName,@Param("list") List<Map<String,Object>> list);
}
