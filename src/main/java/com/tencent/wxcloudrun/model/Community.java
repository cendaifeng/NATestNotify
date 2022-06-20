package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("community_list")
public class Community implements Serializable {

  @TableId(value = "community_id", type = IdType.AUTO)
  private Integer community_id;

  private String community_name;

  private String street;

  private String region_code;

}
