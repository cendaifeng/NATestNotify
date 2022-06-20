package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("location_list")
public class Location implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String community_id;

  private String tag1;

  private String tag2;

}
