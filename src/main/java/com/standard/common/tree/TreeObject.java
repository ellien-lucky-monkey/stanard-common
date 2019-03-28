package com.standard.common.tree;

import java.util.List;

/**
 * @author Jiangkui
 * @since 2019/02/22 09:11
 */
public interface TreeObject {
    Object getId();

    void setId(Object id);

    Object getParentId();

    void setParentId(Object parentId);

    String getName();

    void setName(String name);

    List getChildren();

    void setChildren(List children);
}
