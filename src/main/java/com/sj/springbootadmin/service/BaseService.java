package com.sj.springbootadmin.service;

import java.util.function.Consumer;

public class BaseService {

    public <T> ResponseData<T>  doInvoke(Consumer<ResponseData<T>>  action)
    {
        ResponseData result = new ResponseData<T>();
        try
        {
                action.accept(result);
        }
        catch (Exception ex)
        {
            result.setCode(500);
            result.setMsg(ex.getMessage());
        }
        return result;
    }

}
