package com.carter.sbdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Sender{

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**********************************************Direct*********************************************/
    public void sender(String msg){
        log.debug("notify.payment send message: "+msg);
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }

    /**********************************************Topic*********************************************/
    public void user(String msg){
        log.debug("api.core.user send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.user", msg);
    }

    public void userQuery(String msg){
        log.debug("api.core.user.query send message: "+msg);
        rabbitTemplate.convertAndSend("coreExchange", "api.core.userquery", msg);
    }

    public void order(String msg){
        log.debug("api.payment.order send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    public void orderQuery(String msg){
        log.debug("api.payment.order.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    public void orderDetailQuery(String msg){
        log.debug("api.payment.order.detail.query send message: "+msg);
        rabbitTemplate.convertAndSend("paymentExchange", "api.payment.order.detail.query", msg);
    }

    /**********************************************HeadersExchange*********************************************/
    public void creditBank(Map<String, Object> head, String msg){
        log.debug("credit.bank send message: "+msg);
        rabbitTemplate.convertAndSend("creditBankExchange", "credit.bank", getMessage(head, msg));
    }

    public void creditFinance(Map<String, Object> head, String msg){
        log.debug("credit.finance send message: "+msg);
        rabbitTemplate.convertAndSend("creditFinanceExchange", "credit.finance", getMessage(head, msg));
    }

    private Message getMessage(Map<String, Object> head, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : head.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }

    /**********************************************FanoutExchange*********************************************/
    public void generateReports(String msg){
        log.debug("api.generate.reports send message: "+msg);
        rabbitTemplate.convertAndSend("reportExchange", "api.generate.reports", msg);
    }

    /**********************************************发送对象*********************************************/
    public void sender(Object obj){
        log.debug("notify.payment send object: " + obj.toString());
        rabbitTemplate.convertAndSend("notify.payment", obj.toString());
    }


    public void sendrpc(String str){
        log.debug("query.rpc send message: "+str);
        String result = (String) rabbitTemplate.convertSendAndReceive("query.rpc", str);
        log.debug("query.rpc send message: "+result);
    }


}
