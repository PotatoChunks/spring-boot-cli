package top.potat.spring.admin.component.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.potat.spring.common.constant.CommonConstant;

/**
 * 事件监听处理 消费者
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventReceiverConsumer {

    /**
     * 普通事件
     */
    @EventListener
    public void eventReceiver(EventReq eventReq){
        log.debug("事件监听进入");
        if (eventReq == null) return;
        log.debug("接收到事件：{}", eventReq.getEventType());
        String json = eventReq.getJson();
        // 根据事件类型做业务处理
        switch (eventReq.getEventType()){
            case "test":
                log.info("测试事件");
                break;
            case "test1":
                log.info("测试事件1");
                break;
        }
    }


    /**
     * 异步事件
     */
    @Async(CommonConstant.BUSINESS_THREAD_POOL)
    @EventListener
    public void asyncEventReceiver(EventAsyncReq eventReq){
        log.debug("异步事件监听进入");
        if (eventReq == null) return;
        log.info("接收到异步事件：{}", eventReq.getEventType());
        String json = eventReq.getJson();
        // 根据事件类型做业务处理
        switch (eventReq.getEventType()){
            case "test":
                log.info("测试事件");
                break;
            case "test2":
                log.info("测试事件2");
                break;
        }

    }

}
