package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.dao.CountersMapper;
import com.tencent.wxcloudrun.dao.ResidentMapper;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {

  final ResidentMapper residentMapper;

  public ResidentServiceImpl(@Autowired ResidentMapper residentMapper) {
    this.residentMapper = residentMapper;
  }

  @Override
  public Optional<Resident> getResident(String ic_card_no) {
    return Optional.ofNullable(residentMapper.getResident(ic_card_no));
  }

  @Override
  public void upsertResident(Resident resident) {
    residentMapper.upsertResident(resident);
  }




  @Override
  public int insert(Resident entity) {
    return 0;
  }

  @Override
  public int deleteById(Serializable id) {
    return 0;
  }

  @Override
  public int deleteByMap(Map<String, Object> columnMap) {
    return 0;
  }

  @Override
  public int delete(Wrapper<Resident> wrapper) {
    return 0;
  }

  @Override
  public int deleteBatchIds(Collection<? extends Serializable> idList) {
    return 0;
  }

  @Override
  public int updateById(Resident entity) {
    return 0;
  }

  @Override
  public int update(Resident entity, Wrapper<Resident> updateWrapper) {
    return 0;
  }

  @Override
  public Resident selectById(Serializable id) {
    return null;
  }

  @Override
  public List<Resident> selectBatchIds(Collection<? extends Serializable> idList) {
    return null;
  }

  @Override
  public List<Resident> selectByMap(Map<String, Object> columnMap) {
    return null;
  }

  @Override
  public Resident selectOne(Wrapper<Resident> queryWrapper) {
    return null;
  }

  @Override
  public Integer selectCount(Wrapper<Resident> queryWrapper) {
    return null;
  }

  @Override
  public List<Resident> selectList(Wrapper<Resident> queryWrapper) {
    return null;
  }

  @Override
  public List<Map<String, Object>> selectMaps(Wrapper<Resident> queryWrapper) {
    return null;
  }

  @Override
  public List<Object> selectObjs(Wrapper<Resident> queryWrapper) {
    return null;
  }

  @Override
  public <E extends IPage<Resident>> E selectPage(E page, Wrapper<Resident> queryWrapper) {
    return null;
  }

  @Override
  public <E extends IPage<Map<String, Object>>> E selectMapsPage(E page, Wrapper<Resident> queryWrapper) {
    return null;
  }
}
