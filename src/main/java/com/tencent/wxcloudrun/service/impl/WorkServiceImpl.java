package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.ResidentMapper;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.ResidentService;
import com.tencent.wxcloudrun.service.WorkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WorkServiceImpl extends ServiceImpl<ResidentMapper, Resident> implements WorkService {

  @Override
  public boolean newTask(Resident resident) {
    return false;
  }
}
