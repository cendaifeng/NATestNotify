package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.config.Query;
import com.tencent.wxcloudrun.dao.ResidentMapper;
import com.tencent.wxcloudrun.model.Resident;
import com.tencent.wxcloudrun.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ResidentServiceImpl extends ServiceImpl<ResidentMapper, Resident> implements ResidentService {

  final ResidentMapper residentMapper;

  public static String sendPost(String url, String param) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      URLConnection conn = realUrl.openConnection();
      // 设置通用的请求属性
      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent",
              "Mozilla/5.0 (Windows NT 6.1; Win64; x64)");

      // 发送POST请求必须设置如下两行
      conn.setDoOutput(true);
      conn.setDoInput(true);

      // 获取URLConnection对象对应的输出流
      out = new PrintWriter(conn.getOutputStream());

      // 发送请求参数
      out.print(param);

      // flush输出流的缓冲
      out.flush();

      // 定义BufferedReader输入流来读取URL的响应
      in = new BufferedReader(
              new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println("发送 POST 请求出现异常！"+e);
      e.printStackTrace();
    }
    //使用finally块来关闭输出流、输入流
    finally{
      try{
        if(out!=null){
          out.close();
        }
        if(in!=null){
          in.close();
        }
      }
      catch(IOException ex){
        ex.printStackTrace();
      }
    }
    return result;
  }

  public ResidentServiceImpl(@Autowired ResidentMapper residentMapper) {
    this.residentMapper = residentMapper;
  }

  @Override
  public Optional<Resident> getResident(String ic_card_no) {
    return Optional.ofNullable(residentMapper.getResident(ic_card_no));
  }

  @Override
  public Optional<Resident> getResidentByOpenId(String open_id) {
    return Optional.ofNullable(residentMapper.getResidentByOpenId(open_id));
  }

  @Override
  public Optional<List<Resident>> getResidentByLocationIds(List location_ids) {
    List<Resident> residents = this.list(new QueryWrapper<Resident>().in("location_id", location_ids));
    return Optional.ofNullable(residents);
  }

  @Override
  public Optional<IPage<Resident>> getResidentByLocationIdsPage(List location_ids, String page, String limit) {
    IPage<Resident> page1 = new Query<Resident>().getPage(new HashMap<String, Object>() {{
      put("page", page);
      put("limit", limit);
    }});
    QueryWrapper<Resident> wrapper = new QueryWrapper<Resident>().in("location_id", location_ids);
    IPage<Resident> ipage = this.page(page1, wrapper);
    return Optional.ofNullable(ipage);
  }

  @Override
  public boolean register(Resident resident) {
    if (super.save(resident)) {
      log.info("新用户注册啦！ "+resident);
      return true;
    }
    return false;
  }

  @Override
  public boolean update(Resident resident) {
    UpdateWrapper<Resident> wrapper = new UpdateWrapper<>();
    wrapper.eq("open_id", resident.getOpen_id());
    return super.update(resident, wrapper);
  }

  @Override
  public boolean updateByIc(Resident resident) {
    UpdateWrapper<Resident> wrapper = new UpdateWrapper<>();
    wrapper.eq("ic_card_no", resident.getIc_card_no());
    return super.update(resident, wrapper);
  }

  @Override
  public boolean pushMsg(String open_id) {

    return false;
  }

}
