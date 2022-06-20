package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dao.CommunityMapper;
import com.tencent.wxcloudrun.dao.LocationMapper;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.model.Location;
import com.tencent.wxcloudrun.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

  final CommunityMapper communityMapper;

  final LocationMapper locationMapper;

  public SystemServiceImpl(@Autowired CommunityMapper communityMapper, LocationMapper locationMapper) {
    this.communityMapper = communityMapper;
    this.locationMapper = locationMapper;
  }

  @Override
  public Map<String, List<Community>> getRegionCommunity(String region_code) {
    List<Community> allCommunity = communityMapper.getAllCommunity(region_code);
    Map<String, List<Community>> map = allCommunity.stream().collect(Collectors.groupingBy(Community::getStreet));
    return map;
  }

  @Override
  public Map<String, List<Location>> getCommunityLocation(String community_id) {
    List<Location> allLocation = locationMapper.getAllLocation(community_id);
    Map<String, List<Location>> map = allLocation.stream().collect(Collectors.groupingBy(Location::getTag1));
    return map;
  }
}
