package com.gstdev.cloud.message.core.constants;

/**
 * <p>Description: 消息通道定义 </p>
 *
 * @author : cc
 * @date : 2023/11/22 18:49
 */
public interface FrameChannels {

    /**
     * Mqtt 默认入站通道
     */
    String MQTT_DEFAULT_INBOUND_CHANNEL = "mqttDefaultInboundChannel";
    /**
     * Mqtt 默认出站通道
     */
    String MQTT_DEFAULT_OUTBOUND_CHANNEL = "mqttDefaultOutboundChannel";
    /**
     * Emqx 默认的监控指标数据数据 Mqtt 类型入站通道
     */
    String EMQX_DEFAULT_MONITOR_MQTT_INBOUND_CHANNEL = "emqxDefaultMonitorMqttInboundChannel";
    /**
     * Emqx 默认的 Webhook 数据 HTTP 类型入站通道
     */
    String EMQX_DEFAULT_WEBHOOK_HTTP_INBOUND_CHANNEL = "emqxDefaultWebhookHttpInboundChannel";
    /**
     * Emqx 默认的系统时间数据 EVENT 类型出站通道
     */
    String EMQX_DEFAULT_EVENT_OUTBOUND_CHANNEL = "emqxDefaultEventOutboundChannel";
}
