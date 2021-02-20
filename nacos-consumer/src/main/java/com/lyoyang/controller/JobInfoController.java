package com.lyoyang.controller;

import com.alibaba.fastjson.JSONObject;
import com.xxl.job.api.XXLJobInfoApi;
import com.xxl.job.api.entity.Result;
import com.xxl.job.api.entity.XxlJobInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/iobInfo")
public class JobInfoController {

    @Resource
    private XXLJobInfoApi xxlJobInfoApi;

    @RequestMapping("/add")
    public String add() {
        XxlJobInfoVo xxlJobInfoVo = new XxlJobInfoVo();
        xxlJobInfoVo.setJobGroup(1);
        xxlJobInfoVo.setJobDesc("测试任务002");
        xxlJobInfoVo.setAuthor("brian");
        xxlJobInfoVo.setScheduleType("CRON");
        xxlJobInfoVo.setScheduleConf("* 0/1 * * * ?");
        xxlJobInfoVo.setGlueType("BEAN");
        xxlJobInfoVo.setExecutorHandler("demoJobHandler");
        xxlJobInfoVo.setExecutorRouteStrategy("FIRST");
        xxlJobInfoVo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfoVo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfoVo.setExecutorTimeout(0);
        xxlJobInfoVo.setExecutorFailRetryCount(0);
        xxlJobInfoVo.setGlueRemark("GLUE代码初始化");
        Result result = xxlJobInfoApi.add(xxlJobInfoVo);
        System.out.println(JSONObject.toJSONString(result));
        return "OK";
    }

}
