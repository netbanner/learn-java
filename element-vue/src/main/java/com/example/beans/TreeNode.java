package com.example.beans;

import lombok.Data;

import java.util.List;

@Data
public class TreeNode {

    private long id;

    private String name;

    private String icon = "edit";

    private List<TreeNode> subnodes;

    public TreeNode(long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<TreeNode> getSubnodes() {
        return subnodes;
    }

    public void setSubnodes(List<TreeNode> subnodes) {
        this.subnodes = subnodes;
    }
}
