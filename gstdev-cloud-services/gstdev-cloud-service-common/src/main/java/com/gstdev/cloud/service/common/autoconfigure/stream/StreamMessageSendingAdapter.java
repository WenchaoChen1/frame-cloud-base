//package com.gstdev.cloud.oauth2.resource.server.autoconfigure.stream;
//
//import com.gstdev.cloud.message.core.definition.MessageSendingAdapter;
//import com.gstdev.cloud.message.core.definition.domain.StreamMessage;
//import com.gstdev.cloud.message.core.definition.event.StreamMessageSendingEvent;
//import org.apache.commons.lang3.ObjectUtils;
//import org.springframework.cloud.stream.function.StreamBridge;
//
///**
// * <p>Description: Spring Cloud Stream 消息发送适配器 </p>
// *
// * @author : cc
// * @date : 2023/10/26 18:01
// */
//public class StreamMessageSendingAdapter implements MessageSendingAdapter<StreamMessage, StreamMessageSendingEvent<StreamMessage>> {
//
//    private final StreamBridge streamBridge;
//
//    public StreamMessageSendingAdapter(StreamBridge streamBridge) {
//        this.streamBridge = streamBridge;
//    }
//
//    @Override
//    public void onApplicationEvent(StreamMessageSendingEvent<StreamMessage> event) {
//        StreamMessage message = event.getData();
//
//        if (ObjectUtils.isEmpty(message.getBinderName())) {
//            if (ObjectUtils.isEmpty(message.getOutputContentType())) {
//                streamBridge.send(message.getBindingName(), message.getBinderName(), message.getData());
//            } else {
//                streamBridge.send(message.getBindingName(), message.getBinderName(), message.getData(), message.getOutputContentType());
//            }
//        } else {
//            if (ObjectUtils.isEmpty(message.getOutputContentType())) {
//                streamBridge.send(message.getBindingName(), message.getData());
//            } else {
//                streamBridge.send(message.getBindingName(), message.getData(), message.getOutputContentType());
//            }
//        }
//    }
//}
