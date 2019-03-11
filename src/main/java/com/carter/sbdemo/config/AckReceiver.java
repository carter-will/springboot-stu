package com.carter.sbdemo.config;


import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AckReceiver {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    @RabbitListener(queues = "spring-boot-queue")//
    public String processMessage1(String msg, Message message, Channel channel) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自消息队列的信息：" + msg);

        try {

            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            //消息确认  因为我在属性配置文件里面开启了ACK确认 所以如果代码没有执行ACK确认 你在RabbitMQ的后台会看到消息会一直留在队列里面未消费掉 只要程序一启动开始接受该队列消息的时候 又会收到

            log.debug("HelloReceiver 消息接收成功");

        } catch (Exception e) {
            e.printStackTrace();

            log.debug("HelloReceiver 消息接收失败");
            // ack返回false，并重新放回队列
            try {
                log.debug("HelloReceiver ack返回false，并重新放回队列");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }


        return msg.toUpperCase();
    }


    @RabbitListener(queues = {DQRabbitmqConfig.REGISTER_QUEUE_NAME})
    public void listenerDelayQueue(Object obj, Message message, Channel channel) {
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now(), obj.toString());
        try {
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }




}
