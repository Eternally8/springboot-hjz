package com.xxljob.controller;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;


@Component
public class DemoJobHandler {

	@XxlJob(value = "lalala")
	public ReturnT<String> lalala(String param) throws Exception {
		XxlJobLogger.log("startAllFlinkTask begin");

		XxlJobLogger.log("startAllFlinkTask end");
		return ReturnT.SUCCESS;
	}

}
