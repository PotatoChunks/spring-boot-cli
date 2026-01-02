package top.potat.spring.common.config.redis;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastJson2RedisSerializer<T> implements RedisSerializer<T> {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private final Class<T> clazz;

    public FastJson2RedisSerializer(Class<T> clazz){
        super();
        this.clazz = clazz;
    }


    @Override
    public byte[] serialize(T t) throws SerializationException {
        if(t == null)
            return new byte[0];
        return JSON.toJSONBytes(t,
                JSONWriter.Feature.WriteClassName);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }
}
