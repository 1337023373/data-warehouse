package com.ggzed.im.web;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class HelloController {
 
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
 
    @GetMapping("/hello")
    public String hello(){
        String s = "hello," + new Date();
        log.error(s);
        return s;
    }
 
}