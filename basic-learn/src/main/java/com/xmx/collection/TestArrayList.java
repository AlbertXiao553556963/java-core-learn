package com.xmx.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-17 08:34
 **/
public class TestArrayList {
    public static void print(List list) {
        list.stream().forEach(it ->
            System.out.print(it + "-")
        );

    }
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");
        list.add("d");
        list.add("a");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }

        print(list);
    }
}
