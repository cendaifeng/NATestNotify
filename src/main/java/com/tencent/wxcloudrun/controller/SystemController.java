package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.HashMap;
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
   * 街道1:{
   *  {社区1名称,社区1code},
   *  {社区2名称,社区2code},
   *  ...},
   * 街道2:{
   *  {社区1名称,社区1code},
   *  {社区2名称,社区2code},
   *  ...},...
   * }
   */
  @GetMapping(value = "/system/region")
  ApiResponse getRegion(@RequestParam String region_code) {
    logger.info("/system/region get request");
    String region_info =  systemService.getRegion(region_code);
    return ApiResponse.ok(region_info);
  }
  
}