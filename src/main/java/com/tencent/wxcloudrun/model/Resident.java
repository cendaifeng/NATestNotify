package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Resident implements Serializable {

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
