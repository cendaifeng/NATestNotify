package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class TemplateRequest {

  private String touser; // OPENID
  private String template_id = "EExnBlMHDFIhx-nqapk4ARrNUqK_18jVcoNEmdEQVrQ";
  private HashMap<String, String> data;

  public TemplateRequest(String touser, String name1, String thing2, String time60) {
    this.touser = touser;
    this.data = new HashMap<String, String>(){{
      put("name1", name1);
      put("thing2", thing2);
      put("thing8", "请尽快前来检测");
      put("time60", time60);
    }};
  }
}
