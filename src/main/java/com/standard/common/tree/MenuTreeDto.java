package com.standard.common.tree;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiangkui
 * @since 2019/02/22 09:16
 */
@ApiModel(value = "菜单树对象")
public class MenuTreeDto implements Serializable, TreeObject {

//    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID", example = "1", required = true)
    private String id;
    @ApiModelProperty(value = "菜单名称", example = "菜单", required = true)
    private String menuName;
    @ApiModelProperty(value = "菜单类型", example = "1", required = true)
    private Short menuType;
    @ApiModelProperty(value = "菜单代码", example = "1", required = true)
    private String menuCode;
    @ApiModelProperty(value = "父节点ID", example = "2", required = true)
    private String parentId;
    @ApiModelProperty(value = "排序", example = "2", required = false)
    private Long sortNo;
    @ApiModelProperty(value = "展开状态", example = "1/0", required = true)
    private Short expand;
    @ApiModelProperty(value = "是否为叶子", example = "0/1", required = true)
    private Short isShow;
    @ApiModelProperty(value = "权限标识", example = "task.scheduled", required = true)
    private String permission;
    @ApiModelProperty(value = "备注", example = "备注", required = false)
    private String comt;
    @ApiModelProperty(value = "是否启用", example = "1/0", required = false)
    private Short enable;
    @ApiModelProperty(value = "节点图标CSS类名", example = "fa fas", required = false)
    private String iconcls;
    @ApiModelProperty(value = "请求地址", example = "app.syslog", required = false)
    private String request;
    @ApiModelProperty(value = "子菜单", example = "父节点", required = false)
    private List children;

    @Override
    public Object getId() {
        return this.id;
    }

    @Override
    public void setId(Object id) {
      this.id = (String) id;
    }

    @Override
    public void setParentId(Object parentId) {
        this.parentId = (String) parentId;
    }

    @Override
    public String getName() {
        return this.menuName;
    }

    @Override
    public void setName(String name) {
        this.menuName = name;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Short getMenuType() {
        return menuType;
    }

    public void setMenuType(Short menuType) {
        this.menuType = menuType;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getSortNo() {
        return sortNo;
    }

    public void setSortNo(Long sortNo) {
        this.sortNo = sortNo;
    }

    public Short getExpand() {
        return expand;
    }

    public void setExpand(Short expand) {
        this.expand = expand;
    }

    public Short getIsShow() {
        return isShow;
    }

    public void setIsShow(Short isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getComt() {
        return comt;
    }

    public void setComt(String comt) {
        this.comt = comt;
    }

    public Short getEnable() {
        return enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }

    public String getIconcls() {
        return iconcls;
    }

    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public List getChildren() {
        return children;
    }

    @Override
    public void setChildren(List children) {
        this.children = children;
    }
}
