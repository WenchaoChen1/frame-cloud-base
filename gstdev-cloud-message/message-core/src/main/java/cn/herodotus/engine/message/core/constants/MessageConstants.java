package cn.herodotus.engine.message.core.constants;

import cn.herodotus.engine.assistant.definition.constants.BaseConstants;

/**
 * <p>Description: 消息模块常量 </p>
 *
 * @author : cc
 * @date : 2022/2/4 17:06
 */
public interface MessageConstants extends BaseConstants {

    String MESSAGE_AREA_PREFIX = AREA_PREFIX + "message:";

    String PROPERTY_PREFIX_KAFKA = PROPERTY_PREFIX_EVENT + ".kafka";
    String PROPERTY_PREFIX_WEBSOCKET = PROPERTY_PREFIX_MESSAGE + ".websocket";
    String PROPERTY_PREFIX_MQTT = PROPERTY_PREFIX_MESSAGE + ".mqtt";

    String MULTIPLE_INSTANCE_INPUT = "webSocketMultipleInstanceSyncInput";
    String MULTIPLE_INSTANCE_OUTPUT = "webSocketMultipleInstanceSyncOutput";

    /**
     * 消息类型判断值。
     */
    String MESSAGE_TO_ALL = "message_to_all";

    String ITEM_KAFKA_ENABLED = PROPERTY_PREFIX_KAFKA + PROPERTY_ENABLED;
    String ITEM_WEBSOCKET_MULTIPLE_INSTANCE = PROPERTY_PREFIX_WEBSOCKET + ".mode";
    String ITEM_MQTT_USERNAME = PROPERTY_PREFIX_MQTT + ".username";
    String ITEM_MQTT_PASSWORD = PROPERTY_PREFIX_MQTT + ".password";

    String REDIS_CURRENT_ONLINE_USER = MESSAGE_AREA_PREFIX + "online:user";
    String REGION_MESSAGE_ANNOUNCEMENT = MESSAGE_AREA_PREFIX + "system_announcement";
    String REGION_MESSAGE_DIALOGUE_CONTACT = MESSAGE_AREA_PREFIX + "personal:contact";
    String REGION_MESSAGE_DIALOGUE = MESSAGE_AREA_PREFIX + "personal:dialogue";
    String REGION_MESSAGE_DIALOGUE_DETAIL = MESSAGE_AREA_PREFIX + "personal:dialogue:detail";
    String REGION_MESSAGE_NOTIFICATION = MESSAGE_AREA_PREFIX + "notification_queue";
    String REGION_MESSAGE_PULL_STAMP = MESSAGE_AREA_PREFIX + "pull_stamp";
    String WEBSOCKET_CHANNEL_PROXY_BROADCAST = "/broadcast";
    String WEBSOCKET_CHANNEL_PROXY_PERSONAL = "/personal";
    String WEBSOCKET_DESTINATION_BROADCAST_NOTICE = WEBSOCKET_CHANNEL_PROXY_BROADCAST + "/notice";
    String WEBSOCKET_DESTINATION_BROADCAST_ONLINE = WEBSOCKET_CHANNEL_PROXY_BROADCAST + "/online";
    String WEBSOCKET_DESTINATION_PERSONAL_MESSAGE = WEBSOCKET_CHANNEL_PROXY_PERSONAL + "/message";
}
