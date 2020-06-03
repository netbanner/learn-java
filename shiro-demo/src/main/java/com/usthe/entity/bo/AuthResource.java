package com.usthe.entity.bo;

import java.util.Date;

public class AuthResource {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.ID
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.CODE
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.NAME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.PARENT_ID
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.URI
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private String uri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.TYPE
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private Short type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.METHOD
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private String method;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.ICON
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private String icon;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.STATUS
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private Short status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.CREATE_TIME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column auth_resource.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.ID
     *
     * @return the value of auth_resource.ID
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.ID
     *
     * @param id the value for auth_resource.ID
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.CODE
     *
     * @return the value of auth_resource.CODE
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.CODE
     *
     * @param code the value for auth_resource.CODE
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.NAME
     *
     * @return the value of auth_resource.NAME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.NAME
     *
     * @param name the value for auth_resource.NAME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.PARENT_ID
     *
     * @return the value of auth_resource.PARENT_ID
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.PARENT_ID
     *
     * @param parentId the value for auth_resource.PARENT_ID
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.URI
     *
     * @return the value of auth_resource.URI
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public String getUri() {
        return uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.URI
     *
     * @param uri the value for auth_resource.URI
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.TYPE
     *
     * @return the value of auth_resource.TYPE
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public Short getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.TYPE
     *
     * @param type the value for auth_resource.TYPE
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.METHOD
     *
     * @return the value of auth_resource.METHOD
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public String getMethod() {
        return method;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.METHOD
     *
     * @param method the value for auth_resource.METHOD
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.ICON
     *
     * @return the value of auth_resource.ICON
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.ICON
     *
     * @param icon the value for auth_resource.ICON
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.STATUS
     *
     * @return the value of auth_resource.STATUS
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public Short getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.STATUS
     *
     * @param status the value for auth_resource.STATUS
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.CREATE_TIME
     *
     * @return the value of auth_resource.CREATE_TIME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.CREATE_TIME
     *
     * @param createTime the value for auth_resource.CREATE_TIME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column auth_resource.UPDATE_TIME
     *
     * @return the value of auth_resource.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column auth_resource.UPDATE_TIME
     *
     * @param updateTime the value for auth_resource.UPDATE_TIME
     *
     * @mbg.generated Mon Apr 20 13:14:00 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}