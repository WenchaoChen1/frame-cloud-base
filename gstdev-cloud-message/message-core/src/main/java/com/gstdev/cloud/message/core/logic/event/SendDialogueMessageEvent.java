package com.gstdev.cloud.message.core.logic.event;

import com.gstdev.cloud.message.core.definition.event.AbstractApplicationEvent;
import com.gstdev.cloud.message.core.logic.domain.DialogueMessage;

import java.time.Clock;

/**
 * <p>Description: 本地发送对话消息事件 </p>
 *
 * @author : cc
 * @date : 2023/3/11 18:40
 */
public class SendDialogueMessageEvent extends AbstractApplicationEvent<DialogueMessage> {

    public SendDialogueMessageEvent(DialogueMessage data) {
        super(data);
    }

    public SendDialogueMessageEvent(DialogueMessage data, Clock clock) {
        super(data, clock);
    }
}
