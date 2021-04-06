package com.project.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.demo.entity.McCard;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author redHin
 * @since 2021-02-21
 */
public interface McCardMapper extends BaseMapper<McCard> {

  /**
   * mcCard 列表
   *
   * @return
   */
  List<McCard> getMcCardList();

}
