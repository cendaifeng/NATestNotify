package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.config.Query;
import com.tencent.wxcloudrun.dao.ResidentMapper;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
  public Optional<List<Resident>> getResidentByLocationIds(List location_ids) {
    List<Resident> residents = this.list(new QueryWrapper<Resident>().in("location_id", location_ids));
    return Optional.ofNullable(residents);
  }

  @Override
  public Optional<IPage<Resident>> getResidentByLocationIdsPage(List location_ids, Integer page, Integer limit) {
    IPage<Resident> page1 = new Query<Resident>().getPage(new HashMap<String, Object>() {{
      put("page", page);
      put("limit", limit);
    }});
    QueryWrapper<Resident> wrapper = new QueryWrapper<Resident>().in("location_id", location_ids);
    IPage<Resident> ipage = this.page(page1, wrapper);
    return Optional.ofNullable(ipage);
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
