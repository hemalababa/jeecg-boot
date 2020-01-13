package org.jeecg.modules.iot.service.impl;

import org.jeecg.modules.iot.entity.Equiment;
import org.jeecg.modules.iot.mapper.EquimentMapper;
import org.jeecg.modules.iot.service.IEquimentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 设备表
 * @Author: jeecg-boot
 * @Date:   2020-01-10
 * @Version: V1.0
 */
@Service
public class EquimentServiceImpl extends ServiceImpl<EquimentMapper, Equiment> implements IEquimentService {

}
