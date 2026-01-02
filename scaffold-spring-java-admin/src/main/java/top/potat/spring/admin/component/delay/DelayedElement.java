package top.potat.spring.admin.component.delay;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列元素
 */
@Data
public class DelayedElement implements Delayed {
    /**
     * 延迟时长
     */
    private long delayUntil; // 过期时间戳
    /**
     * 信息存储对象
     */
    private DelayedMsgRes delayedMsgRes;//数据

    /**
     * 构造延时任务
     * @param delayedMsgRes      业务数据
     * @param delayInSeconds    任务延时时间（秒）
     */
    public static DelayedElement init(DelayedMsgRes delayedMsgRes, long delayInSeconds){
        DelayedElement delayedElement = new DelayedElement();
        delayedElement.setDelayedMsgRes(delayedMsgRes);
        delayedElement.setDelayUntil( System.currentTimeMillis() + delayInSeconds * 1000 );
        return delayedElement;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.delayUntil - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        long delta = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return (int) delta;
    }
}
