package com.ggzed.im.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MqttSenderService {

    @Resource
    private MessageChannel mqttOutboundChannel;

    //public void sendMessage(String topic, String message) {
    //    mqttOutboundChannel.send(MessageBuilder.withPayload(message)
    //            .setHeader(MqttHeaders.TOPIC, topic)
    //            .build());
    //}

    public void sendMessage(String topic, String message, int qos, boolean retained) {
        mqttOutboundChannel.send(MessageBuilder.withPayload(message)
                .setHeader(MqttHeaders.TOPIC, topic)
                .setHeader(MqttHeaders.QOS, qos)
                .setHeader(MqttHeaders.RETAINED, retained)
                .build());
    }
}
