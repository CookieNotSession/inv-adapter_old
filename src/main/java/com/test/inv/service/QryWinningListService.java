package com.test.inv.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.test.inv.adapter.obj.MOFParamEnum;
import com.test.inv.adapter.util.UUIDUtil;
import com.test.inv.util.HttpAPIService;
import com.test.inv.util.HttpResult;

@RefreshScope
@Service
public class QryWinningListService extends ServiceObject {
	
	@Value("${mof.appID}")
	private String appID;
	
	@Value("${mof.serverUrl}")
	private String serverUrl;	
	
	@Value("${mof.api.QryWinningList}")
	private String apiUrl;	
	
    @Resource
    private HttpAPIService httpAPIService;
	
	public String doAction(String invTerm) throws Exception {
		String url = serverUrl + apiUrl;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(MOFParamEnum.versionQryWinningList.getName(), 
				MOFParamEnum.versionQryWinningList.getValue());
		map.put(MOFParamEnum.actionQryWinningList.getName(), 
				MOFParamEnum.actionQryWinningList.getValue());
		map.put(MOFParamEnum.appID.getName(), 
				appID);
		map.put(MOFParamEnum.UUID.getName(), 
				UUIDUtil.generate());		
		map.put(MOFParamEnum.invTerm.getName(), 
				invTerm);			
        HttpResult httpResult = httpAPIService.doPost(url,map);
        logger.info("Http result => code:[{}] ,body:[{}]",httpResult.getCode(),httpResult.getBody());
        return httpResult.getBody();
	}
	


}
