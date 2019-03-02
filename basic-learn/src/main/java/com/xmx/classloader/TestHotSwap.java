package com.xmx.classloader;

public class TestHotSwap {
    public static void main(String[] args) throws Exception {
        String className = "com.xmx.classloader.HelloWorld";
        long millis = 10000;
        MonitorHotSwap monitorHotSwap = new MonitorHotSwap(className, millis);
        //开启线程，如果class文件有修改，就热替换
        new Thread(monitorHotSwap).start();
    }
}