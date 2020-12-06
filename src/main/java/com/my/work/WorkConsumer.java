package com.my.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/06 10:56
 */
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "springBoot-work",autoDelete = "true"))
    public void receive(String message){
        System.out.println("消费者 1 消费信息:"+message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "springBoot-work",autoDelete = "true"))
    public void receive2(String message){
        System.out.println("消费者 2 消费信息:"+message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
