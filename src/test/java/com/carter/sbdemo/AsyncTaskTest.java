package com.carter.sbdemo;

import com.carter.sbdemo.async.AsyncTask;
import com.carter.sbdemo.async.ExecutorTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AsyncTaskTest {

    @Autowired
    private AsyncTask task;

    @Autowired
    private ExecutorTask exTask;

    @Test
    public void testAsync() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }


    @Test
    public void testExecutor() throws Exception {
        long start = System.currentTimeMillis();
        exTask.doTaskOne();
        exTask.doTaskTwo();
        exTask.doTaskThree();
        long end = System.currentTimeMillis();
        System.out.println("完成所有任务，耗时：" + (end - start) + "毫秒");
        Thread.currentThread().join();//表示获取当前正在运行的线程，join方法是阻塞当前调用线程，直到某线程完全执行才调用线程才继续执行

    }



}
