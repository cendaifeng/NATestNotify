package com.tencent.wxcloudrun.dao;

import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.model.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LocationMapper {

  List<Location> getAllTag2(@Param("community_id") String community_id);

}
