package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("test_site_list")
public class TestSite implements Serializable {

  @TableId(value = "site_id", type = IdType.AUTO)

  private Integer site_id;

  private Integer community_id;

  private String sitename;

  private String site_location;

}
