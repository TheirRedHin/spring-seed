package com.project.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.demo.entity.McCard;
import com.project.demo.mapper.McCardMapper;
import com.project.demo.service.IMcCardService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author redHin
 * @since 2021-02-21
 */
@Service
public class McCardServiceImpl extends ServiceImpl<McCardMapper, McCard> implements IMcCardService {

  @Resource
  McCardMapper mcCardMapper;

  @Override
  public List<McCard> getMcCardList(){
    return mcCardMapper.getMcCardList();
  }

}
