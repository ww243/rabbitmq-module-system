package com.my;

import com.my.util.RabbitmqUtil;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/03 21:27
 */
@SpringBootTest
public class RabbitmqTests {

    @Test
    void contextLoads()throws Exception{

        //获取连接
        Connection connection = RabbitmqUtil.getConnection();

        //根据连接创建通道
        Channel channel = connection.createChannel();

        /**设置队列参数
         * @param:queue,队列名称、如果这个队列不存在，将会被创建
         * @param:durable,持久性：用来定义队列是否要持久化、true:持久化、false:不持久化
         * @param:exclusive,是否只能由创建者使用，其他连接不能使用。true:独占队列、false:不独占队列
         * @param:autoDelete,是否自动删除（没有连接自动删除）、true:自动删除、false:不自动删除
         * @param:arguments,队列的其他属性(构造参数)
         */
        channel.queueDeclare("javaQueues",true,false,true,null);

        //发布消息
        //设置发布消息的参数
        /**发布消息
         * @param:exchange、消息交换机名称,空字符串将使用直接交换器模式，发送到默认的Exchange=amq.direct。此状态下，RoutingKey默认和Queue名称相同
         * @param:queueName、队列名称
         * @param:BasicProperties、设置消息持久化：MessageProperties.PERSISTENT_TEXT_PLAIN是持久化；MessageProperties.TEXT_PLAIN是非持久化。
         * @param:body、消息对象转换的byte[]
         */
        channel.basicPublish("","javaQueues", MessageProperties.PERSISTENT_TEXT_PLAIN,"这是第一条消息".getBytes());

        //关闭连接
        RabbitmqUtil.closeConnection(channel,connection);

    }

    public static void main(String[] args) {
        try {
            //获取连接
            Connection connection = RabbitmqUtil.getConnection();
            //根据连接创建通道
            Channel channel = connection.createChannel();
            /**设置队列参数
             * @param:queue、队列名称、如果这个队列不存在，将会被创建
             * @param:durable、持久性：用来定义队列是否要持久化、、true:持久化、false:不持久化
             * @param:exclusive、是否只能由创建者使用，其他连接不能使用。true:独占队列、false:不独占队列
             * @param:autoDelete、是否自动删除（没有连接自动删除）、true:自动删除、false:不自动删除
             * @param:arguments、队列的其他属性(构造参数)
             */
            channel.queueDeclare("javaQueues",true,false,true,null);
            //消费者消费消息
            /**消费者消费消息
             * @param:queue、队列名称
             * @param:autoAck、是否自动应答。false表示consumer在成功消费过后必须要手动回复一下服务器，如果不回复，服务器就将认为此条消息消费失败，继续分发给其他consumer。
             * @param:callback、回调方法类，一般为自己的Consumer类
             */
            channel.basicConsume("javaQueues",true,new DefaultConsumer(channel){

                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)throws IOException{
                    String s = new String(body, "UTF-8");
                    System.out.println("消费消息:"+s);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
