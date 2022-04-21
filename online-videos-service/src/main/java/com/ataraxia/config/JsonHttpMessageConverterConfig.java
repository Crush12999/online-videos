package com.ataraxia.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 00:54
 * @description 全局异常处理配置类
 */

@Configuration
public class JsonHttpMessageConverterConfig {

    @Bean
    @Primary
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // 设置时间格式
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 设置序列化相关配置
        fastJsonConfig.setSerializerFeatures(
                // 代表 JSON 数据需要格式化的输出
                SerializerFeature.PrettyFormat,
                // 如果输出的 JSON 数据里有的数据是null，需要让前端看到这个字段，这个参数会把空数据转化为空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                // 让Map的键值对升序排序
                SerializerFeature.MapSortField,
                // 禁用循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    public static void main(String[] args) {
        // 测试循环引用
        List<Object> list = new ArrayList<>();
        Object o = new Object();
        list.add(o);
        list.add(o);
        System.out.println(list.size());
        // FastJson在做Json数据构建的时候，如果发现前面有相关Object
        // 后面会直接把相关引用的地址或者引用内容相关的地址直接写到后面的数据里。
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect));
    }
}
