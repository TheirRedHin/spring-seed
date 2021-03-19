package com.project.base.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 服务启动成功的监听器
 */
@Component
public class ApplicationReadyEventListener implements  ApplicationListener<ApplicationReadyEvent>{
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("***********************");
		System.out.println(String.format("********%s********", "应用已启动"));
		System.out.println("***********************");
	}
}
