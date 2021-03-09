package com.redhin.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.redhin.demo.entity.McCard;
import com.redhin.demo.mapper.McCardMapper;
import com.redhin.demo.service.IMcCardService;
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

}
