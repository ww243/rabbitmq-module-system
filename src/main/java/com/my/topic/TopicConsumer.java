package com.my.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/06 10:36
 */
@Component
public class TopicConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "Springbt-topic",type = "topic"),
            key = {"user.*"}
    ))
    public void receivel(String message){
        System.out.println("消费者。1。消费信息:"+message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "Springbt-topic",type = "topic"),
            key = {"user.#"}
    ))
    public void receivel2(String message){
        System.out.println("消费者。2。消费信息:"+message);
    }

}
