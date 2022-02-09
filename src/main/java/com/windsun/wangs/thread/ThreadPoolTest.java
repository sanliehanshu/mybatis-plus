package com.windsun.wangs.thread;

import java.util.concurrent.*;

/**
 * @Author：wangsheng
 * @Description：自定义线程池
 * @Date：2022/2/9 20:49
 */
public class ThreadPoolTest {


    public static void main(String[] args) {
        ExecutorService poolExecutor = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                /**
                 * 默认策略，直接抛出异常
                 * Exception in thread "main" java.util.concurrent.RejectedExecutionException: Task com.windsun.wangs.thread.ThreadPoolTest$$Lambda$1/1567581361@1376c05c rejected from java.util.concurrent.ThreadPoolExecutor@51521cc1[Running, pool size = 5, active threads = 2, queued tasks = 0, completed tasks = 7]
                 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
                 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
                 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
                 * 	at com.windsun.wangs.thread.ThreadPoolTest.main(ThreadPoolTest.java:27)
                 */
                //new ThreadPoolExecutor.AbortPolicy()

                /**
                 * 用调用者所在的线程来执行任务
                 */
                //new ThreadPoolExecutor.CallerRunsPolicy()

                /**
                 * 如果执行程序尚未关闭，丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
                 */
                //new ThreadPoolExecutor.DiscardOldestPolicy()

                /**
                 * 由调用线程处理该任务。即直接在 execute 方法的调用线程中运行被拒绝的任务；
                 */
                new ThreadPoolExecutor.DiscardPolicy()
        );


        try {
            for (int i = 0; i < 20; i++) {
                poolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }
    }
}
