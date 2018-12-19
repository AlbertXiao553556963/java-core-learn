package com.xmx.bit;

import java.math.BigInteger;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-16 21:04
 * 常用的几种位运算
 **/
public class BitTransfer {

    /**
     * @param decimalSource
     * @return String
     * @Description: 十进制转换成二进制
     */
    public static String decimalToBinary(int decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource)); // 转换成 BigInteger 类型，默认是十进制
        return bi.toString(2); // 参数 2 指定的是转化成二进制
    }

    /**
     * @param binarySource
     * @return int
     * @Description: 二进制转换成十进制
     */
    public static int binaryToDecimal(String binarySource) {
        BigInteger bi = new BigInteger(binarySource, 2);  // 转换为 BigInteger 类型，参数 2 指定的是二进制
        return Integer.parseInt(bi.toString());     // 默认转换成十进制
    }


    /**
     * @param num- 等待移位的十进制数, m- 向左移的位数
     * @return int- 移位后的十进制数
     * @Description: 向左移位
     */
    public static int leftShift(int num, int m) {
        return num << m;
    }

    /**
     * @param num- 等待移位的十进制数, m- 向右移的位数
     * @return int- 移位后的十进制数
     * @Description: 向右移位
     */
    public static int rightShift(int num, int m) {
        return num >> m;
    }


    /**
     * @param num1- 第一个数字，num2- 第二个数字
     * @return 二进制按位“或”的结果
     * @Description: 二进制按位“或”的操作
     */
    public static int or(int num1, int num2) {

        return (num1 | num2);

    }

    /**
     * @param num1- 第一个数字，num2- 第二个数字
     * @return 二进制按位“与”的结果
     * @Description: 二进制按位“与”的操作
     */
    public static int and(int num1, int num2) {

        return (num1 & num2);

    }

    /**
     * @param num1- 第一个数字，num2- 第二个数字
     * @return 二进制按位“异或”的结果
     * @Description: 二进制按位“异或”的操作
     */

    public static int xor(int num1, int num2) {

        return (num1 ^ num2);

    }

    public static void binaryToDecimal1(int n) {
        for (int i = 31; i >= 0; i--)
            System.out.print(n >>> i & 1);
        System.out.println();
    }

    public static void binaryToDecimal2(int n) {
        String result = Integer.toBinaryString(n);
        System.out.println(result);
    }

    public static void main(String[] args) {

        int a = -53;
        int b = 35;

        binaryToDecimal1(-3);
        binaryToDecimal2(-3);
        binaryToDecimal2(-3 >> 1);
        System.out.println(decimalToBinary(-3 >>> 1));

//        System.out.println(String.format(" 数字 %d(%s) 和数字 %d(%s) 的按位‘或’结果是 %d(%s)",
//                a, decimalToBinary(a), b, decimalToBinary(b), or(a, b), decimalToBinary(or(a, b)))); // 获取十进制数 53 和 35 的按位“或”
//
//        System.out.println(String.format(" 数字 %d(%s) 和数字 %d(%s) 的按位‘与’结果是 %d(%s)",
//                a, decimalToBinary(a), b, decimalToBinary(b), and(a, b), decimalToBinary(and(a, b))));  // 获取十进制数 53 和 35 的按位“与”
//
//        System.out.println(String.format(" 数字 %d(%s) 和数字 %d(%s) 的按位‘异或’结果是 %d(%s)",
//                a, decimalToBinary(a), a, decimalToBinary(a), xor(a, a), decimalToBinary(xor(a, a))));  // 获取十进制数 53 和 35 的按位“异或”

    }


}
