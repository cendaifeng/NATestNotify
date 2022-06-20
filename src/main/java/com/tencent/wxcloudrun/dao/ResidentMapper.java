package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.Resident;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ResidentMapper extends BaseMapper<Resident> {

  Resident getResident(@Param("ic_card_no") String ic_card_no);

  Resident getResidentByOpenId(@Param("open_id") String open_id);

}
