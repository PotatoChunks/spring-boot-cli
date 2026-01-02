package top.potat.spring.admin.component.time;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 定时器
 */
@Slf4j
@EnableScheduling
@Component
@RequiredArgsConstructor
public class TimeOutCancelTask {
    // 是否有服务正在运行定时器
    private boolean isRunning = false;

    @PreDestroy
    public void saveTasksOnShutdown(){
        log.debug("定时器停止");
//        if(!isRunning)
//            redisService.del(SERVICE_TIMER_START);
        isRunning = false;
    }

    @PostConstruct
    public void init(){
        log.debug("定时任务初始化");
//        if (redisService.hasKey(SERVICE_TIMER_START)) {
//            isRunning = true;
//        }else {
//            isRunning = false;
//            redisService.set(SERVICE_TIMER_START, "1");
//        }
    }


    //每10秒扫描一次
    @Scheduled(cron = "0/10 * * * * ?")
    public void cancelTimeOutOrder10() {
        if (isRunning) return;


    }

}
