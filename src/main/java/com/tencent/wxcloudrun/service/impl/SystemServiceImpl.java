package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.SystemMapper;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

  final SystemMapper systemMapper;

  public SystemServiceImpl(@Autowired SystemMapper systemMapper) {
    this.systemMapper = systemMapper;
  }

  @Override
  public String getRegion(String region_code) {
    List<Community> allCommunity = systemMapper.getAllCommunity(region_code);
    log.info(allCommunity.toString());
    return allCommunity.toString();
  }
}
