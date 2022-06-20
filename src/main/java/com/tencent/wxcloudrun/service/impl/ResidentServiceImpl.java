package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.CountersMapper;
import com.tencent.wxcloudrun.dao.ResidentMapper;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ResidentServiceImpl extends ServiceImpl<ResidentMapper, Resident> implements ResidentService {

  final ResidentMapper residentMapper;

  public ResidentServiceImpl(@Autowired ResidentMapper residentMapper) {
    this.residentMapper = residentMapper;
  }

  @Override
  public Optional<Resident> getResident(String ic_card_no) {
    return Optional.ofNullable(residentMapper.getResident(ic_card_no));
  }

  @Override
  public Optional<Resident> getResidentByOpenId(String open_id) {
    return Optional.ofNullable(residentMapper.getResidentByOpenId(open_id));
  }

  @Override
  public boolean register(Resident resident) {
    if (super.save(resident)) {
      log.info("新用户注册啦！ "+resident);
      return true;
    }
    return false;
  }

  @Override
  public boolean update(Resident resident) {
    UpdateWrapper<Resident> wrapper = new UpdateWrapper<>();
    wrapper.eq("open_id", resident.getOpen_id());
    return super.update(resident, wrapper);
  }

  @Override
  public boolean updateByIc(Resident resident) {
    UpdateWrapper<Resident> wrapper = new UpdateWrapper<>();
    wrapper.eq("ic_card_no", resident.getIc_card_no());
    return super.update(resident, wrapper);
  }

}
