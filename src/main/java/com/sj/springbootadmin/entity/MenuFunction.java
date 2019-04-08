package com.sj.springbootadmin.entity;

public class MenuFunction extends  BaseEntity {
    private Integer menuID;
    private Integer functionID;

    public Integer getMenuID() {
        return menuID;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public Integer getFunctionID() {
        return functionID;
    }

    public void setFunctionID(Integer functionID) {
        this.functionID = functionID;
    }
}
