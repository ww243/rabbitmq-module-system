package com.my.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author:ljn
 * @Description:
 * @Date:2020/12/03 21:21
 */
public class RabbitmqUtil {

    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    static {
        //创建连接工厂

        //设置RabbitMQ的ip地址
        connectionFactory.setHost("192.168.41.156");
        //设置RabbitMQ端口
        connectionFactory.setPort(5672);
        //设置虚拟主机名
        connectionFactory.setVirtualHost("/yingx");
        //设置用户名密码
        connectionFactory.setUsername("xiaonan");
        connectionFactory.setPassword("123123");
    }

    //获取连接
    public static Connection getConnection(){

        //通过连接工厂创建连接
        Connection connection=null;
        try {
            connection = connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    //关闭连接
    public static void closeConnection(Channel channel, Connection connection){

        try {
            //关闭连接
            if (channel!=null) channel.close();
            if (connection!=null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
