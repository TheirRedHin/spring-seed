package com.redhin.demo.controller;


import com.redhin.demo.bes.QueueConsumer;
import com.redhin.demo.bes.QueueProducer;
import com.redhin.demo.service.IMcCardService;
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

  @RequestMapping("test")
  public String test() {

    String args[] = new String[]{"192.168.1.36", "3200", "admin", "admin", "besTestQueue"};

    QueueProducer producerSample = new QueueProducer(args);
    producerSample.execute();
    QueueConsumer consumerSample = new QueueConsumer(args);
    consumerSample.execute();

    return iMcCardService.getById("1").getName();

  }

}
