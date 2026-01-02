package top.potat.spring.admin.component.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class EventReq extends ApplicationEvent {
    //事件类型
    private String eventType;
    //事件参数
    private String json;

    public EventReq(Object source) {
        super(source);
    }
    public EventReq(Object source, String eventType) {
        super(source);
        this.eventType = eventType;
    }
    public EventReq(Object source, String eventType, String json) {
        super(source);
        this.eventType = eventType;
        this.json = json;
    }
}
