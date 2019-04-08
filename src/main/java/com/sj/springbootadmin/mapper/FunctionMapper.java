package com.sj.springbootadmin.mapper;

import com.sj.springbootadmin.entity.Function;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FunctionMapper {
    List<Function> getAllFunction();
}
