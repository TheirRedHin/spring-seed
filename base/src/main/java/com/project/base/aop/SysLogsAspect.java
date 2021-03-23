package com.project.base.aop;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.base.constants.RabbitConstant;
import com.project.base.jms.JmsProducer;
import com.project.base.util.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 日志切面类
 *
 * @author Admin
 */
@Aspect
@Component
public class SysLogsAspect {

    @Resource
  JmsProducer jmsProducer;

  /**
   * 日志切面
   *
   * @param joinPoint 切面信息
   * @return 继续进行
   */
  @Around("execution(* *..*Controller.*(..))")
  public Object sysLogs(ProceedingJoinPoint joinPoint) throws Throwable {
    Class<?> targetClass = joinPoint.getTarget().getClass();
    String methodName = joinPoint.getSignature().getName();
    ObjectNode objectNode = JsonUtil.getMapper().createObjectNode();
    objectNode.put("className", targetClass.getName());
    objectNode.put("methodName", methodName);
    jmsProducer.send(RabbitConstant.EXCHANGE_TOPICS_INFORM, RabbitConstant.ROUTING_KEY_LOG,
        objectNode.toString());
    return joinPoint.proceed();
  }

}
