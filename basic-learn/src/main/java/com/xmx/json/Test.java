package com.xmx.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-10 10:36
 **/
public class Test {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key","value");
        System.out.println(jsonObject.toJSONString());
    }
}
