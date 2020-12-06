package com.my;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/06 10:30
 */
@SpringBootTest
public class RabbitmqSpringbootApplicationTests {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test//topic
    void topic(){
        //发布消息  参数:交换机名,路由key,内容
        rabbitTemplate.convertAndSend("Springbt-topic","user.add.aaa","你好Springbt-topic");
        System.out.println("==== send success ====");
    }

    @Test//fanout
    void direct() {
        //发布消息  参数:交换机名,路由key,内容
        rabbitTemplate.convertAndSend("Springbt-routing","info","你好Springbt-routing");
        System.out.println("==== send success ====");
    }

    @Test//fanout
    void fanout(){
        //发布消息  参数:交换机名,路由key,内容
        rabbitTemplate.convertAndSend("Springbt-logs","","你好SpringBoot fanout");
        System.out.println("==== send success ====");

    }

    @Test//work
    void testwork() {
        ////发布消息  参数:队列名称,发送内容
        for (int i=0;i<20;i++){
            rabbitTemplate.convertAndSend("springBoot-work","你好SpringBoot work"+i);
            System.out.println("==== send success ====");
        }
    }

    @Test//hello word
    void contextLoads() {
        //发布消息  参数:队列名称,发送内容
        rabbitTemplate.convertAndSend("springBoot-Queues","你好SpringBoot hello-world");
        System.out.println("== send success ===");
    }

}
