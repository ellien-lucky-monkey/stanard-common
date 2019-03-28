package com.standard.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jiangkui
 * @since 2019/02/22 09:46
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TreeUtilTests {

    @Test
    public void tree() throws IOException {
        String path = getClass().getClassLoader().getResource("menu.json").toString();
        path = path.replace("\\", "/");
        if (path.contains(":")){
            path = path.replace("file:/", "");
        }
        String input = FileUtils.readFileToString(new File(path), "UTF-8");
        List menuTreeDtos = JSON.parseArray(input,MenuTreeDto.class);
        if (CollectionUtils.isEmpty(menuTreeDtos)) {
            return;
        }
        TreeUtils treeUtils = new TreeUtils();
        treeUtils.getChildTreeObjects(menuTreeDtos,1);
        System.out.println("");
    }
}
