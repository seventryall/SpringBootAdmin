package com.sj.springbootadmin.service.contract;

import com.sj.springbootadmin.service.ResponseData;
import com.sj.springbootadmin.service.dto.FunctionDto;


import java.util.List;

public interface FunctionService {

    ResponseData<List<FunctionDto>> getFunctionList();

}
