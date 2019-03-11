package com.carter.sbdemo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    /**********************************************Direct*********************************************/
    @RabbitListener(queues = "notify.payment")
    public void receive(String msg) {
        log.debug("notify.payment receive message: "+msg);
    }




    /**********************************************Topic*********************************************/

    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void user(String msg) {
        log.debug("api.core receive message: "+msg);
    }


    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void order(String msg) {
        log.debug("api.payment.order receive message: "+msg);
    }

    /**********************************************HeadersExchange*********************************************/
    @RabbitHandler
    @RabbitListener(queues = "credit.bank")
    public void creditBank(String msg) {
        log.debug("credit.bank receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "credit.finance")
    public void creditFinance(String msg) {
        log.debug("credit.finance receive message: "+msg);
    }


    /**********************************************FanoutExchange*********************************************/
    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg) {
        log.debug("api.report.payment receive message: "+msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg) {
        log.debug("api.report.refund receive message: "+msg);
    }

    /**********************************************模拟一对多 添加一个消费者*********************************************/
    @RabbitListener(queues = "notify.payment")
    public void receive2(String msg) {
        log.debug("bak notify.payment receive message: "+msg);
    }



    /**********************************************发送对象*********************************************/
    @RabbitListener(queues = "notify.payment")
    public void receive(Object obj){
        log.debug("notify.payment receive object: " + obj.toString());
    }

    /**********************************************RPC  RabbitMQ支持RPC远程调用，同步返回结果。*********************************************/
    @RabbitListener(queues = "query.rpc")
    public Object receiveObj(Object obj) {
        log.debug("query.rpc request msg: " + obj.toString());

        return "handle result" + obj.toString();
    }


}
