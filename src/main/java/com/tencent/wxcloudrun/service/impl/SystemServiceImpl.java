package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.CommunityMapper;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

  final CommunityMapper communityMapper;

  public SystemServiceImpl(@Autowired CommunityMapper communityMapper) {
    this.communityMapper = communityMapper;
  }

  @Override
  public String getRegion(String region_code) {
    List<Community> allCommunity = communityMapper.getAllCommunity(region_code);
    log.info(allCommunity.toString());
    return allCommunity.toString();
  }
}
