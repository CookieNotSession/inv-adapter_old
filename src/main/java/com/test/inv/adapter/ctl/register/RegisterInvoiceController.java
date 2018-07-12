package com.test.inv.adapter.ctl.register;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.inv.adapter.obj.QryWinningListObj;

import io.swagger.annotations.Api;

@Api(tags = "inv-register")
@RestController
@RequestMapping("/api/inv-register")
public class RegisterInvoiceController {
	Logger logger = Logger.getLogger(RegisterInvoiceController.class);

	@PostMapping("/GeneralCarrierReg")
	@ResponseStatus(HttpStatus.OK)
	public void generalCarrierReg(
			@RequestBody QryWinningListObj qryWinningListObj) {
		logger.info("obj : "+ qryWinningListObj);
		
	}
}
