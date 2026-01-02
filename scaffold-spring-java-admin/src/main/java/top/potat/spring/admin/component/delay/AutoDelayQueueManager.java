package top.potat.spring.admin.component.delay;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.potat.spring.common.utils.time.DateUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 自定义延时任务管理器
 */
@Component
@Order
public class AutoDelayQueueManager implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(AutoDelayQueueManager.class);
    // 创建一个包含 1 个线程的线程池
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();
    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(this::executeThread);
    }

    /**
     * 加入到延时队列中
     */
    public void put(DelayedElement task) {
        logger.info("加入延时任务：{},延时到时间:{}", task, DateUtils.getDayTime( new Date(task.getDelayUntil())));
        delayQueue.put(task);
    }

    /**
     * 取消延时任务
     */
    public boolean remove(DelayedElement task) {
        //logger.info("取消延时任务：{}", task);
        return delayQueue.remove(task);
    }



    /**
     * 延时任务执行线程
     */
    private void executeThread() {
        while (true) {
            try {
                DelayedElement task = delayQueue.take();
                processTask(task);
            } catch (Exception e) {
                continue;
            }
        }
    }

    /**
     * 内部执行延时任务
     */
    private void processTask(DelayedElement task) {
        if (task == null) return;
        try {
            publisher.publishEvent(task.getDelayedMsgRes());
        } catch (Exception e) {
            logger.error("任务信息:{},延时任务执行失败：",task.getDelayedMsgRes(), e);
        }

    }

    @PreDestroy
    public void saveTasksOnShutdown(){
        logger.info("延时任务 结束进程");
        int size = delayQueue.size();
        logger.info("延时任务 结束进程，当前任务还有 {} 个",size);
        // 进行序列化操作 存储未完成的任务
        // 关闭线程池
        executorService.shutdown();
    }

    @PostConstruct
    public void init(){
        logger.info("延时任务 启动");
        // 读取未完成的任务
        // 注意如果用json方式会执行DelayedElement里的全参构造方法 注意!!!
    }

}
