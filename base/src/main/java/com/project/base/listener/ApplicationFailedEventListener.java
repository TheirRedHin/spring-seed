package com.project.base.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 服务启动失败的监听器
 */
@Component
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

  @Override
  public void onApplicationEvent(ApplicationFailedEvent event) {
    System.err.println("------------------------");
    System.err.println(String.format("--------%s--------", "应用启动失败"));
    System.err.println("------------------------");
  }
}
