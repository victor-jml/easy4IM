package pers.enoch.im.api.netty.task.execute;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author yang.zhao
 * @Date 2020/12/15 16:46
 * @Version 1.0
 * @Description 线程池执行业务逻辑
 **/
public class TaskExecute {
    private static final ExecutorService receiveMsgPool = new ThreadPoolExecutor(2, 10, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.DiscardPolicy());

    public static void execute(Runnable task) {
        receiveMsgPool.execute(task);
    }
}
