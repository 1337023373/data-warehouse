package com.ggzed.im.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientTest {

    public static void main(String[] args) {
        String brokerUrl = "tcp://10.10.10.242:1883"; // Docker 容器映射到本地
        String clientId = "testClient123";         // 客户端 ID，唯一
        String topic = "test123";               // 主题

        try {
            MqttClient client = new MqttClient(brokerUrl, clientId, new MemoryPersistence());

            // 设置连接参数
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            // 如果启用了认证，则设置用户名密码
            // options.setUserName("admin");
            // options.setPassword("public".toCharArray());

            // 设置回调（接收消息）
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("连接丢失: " + cause.getMessage());
                }
                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("接收到消息: " + topic + " -> " + new String(message.getPayload()));
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("消息发送成功");
                }
            });

            // 连接
            client.connect(options);
            System.out.println("连接成功");

            // 订阅主题
            client.subscribe(topic, 1);

            // 发布消息
            String payload = "Hello from Java!";
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(1);
            client.publish(topic, message);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
