package com.project.demo.controller;


import com.project.base.exception.BaseException;
import com.project.demo.entity.McCard;
import com.project.demo.service.IMcCardService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

  @RequestMapping(value = "test", method = RequestMethod.GET, produces = {
      "application/json; charset=utf-8"})
  public String test() {
    McCard mcCard = iMcCardService.getById("1");
    return mcCard.getName();
  }

  @RequestMapping(value = "listTest", method = RequestMethod.GET, produces = {
      "application/json; charset=utf-8"})
  public List<McCard> listTest() {
    List<McCard> mcCardList = iMcCardService.getMcCardList();
    String test = iMcCardService.test();
    return mcCardList;
  }

  @RequestMapping(value = "logTest", method = RequestMethod.GET, produces = {
      "application/json; charset=utf-8"})
  public String logTest() {
    McCard mcCard = iMcCardService.getById("2");
    return mcCard.getName();
  }

  @RequestMapping("/zero")
  @ResponseBody
  public String zero() {
    String info = "除0异常";
    int a = 1 / 0;
    return info;
  }

  @RequestMapping("/null")
  @ResponseBody
  public String nullError() {
    String info = "空指针异常";
    McCard mcCard = null;
    String name = mcCard.getName();
    return info;
  }

  @RequestMapping("/other")
  @ResponseBody
  public String other() {
    throw new BaseException("2222", "测试异常");
  }

}
