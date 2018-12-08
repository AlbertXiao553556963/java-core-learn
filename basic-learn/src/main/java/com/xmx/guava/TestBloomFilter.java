package com.xmx.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-07 23:40
 **/
public class TestBloomFilter {

    static int sizeOfNumberSet = Integer.MAX_VALUE >> 4;

    static Random generator = new Random();

    public static void main(String[] args) {

        /**
         * 初始化一个检验String类型的布隆过滤器
         */
        BloomFilter<String> filterString = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-16")), sizeOfNumberSet);
        for(int i = 0; i < sizeOfNumberSet; i++) {
            String uuid = UUID.randomUUID().toString();
            filterString.put(uuid);
        }

        /**
         * 初始化一个检验int类型的布隆过滤器
         */
        BloomFilter<Integer> filterInt = BloomFilter.create(Funnels.integerFunnel(), sizeOfNumberSet);
        for(int i = 0; i < sizeOfNumberSet; i++) {
            int number = generator.nextInt();
            filterInt.put(number);
        }
    }
}
