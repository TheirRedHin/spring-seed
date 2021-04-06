package com.project.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.demo.entity.McCard;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author redHin
 * @since 2021-02-21
 */
public interface IMcCardService extends IService<McCard> {

  /**
   * 获取 mcCard 列表
   *
   * @return
   */
  List<McCard> getMcCardList();

  String test();

}
