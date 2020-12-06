package com.my.helloword;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/06 10:49
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "springBoot-Queues",durable = "true",autoDelete = "true"))
public class Consumer {

    @RabbitListener//指定回调方法
    public void receive(String message){
        System.out.println("消费者消费信息:"+message);
    }

}
