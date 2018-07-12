package com.test.inv.adapter.util;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.test.inv.service.ServiceObject;

@Service
public class SignatureUtil extends ServiceObject{

	@Value("${mof.appKey}")
	private String apiKey;

	public String signature(String params) {
		if (StringUtils.isEmpty(params)) return null;

		Map<String,Object> keyValues = new TreeMap<String,Object>();
		List<String> list = Arrays.asList(params.split("&"));
		for (String str : list) {
			String[] splitStr = str.split("=");
			keyValues.put(splitStr[0],splitStr[1]);
		}

		return signature(keyValues);

	}
	
	public String signature(Map<String,Object> keyValues) {
		StringBuilder buffer = new StringBuilder();
		for (Map.Entry<String,Object> entry : keyValues.entrySet()) {
			buffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		buffer.deleteCharAt(buffer.length()-1);
		String data = buffer.toString();
		logger.debug("sorting result : {}",data);

		String result = null;
		try {
			byte[] apiKeyByte = apiKey.getBytes("UTF-8");
			byte[] dataByte = data.getBytes("UTF-8");
			result = hamcsha1(dataByte, apiKeyByte) ;
			logger.debug("signature result : {}",result);
		}catch(Throwable e) {
			logger.error("signature fail",e);
		}
		return result;

	}

	private String hamcsha1(byte[] data, byte[] key) throws Exception {
		SecretKeySpec signingKey 
			= new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		return Base64
				.getEncoder()
				.encodeToString(mac.doFinal(data));
	}


}
