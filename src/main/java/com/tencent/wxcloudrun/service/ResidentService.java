package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.Resident;

import java.util.List;
import java.util.Optional;

public interface ResidentService extends IService<Resident> {

  Optional<Resident> getResident(String ic_card_no);

  Optional<Resident> getResidentByOpenId(String open_id);

  Optional<List<Resident>> getResidentByLocationIds(List location_ids);

  Optional<IPage<Resident>> getResidentByLocationIdsPage(List location_ids, String page, String limit);

  boolean register(Resident resident);

  boolean update(Resident resident);

  boolean updateByIc(Resident resident);

  boolean pushMsg(String open_id);

}
