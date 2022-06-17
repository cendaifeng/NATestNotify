package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Resident;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Optional;

public interface ResidentService extends BaseMapper<Resident> {

  Optional<Resident> getResident(String ic_card_no);

  void upsertResident(Resident resident);

}
