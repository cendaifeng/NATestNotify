package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.ResidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * counter控制器
 */
@RestController
public class ResidentController {

  final ResidentService residentService;
  final Logger logger;

  public ResidentController(@Autowired ResidentService residentService) {
    this.residentService = residentService;
    this.logger = LoggerFactory.getLogger(ResidentController.class);
  }

  public static String getSha1(String str) {
    char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };
    try {
      MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
      mdTemp.update(str.getBytes("UTF-8"));
      byte[] md = mdTemp.digest();
      int j = md.length;
      char buf[] = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++) {
        byte byte0 = md[i];
        buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
        buf[k++] = hexDigits[byte0 & 0xf];
      }
      return new String(buf);
    } catch (Exception e) {
      return null;
    }
  }


  /**
   * 居民登录
   * @return API response json
   */
  @PostMapping(value = "/resident/login")
  ApiResponse login(@RequestBody Map info) {
    logger.info("/resident/login post request");
    String open_id = (String) info.get("open_id");
//    String ic_card_no = (String) info.get("ic_card_no");
//    Optional<Resident> resid = residentService.getResident(ic_card_no);

    Optional<Resident> resid = residentService.getResidentByOpenId(open_id);
    if (!resid.isPresent())
      return ApiResponse.ok("该微信账号未绑定身份信息，请先注册",
              new HashMap<Object, Object>(){{
                put("status", 0);}});

    String sha1 = getSha1(open_id.concat(resid.get().getIc_card_no()));
    return ApiResponse.ok(new HashMap<Object, Object>(){{
      put("status", 1);
      put("qrcode", sha1);}});

//    String resid_open_id = resid.get().getOpen_id();
//    if (resid_open_id == null || resid_open_id.equals("")) {
//      Resident resid2 = resid.get();
//      resid2.setOpen_id(open_id);
//      if (!residentService.updateByIc(resid2))
//        logger.info("用户openid更新失败");
//      resid_open_id = open_id;
//    }

//    if (open_id.equals(resid_open_id)) {
//      String sha1 = getSha1(open_id.concat(ic_card_no));
//      return ApiResponse.ok(new HashMap<Object, Object>(){{
//        put("status", 1);
//        put("qrcode", sha1);}});
//    } else {
//      return ApiResponse.ok("该用户已使用其他微信账户注册",
//              new HashMap<Object, Object>(){{
//                put("status", 2);}});
//    }
  }

  /**
   * 居民注册
   * @return API response json
   */
  @PostMapping(value = "/resident/register")
  ApiResponse register(@RequestBody Resident resident) {
    logger.info("/resident/register post request");

    if (residentService.register(resident))
      return ApiResponse.ok();
    else
      return ApiResponse.error("注册失败");
  }

  /**
   * 查询居民信息
   * @return API response json
   */
  @GetMapping(value = "/resident/info")
  ApiResponse info(@RequestParam String open_id) {
    logger.info("/resident/info get request");
    Optional<Resident> resid = residentService.getResidentByOpenId(open_id);

    if (!resid.isPresent())
      return ApiResponse.ok("该微信账号未绑定身份信息", null);

    return ApiResponse.ok(resid);
  }

  /**
   * 更新居民信息
   * 按open_id查找
   * @return API response json
   */
  @PostMapping(value = "/resident/update")
  ApiResponse update(@RequestBody Resident resident) {
    logger.info("/resident/update post request");

    if (residentService.update(resident))
      return ApiResponse.ok();
    else
      return ApiResponse.error("更新失败");
  }
  
}