package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.CommunityMapper;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

  final CommunityMapper communityMapper;

  public SystemServiceImpl(@Autowired CommunityMapper communityMapper) {
    this.communityMapper = communityMapper;
  }

  @Override
  public Map<String, List<Community>> getRegion(String region_code) {
    List<Community> allCommunity = communityMapper.getAllCommunity(region_code);
    Map<String, List<Community>> map = allCommunity.stream().collect(Collectors.groupingBy(Community::getStreet));
    log.info(map.toString());
    return map;
  }
}
