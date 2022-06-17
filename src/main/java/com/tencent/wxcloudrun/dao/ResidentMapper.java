package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Resident;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResidentMapper {

  Resident getResident(@Param("ic_card_no") String ic_card_no);

  void upsertResident(Resident resident);

}
