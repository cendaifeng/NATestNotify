package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.model.Resident;

import java.util.List;
import java.util.Map;

public interface SystemService{

  Map<String, List<Community>> getRegion(String region_code);

}
