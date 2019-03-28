package com.standard.common.utils;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author Jiangkui
 * @since 2019/02/22 09:11
 */
public class TreeUtils {

    private List<TreeObject> returnList = new ArrayList<TreeObject>();

    /**
     * 判断两个父ID是否相同
     *
     * @param p1
     * @param p2
     * @return
     */
    private boolean isEqualsParentId(Object p1, Object p2) {
        if (p1 != null && p1 != null) {
            return p1.equals(p2);
        } else if (p1 == null && p2 == null) {
            return true;
        } else if (p1 == null && p2 != null) {
            if ("".equals(p2.toString())) {
                return true;
            } else {
                return false;
            }
        } else if (p1 != null && p2 == null) {
            if ("".equals(p1.toString())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 根据父节点的ID获取所有子节点，该方法顶级节点必须为空
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<TreeObject> getChildTreeObjects(List<TreeObject> list, Object parentId) {
        List<TreeObject> returnList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext(); ) {
                TreeObject t = iterator.next();
                // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
                if (isEqualsParentId(t.getParentId(), parentId)) {
                    recursionFn(list, t);
                    returnList.add(t);
                }
            }
        }
        return returnList;
    }

    /**
     * 根据父节点的ID获取所有子节点，该方法顶级节点可以不为空,非树直接返回
     *
     * @param list 分类表
     * @return String
     */
    public List<TreeObject> getChildTreeObjects(List<TreeObject> list) {
        if (list != null && list.size() > 0) {
            List<TreeObject> topList = new ArrayList<>();
            List<TreeObject> subList = new ArrayList<>();

            Map<String, String> idMap = new HashMap<>();

            for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext(); ) {
                //归并所有list的id集合
                TreeObject t = iterator.next();
                idMap.put(t.getId().toString(), t.getId().toString());
            }

            for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext(); ) {
                //获取最顶级的list
                TreeObject t = iterator.next();
                if (isTop(t)) {
                    topList.add(t);
                } else {
                    String id = idMap.get(t.getParentId().toString());
                    if (StringUtils.isEmpty(id)) {
                        topList.add(t);
                    } else {
                        subList.add(t);
                    }
                }
            }
            if (CollectionUtils.isNotEmpty(topList) && CollectionUtils.isNotEmpty(subList)) {
                List<TreeObject> resultList = new ArrayList<>();
                for (TreeObject t : topList) {
                    //将儿子级别的list归并到顶级中
                    List<TreeObject> subOneList = new ArrayList<>();

                    for (TreeObject sub : subList) {
                        // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
                        if (isEqualsParentId(sub.getParentId(), t.getId())) {
                            recursionFn(subList, sub);
                            subOneList.add(sub);
                        }
                    }
                    t.setChildren(subOneList);


                    resultList.add(t);
                }
                return resultList;
            } else {
                return list;
            }
        }
        return list;
    }


    private boolean isTop(TreeObject object) {
        return object.getParentId() == null || StringUtils.isEmpty(object.getParentId().toString()) || object.getParentId().equals(0);
    }
    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<TreeObject> list, TreeObject t) {
        // 得到子节点列表
        List<TreeObject> childList = getChildList(list, t);
        t.setChildren(childList);
        for (TreeObject tChild : childList) {
            // 判断是否有子节点
            if (hasChild(list, tChild)) {
                //returnList.add(TreeObject);
                Iterator<TreeObject> it = childList.iterator();
                while (it.hasNext()) {
                    TreeObject n = (TreeObject) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<TreeObject> getChildList(List<TreeObject> list, TreeObject t) {

        List<TreeObject> tlist = new ArrayList<TreeObject>();
        Iterator<TreeObject> it = list.iterator();
        while (it.hasNext()) {
            TreeObject n = it.next();
            if (isEqualsParentId(n.getParentId(), t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }


    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @param prefix   子节点前缀
     */
    public List<TreeObject> getChildTreeObjects(List<TreeObject> list, Object parentId, String prefix) {
        if (list == null) {
            return null;
        }
        for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext(); ) {
            TreeObject node = (TreeObject) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (isEqualsParentId(node.getParentId(), parentId)) {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*if (node.getParentId()==0) {
                recursionFn(list, node);
            }*/
        }
        return returnList;
    }

    private void recursionFn(List<TreeObject> list, TreeObject node, String p) {
        // 得到子节点列表
        List<TreeObject> childList = getChildList(list, node);
        // 判断是否有子节点
        if (hasChild(list, node)) {
            returnList.add(node);
            Iterator<TreeObject> it = childList.iterator();
            while (it.hasNext()) {
                TreeObject n = (TreeObject) it.next();
                n.setName(p + n.getName());
                recursionFn(list, n, p + p);
            }
        } else {
            returnList.add(node);
        }
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<TreeObject> list, TreeObject t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
