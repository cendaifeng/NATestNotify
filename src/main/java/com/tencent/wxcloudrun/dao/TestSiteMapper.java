package com.tencent.wxcloudrun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tencent.wxcloudrun.model.Community;
import com.tencent.wxcloudrun.model.Location;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.model.TestSite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestSiteMapper {

  List<TestSite> getAllTestSite(@Param("community_id") String community_id);

  TestSite getTestSite(@Param("site_id") Integer site_id);

}
