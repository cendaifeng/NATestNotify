package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.model.Location;
import com.tencent.wxcloudrun.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * counter控制器
 */
@RestController
public class SystemController {

  final SystemService systemService;
  final Logger logger;

  public SystemController(@Autowired SystemService systemService) {
    this.systemService = systemService;
    this.logger = LoggerFactory.getLogger(SystemController.class);
  }

  /**
   * 查詢region信息
   * @return API response json
   * {
   *         "福保": [
   *             {
   *                 "community_id": 1,
   *                 "community_name": "益田",
   *                 "street": "福保",
   *                 "region_code": "666666"
   *             },
   *             {
   *                 "community_id": 2,
   *                 "community_name": "石厦",
   *                 "street": "福保",
   *                 "region_code": "666666"
   *             }
   *         ],
   *         "粤海": [
   *             {
   *                 "community_id": 3,D
   *                 "community_name": "科技园",
   *                 "street": "粤海",
   *                 "region_code": "666666"
   *             }
   *         ]
   * }
   */
  @GetMapping(value = "/system/region")
  ApiResponse getRegionCommunity(@RequestParam String region_code) {
    logger.info("/system/region get request");
    Map<String, List<Community>> region_info =  systemService.getRegionCommunity(region_code);
    logger.info(region_info.toString());
    logger.info(new Gson().toJson(region_info));
    return ApiResponse.ok(region_info);
  }

  /**
   * 查詢community信息
   * @return API response json
   */
  @GetMapping(value = "/system/community")
  ApiResponse getCommunityTag2(@RequestParam String community_id) {
    logger.info("/system/community get request");
    Map<String, List<Location>> community_info =  systemService.getCommunityTag2(community_id);
    return ApiResponse.ok(community_info);
  }
  
}