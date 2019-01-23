package com.xmx.refrence;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-22 21:45
 **/
public class WeakRefrenceDemo {

    public static ReferenceQueue referenceQueue = new ReferenceQueue();

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Reference sReference = new WeakReference(object, referenceQueue);
        object = null;
        System.out.println(sReference.get());
        System.gc();
        System.out.println(sReference.get());
        System.out.println(sReference);


    }
}
