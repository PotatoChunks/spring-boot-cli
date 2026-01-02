package top.potat.spring.admin.component.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class EventAsyncReq extends ApplicationEvent {
    //事件类型
    private String eventType;
    //事件参数
    private String json;

    public EventAsyncReq(Object source) {
        super(source);
    }
    public EventAsyncReq(Object source, String eventType) {
        super(source);
        this.eventType = eventType;
    }
    public EventAsyncReq(Object source, String eventType, String json) {
        super(source);
        this.eventType = eventType;
        this.json = json;
    }
}
