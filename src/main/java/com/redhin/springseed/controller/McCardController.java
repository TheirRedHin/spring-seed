package com.redhin.springseed.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2021-02-21
 */
@RestController
@RequestMapping("//mcCard")
public class McCardController {

  @RequestMapping("/test")
  public String test(){
    return "this is text";
  }

}
