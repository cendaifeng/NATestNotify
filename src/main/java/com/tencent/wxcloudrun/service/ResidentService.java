package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Resident;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Optional;

public interface ResidentService extends IService<Resident> {

  Optional<Resident> getResident(String ic_card_no);

  boolean register(Resident resident);

}
