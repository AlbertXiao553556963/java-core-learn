package com.xmx.bit;

import java.util.BitSet;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-18 09:33
 **/
public class BitFilter {
    public static void main(String[] args) {
        String a = "201/453/710430700390001";
        BitSet bitSet = new BitSet(Integer.MAX_VALUE - 1);
        System.out.println(bitSet.length());
        System.out.println(bitSet.get(a.hashCode()));
        bitSet.set(a.hashCode());
        System.out.println(bitSet.get(a.hashCode()));
    }
}
