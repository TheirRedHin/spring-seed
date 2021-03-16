package com.project.demo.controller;


import com.project.demo.service.IMcCardService;
import com.project.rabbitmq.producer.HelloProducer;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author redHin
 * @since 2021-02-21
 */
@RestController
@RequestMapping("//mcCard")
public class McCardController {

  @Resource
  private IMcCardService iMcCardService;

  @Resource
  private HelloProducer helloProducer;

  @RequestMapping("test")
  public String test() {
    helloProducer.send();
    return "test";
  }

}
