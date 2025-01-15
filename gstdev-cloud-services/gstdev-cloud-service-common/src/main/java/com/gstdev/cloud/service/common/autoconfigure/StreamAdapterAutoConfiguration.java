//package com.gstdev.cloud.oauth2.resource.server.autoconfigure;
//
//import jakarta.annotation.PostConstruct;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.context.annotation.Bean;
//
///**
// * <p>Description: Stream 消息发送适配器配置 </p>
// *
// */
//@AutoConfiguration(after = FunctionConfiguration.class)
//@ConditionalOnBean(StreamBridge.class)
//public class StreamAdapterAutoConfiguration {
//
//    private static final Logger log = LoggerFactory.getLogger(MessageKafkaAutoConfiguration.class);
//
//    @PostConstruct
//    public void postConstruct() {
//        log.info("[GstDev Cloud] |- Module [Stream Adapter] Auto Configure.");
//    }
//
//    @Bean
//    public StreamMessageSendingAdapter streamMessageSendingAdapter(StreamBridge streamBridge) {
//        StreamMessageSendingAdapter adapter = new StreamMessageSendingAdapter(streamBridge);
//        log.trace("[GstDev Cloud] |- Bean [Stream Message Sending Adapter] Auto Configure.");
//        return adapter;
//    }
//}
