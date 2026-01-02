package top.potat.spring.admin.component.delay;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import top.potat.spring.common.constant.CommonConstant;

import java.util.Objects;

/**
 * 延迟队列事件消费者
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DelayedReceiverConsumer {

    /**
     * 延迟事件
     */
    @Async(CommonConstant.BUSINESS_THREAD_POOL)
    @EventListener(DelayedMsgRes.class)
    public void delayEventReceiver(DelayedMsgRes delayedMsgRes){
        if(Objects.isNull(delayedMsgRes))
            return;
        log.info("延迟事件监听进入：{}", delayedMsgRes.getType());
        String json = delayedMsgRes.getJson();
        switch (delayedMsgRes.getType()){
            case "test":
                log.info("测试延迟事件");
                break;
            case "test2":
                log.info("测试延迟事件2");
                break;
        }

    }

}
