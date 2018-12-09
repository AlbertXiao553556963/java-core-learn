package com.xmx.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-08 11:52
 **/
public class TestCallable {

    public static Long getUserId() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 231l;
    }

    public static Long getCompanyId() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 100l;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Long start = null;
        Long companyId = null;
        Long userId = null;
        /**
         * 串行调度
         */
        start = System.currentTimeMillis();
        companyId = getCompanyId();
        userId = getUserId();
        System.out.println("串行耗时:" + (System.currentTimeMillis() - start));
        System.out.println(String.format("公司信息:%s,用户信息:%s", companyId, userId));

        /**
         * 并行调度
         */
        FutureTask<Long> userTask = new FutureTask(() -> getUserId());
        FutureTask<Long> companyTask = new FutureTask(() -> getCompanyId());
        start = System.currentTimeMillis();
        new Thread(userTask).start();
        new Thread(companyTask).start();
        companyId = companyTask.get();
        userId = userTask.get();
        System.out.println("\n并行耗时:" + (System.currentTimeMillis() - start));
        System.out.println(String.format("公司信息:%s,用户信息:%s \n", companyId, userId));

        /**
         * 自己实现的futureTask
         */
        MyFutureTask<Long> userMyTask = new MyFutureTask(() -> getUserId());
        MyFutureTask<Long> companyMyTask = new MyFutureTask(() -> getCompanyId());
        start = System.currentTimeMillis();
        new Thread(userMyTask).start();
        new Thread(companyMyTask).start();
        companyId = companyMyTask.get();
        userId = userMyTask.get();
        System.out.println("并行耗时:" + (System.currentTimeMillis() - start));
        System.out.println(String.format("公司信息:%s,用户信息:%s", companyId, userId));
    }
}
