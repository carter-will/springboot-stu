package com.carter.sbdemo.aspect;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;

@Component
@Aspect
public class WebControllerAop {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.carter.sbdemo.controller.AopController.beforeService(..))")
    public void executeService() {

    }

    /**
     * 01 . 前置通知：方法调用前被调用
     */
    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {//  通过JoinPoint 获取通知的签名信息，如目标方法名，目标方法参数信息等
        log.debug("我是前置通知 -----start-------");
        Object[] obj = joinPoint.getArgs();//获取目标方法的参数信息
        joinPoint.getThis(); // AOP代理类信息
        joinPoint.getTarget(); // 代理的目标对象
        Signature signature = joinPoint.getSignature(); //  用的最多，通知的签名
        log.debug("代理的方法是 ： " + signature.getName()); //  打印 代理的是哪一个方法
        log.debug("AOP 代理的名字 ： " + signature.getDeclaringTypeName());// AOP 代理的名字
        log.debug(signature.getDeclaringType().getName());//  AOP代理类的类（class）信息
        /*
          通过RequestContextHolder获取请求信息，如session 信息 ;
         */
        //  获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //  从requestAttributes中获取HttpServletRequest信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //  获取session信息
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

        log.debug("请求 ： " + request + " ,  HttpSession : " + session);
        Enumeration<String> enumerations = request.getParameterNames();
//        Map<String,String> parameterMaps=new HashMap<>();
        Map<String, String> parameterMaps = Maps.newHashMap();
        while (enumerations.hasMoreElements()) {
            String parameter = enumerations.nextElement();
            parameterMaps.put(parameter, request.getParameter(parameter));
        }

        String str = JSON.toJSONString(parameterMaps);
        if (obj.length > 0) {
            log.debug("请求参数信息为 ： " + str);
        }
        log.debug("我是前置通知 -----end-------");
    }



    /**
     * 02  .后置返回通知
     * 需要注意：
     *      如果第一个参数是JoinPoint，则第二个参数是返回值的信息
     *      如果参数中的第一个不是JoinPoint，则第一个参数是returning中对应的参数，
     *    returning 限定了只有目标方法返回值与通知方法相应参数类型时才能
     * 执行后置返回通知，否则不执行;
     * 对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param joinPoint
     * @param keys
     */
    @AfterReturning(value="execution(* com.carter.sbdemo.controller.AopController.afterService(..))",returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object keys){
        System.out.println("后置通知执行了！！");
        System.out.println("第一个后置返回通知的返回值是 ："+keys);
    }

    @AfterReturning(value="execution(* com.carter.sbdemo.controller.AopController.afterService(..))",returning = "keys",argNames="keys")
    public void doAfterReturningAdvice2(String keys){ // 通知方法形影参数的类型是String
        System.out.println("第二个后置返回通知的返回值是 ："+keys);
    }


    @AfterThrowing(value="execution(* com.carter.sbdemo.controller.AopController.afterThrownService(..))",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        // 目标方法名
        System.out.println(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            log.debug("发生了空指针异常");
        }
    }

    @AfterThrowing(value="execution(* com.carter.sbdemo.controller.AopController.testAfter1(..))")
    public void doAfterAdvice(JoinPoint joinPoint){

        System.out.println("后置最终通知执行了！");
    }



    @Around(value="execution(* com.carter.sbdemo.controller.AopController.arround(..))")
    public Object doArroundAdvice(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("环绕通知的目标方法名为 ： "+proceedingJoinPoint.getSignature().getName());

        try {
            Object object=proceedingJoinPoint.proceed();
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return  null;
    }


}