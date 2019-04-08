package com.sj.springbootadmin.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TreeNodeDto {
    private Integer id;
    private String label;
    private List<TreeNodeDto> children;
    private Boolean checked;
    private Boolean disabled;
    private Boolean isLeaf;
    private Integer orderID;
    private String url;

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNodeDto> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNodeDto> children) {
        this.children = children;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @JsonProperty(value="isLeaf")
    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
