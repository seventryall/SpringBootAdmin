package com.sj.springbootadmin.service.dto;

public class MenuDto {
    private Integer id;
    private String name;
    private String url;
    private Integer orderID;
    private Integer parentID;
    private String parentName;
    private String remark;
    private String createTime;
    private MenuDto parentMenu;

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public MenuDto getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuDto parentMenu) {
        this.parentMenu = parentMenu;
    }
}
