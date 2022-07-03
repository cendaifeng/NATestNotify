package com.tencent.wxcloudrun.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.ResidentService;
import com.tencent.wxcloudrun.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * counter控制器
 */
@RestController
public class WorkController {

  final WorkService workService;
  final ResidentService residentService;
  final Logger logger;

  private static ExecutorService executor = new ThreadPoolExecutor(
          10, 100, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100000),
          Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

  public WorkController(@Autowired WorkService workService, ResidentService residentService) {
    this.workService = workService;
    this.residentService = residentService;
    this.logger = LoggerFactory.getLogger(WorkController.class);
  }

  /**
   * 社工发起检测任务
   * @return API response json
   */
  @PostMapping(value = "/system/newTask")
  ApiResponse newTask(@RequestBody Map info) throws ParseException {
    logger.info("/work/launchTask post request");
    Integer site_id = (Integer) info.get("site_id");
    String start_time_d = (String) info.get("start_time_d");
    String start_time_t = (String) info.get("start_time_t");
    String end_time_d = (String) info.get("end_time_d");
    String end_time_t = (String) info.get("end_time_t");
    Integer capability = (Integer) info.get("capability");
    List locationIds = (List) info.get("test_target");
    String st_str = String.join(" ", start_time_d, start_time_t);
    String et_str = String.join(" ", end_time_d, end_time_t);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    Date st = sdf.parse(st_str);
    Date et = sdf.parse(et_str);

    IPage<Resident> r = residentService.getResidentByLocationIdsPage(locationIds, "1", "1").get();
    if (r.getSize() == 0) {
      logger.info("选定位置无有效居民");
      return ApiResponse.ok();
    }

    executor.submit(() -> {
      int page = 0;
      while (new Date().before(et)) {
        logger.info("nowDate：" + new Date());
        logger.info("et：" + et);
        logger.info("st：" + st);
        logger.info("nowDate.before(et): " + new Date().before(et));
        logger.info("nowDate.after(st): " + new Date().after(st));
        if (!new Date().after(st)) {
          try {
            TimeUnit.MINUTES.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          continue;
        }
        page++;
        IPage<Resident> residentList = residentService.getResidentByLocationIdsPage(locationIds, String.valueOf(page), String.valueOf(capability)).get();
        for (Resident record : residentList.getRecords()) {
          logger.info("推送消息：" + record);
//          residentService.pushMsg(resid);
        }
        try {
//          TimeUnit.HOURS.sleep(1);
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    return ApiResponse.ok();
  }

//  /**
//   * 查询社工信息
//   * @return API response json
//   */
//  @GetMapping(value = "/work/info")
//  ApiResponse info(@RequestParam String open_id) {
//    logger.info("/work/info get request");
//    Optional<Work> resid = workService.getWorkByOpenId(open_id);
//
//    if (!resid.isPresent())
//      return ApiResponse.ok("该微信账号未绑定身份信息", null);
//
//    return ApiResponse.ok(resid);
//  }
//
//  /**
//   * 更新社工信息
//   * 按open_id查找
//   * @return API response json
//   */
//  @PostMapping(value = "/work/update")
//  ApiResponse update(@RequestBody Work work) {
//    logger.info("/work/update post request");
//
//    if (workService.update(work))
//      return ApiResponse.ok();
//    else
//      return ApiResponse.error("更新失败");
//  }
  
}