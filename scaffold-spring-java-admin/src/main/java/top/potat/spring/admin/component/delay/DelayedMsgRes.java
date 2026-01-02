package top.potat.spring.admin.component.delay;

import lombok.Data;
import top.potat.spring.common.utils.RandomUtils;

/**
 * 延迟消息封装参数
 */
@Data
public class DelayedMsgRes {
    // 唯一id
    private String uuid;
    // 分类
    private String type;
    // 数据
    private String json;

    /**
     * 延迟消息封装参数
     * @param type 事件分类
     * @param json  数据
     */
    public DelayedMsgRes(String type, String json){
        this.type = type;
        this.json = json;
        this.uuid = RandomUtils.getRandomNoLaw();
    }
    public DelayedMsgRes(String uuid, String type, String json){
        this.uuid = uuid;
        this.type = type;
        this.json = json;
    }
}
