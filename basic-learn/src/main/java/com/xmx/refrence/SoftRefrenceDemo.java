package com.xmx.refrence;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-22 21:45
 **/
public class SoftRefrenceDemo {

    public static ReferenceQueue referenceQueue = new ReferenceQueue();

    public static void main(String[] args) {
        Object object = new Object();
        SoftReference sReference = new SoftReference(object, referenceQueue);
        object = null;
        System.out.println(sReference.get());
        System.gc();
        System.out.println(sReference.get());
    }
}
