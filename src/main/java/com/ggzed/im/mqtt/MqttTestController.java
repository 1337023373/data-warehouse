package com.ggzed.im.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
public class MqttTestController {

    @Autowired
    private MqttSenderService mqttSenderService;

    @GetMapping("/send")
    public String send(@RequestParam String topic, @RequestParam String message) throws InterruptedException {
        mqttSenderService.sendMessage(topic, message,1,true);
        return "Message sent.";
    }
}
