package com.xmx.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MonitorHotSwap implements Runnable {

    private String className;

    private Class hotClazz = null;

    private long millis;

    public MonitorHotSwap(String className, long millis) {
        this.className = className;
        this.millis = millis;
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.load();
                callMethod();
                this.reload(millis);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callMethod() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        System.out.println(hotClazz.getClassLoader());
        Object hot = hotClazz.newInstance();
        Method m = hotClazz.getMethod("say");
        //打印出相关信息
        m.invoke(hot, null);
    }

    private void reload(long millis) throws ClassNotFoundException, InterruptedException {
        Thread.sleep(millis);
        load();
    }

    private void load() throws ClassNotFoundException {
        hotClazz = HotSwapURLClassLoader.getClassLoader().loadClass(className);
    }


}