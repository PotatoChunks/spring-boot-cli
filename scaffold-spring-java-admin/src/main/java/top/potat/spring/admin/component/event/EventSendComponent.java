package top.potat.spring.admin.component.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 事件发送组件
 */
@Component
@RequiredArgsConstructor
public class EventSendComponent {
    private final ApplicationEventPublisher publisher;


    /**
     * 发送事件
     *
     * @param eventReq 事件
     */
    public void sendEvent(EventReq eventReq) {
        publisher.publishEvent(eventReq);
    }

    /**
     * 发送异步事件
     *
     * @param source 事件源
     */
    public void sendAsyncEvent(EventAsyncReq source) {
        publisher.publishEvent(source);
    }

    /**
     * 发送事件
     *
     * @param source 事件源
     * @param eventType 事件类型
     * @param json 事件内容
     */
    public void sendEvent(Object source, String eventType, String json) {
        publisher.publishEvent(new EventReq(source, eventType, json));
    }

}
