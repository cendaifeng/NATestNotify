package com.tencent.wxcloudrun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("resident_info")
public class Resident implements Serializable {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String ic_card_no;

  private String open_id;

  private String name;

  private String phone;

  private Integer community_id;

  private boolean is_staff;

  private Integer location_id;

  private Integer tag3_floor;

  private String tag4_room;
}
