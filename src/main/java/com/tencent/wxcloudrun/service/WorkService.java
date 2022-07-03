package com.tencent.wxcloudrun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tencent.wxcloudrun.model.Resident;

import java.util.List;
import java.util.Optional;

public interface WorkService extends IService<Resident> {

  boolean newTask(Resident resident);

}
