package com.my.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/06 10:52
 */
@Component
public class RoutingConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "Springbt-routing",type = "direct"),
            key = {"error"}
    ))
    public void receive(String message){
        System.out.println("消费者 1 消费消息:"+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "Springbt-routing",type = "direct"),
            key = {"info","error"}
    ))
    public void receive2(String message){
        System.out.println("消费者 2 消费消息:"+message);
    }


}
