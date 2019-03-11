package com.carter.sbdemo.controller;


import com.carter.sbdemo.config.AckSender;
import com.carter.sbdemo.config.DQRabbitmqConfig;
import com.carter.sbdemo.config.Sender;
import com.carter.sbdemo.model.TblChannelDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rabbit")
public class RabbitmqController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Sender sender;

    @Autowired
    private AckSender acksender;

    /**
     *  Direct方式
     *  direct 类型的行为是"先匹配, 再投送". 即在绑定时设定一个 routing_key, 消息的routing_key完全匹配时, 才会被交换器投送到绑定的队列中去。
     * @return
     */
    @ResponseBody
    @GetMapping("/str")
    public boolean sendstr(String sign){
        String msg = "NOW DATE : " + new Date().toString();

        sender.sender(msg);

        return true;
    }

    /**
     * Topic
     * 按规则转发消息（最灵活）。
     * @return
     */
    @ResponseBody
    @GetMapping("/user")
    public boolean senduser(){
        sender.user("user " + new Date().toString());
        sender.userQuery("user query " + new Date().toString());
        return true;
    }

    @ResponseBody
    @GetMapping("/order")
    public boolean sendorder(){
        sender.order("order " + new Date().toString());
        sender.orderQuery("order query " + new Date().toString());
        sender.orderDetailQuery("order detail query " + new Date().toString());
        return true;
    }

    /**HeadersExchange
     * HeadersExchange交换机是根据请求消息中设置的header attribute参数类型来匹配的（和routingKey没有关系）。
     * @return
     */
    @ResponseBody
    @GetMapping("/header")
    public boolean sendheader(){
        Map<String,Object> head = new HashMap<>();
        head.put("type", "cash");
        sender.creditBank(head, "银行授信(部分匹配)");

        head.clear();
        head.put("type", "cash");
        head.put("aging", "fast");
        sender.creditBank(head, "银行授信(全部匹配)");

        head.clear();
        head.put("type", "cash");
        sender.creditFinance(head, "金融公司授信(部分匹配)");

        head.clear();
        head.put("type", "cash");
        head.put("aging", "fast");
        sender.creditFinance(head, "金融公司授信(全部匹配)");

        return true;
    }

    /**FanoutExchange
     * FanoutExchange交换机是转发消息到所有绑定队列（广播模式，和routingKey没有关系）。
     * @return
     */
    @ResponseBody
    @GetMapping("/fanout")
    public boolean sendfanout(){

        sender.generateReports("Fanout send Date : " + new Date().toString());
        return true;
    }


    /**
     * 模拟多对一
     * @return
     */
    @ResponseBody
    @GetMapping("/m2o1")
    public boolean multi2one1() throws InterruptedException {
        for (int i = 0; i < 20; i+=2) {
            sender.sender("支付订单号："+i);
            Thread.sleep(1000);
        }
        return true;
    }

    @ResponseBody
    @GetMapping("/m2o2")
    public boolean multi2one2() throws InterruptedException {
        for (int i = 1; i < 20; i+=2) {
            sender.sender("支付订单号："+i);
            Thread.sleep(1000);
        }
        return true;
    }

    /**
     * 一对多
     * @return
     * @throws InterruptedException
     */
    @ResponseBody
    @GetMapping("/o2m")
    public boolean one2multi() throws InterruptedException {
        for (int i = 1; i < 10; i+=1) {
            sender.sender("支付订单号："+i);
            Thread.sleep(1000);
        }
        return true;
    }


    /**发送对象
     *  传递的对象必须支持序列化（实现了Serializable接口）
     * @return
     */
    @ResponseBody
    @GetMapping("/obj")
    public boolean sendobj(String sign){
        TblChannelDTO channel = new TblChannelDTO();
        channel.setName("夸克渠道");
        channel.setComid("EC11111");

        sender.sender(channel);

        return true;
    }

    /**RPC
     *  RabbitMQ支持RPC远程调用，同步返回结果。
     *  虽然RabbitMQ支持RPC接口调用，但不推荐使用。
     *  1）RPC默认为单线程阻塞模型，效率极低。2）需要手动实现多线程消费。
     * @return
     */
    @ResponseBody
    @GetMapping("/rpc")
    public boolean sendrpc(String sign){
        sender.sendrpc("111111111");
        return true;
    }


    /***
     * ACK模式
     * @return
     */
    @ResponseBody
    @GetMapping("/ack")
    public boolean sendack(){
        acksender.sendMsg("abcdefghijklmnopqrstuvwxyz");
        return true;
    }




    /**
     * 延迟队列
     * @param
     * @return
     */
    @ResponseBody
    @GetMapping("/delay")
    public boolean delayQueue(){
        String cont = "abcdefghijklmnopqrstuvwxyz";
        // 添加延时队列
        this.rabbitTemplate.convertAndSend(DQRabbitmqConfig.REGISTER_DELAY_EXCHANGE, DQRabbitmqConfig.DELAY_ROUTING_KEY, cont, message -> {
            // TODO 第一句是可要可不要,根据自己需要自行处理
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, cont);
            // TODO 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
        return true;
    }
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitmqController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }






}
