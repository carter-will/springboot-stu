package com.carter.sbdemo.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AckSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {


    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
     */
    @Autowired
    public AckSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setReturnCallback(this);
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        String rtn = rabbitTemplate.convertSendAndReceive(AmqpACKConfig.EXCHANGE, AmqpACKConfig.ROUTINGKEY, content, correlationId).toString();
        System.err.println("消息发送返回： " + rtn);
    }

    /**
     * 回调
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println(" 回调id:" + correlationData);
        if (ack) {
            System.out.println("ACK消息消费成功");
        } else {
            System.out.println("ACK消息消费失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
    }
}
