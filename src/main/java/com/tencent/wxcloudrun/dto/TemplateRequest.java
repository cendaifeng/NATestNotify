package com.tencent.wxcloudrun.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TemplateRequest {

  private String touser; // OPENID
  private String template_id = "EExnBlMHDFIhx-nqapk4ARrNUqK_18jVcoNEmdEQVrQ";
  private HashMap<String, Map> data;

  public TemplateRequest(String touser, String name1, String thing2, String time60) {
    this.touser = touser;
    this.data = new HashMap<String, Map>(){{
      put("name1", new HashMap<String, String>(){{put("value", name1);}});
      put("thing2", new HashMap<String, String>(){{put("value", thing2);}});
      put("thing8", new HashMap<String, String>(){{put("value", "请尽快前来检测");}});
      put("time60", new HashMap<String, String>(){{put("value", time60);}});
    }};
  }

}
