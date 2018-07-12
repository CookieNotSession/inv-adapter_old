package com.test.inv.adapter.obj;

public enum MOFParamEnum {
	
	versionQryWinningList("version","0.2"),
	actionQryWinningList("action","QryWinningList"),
	versionQryInvHeader("version","0.4"),
	actionQryInvHeader("action","qryInvHeader"),
	versionCarrierInvChk("version","0.4"),
	actionCarrierInvChk("action","carrierInvChk"),
	versionQryCarrierAgg("version","1.0"),
	actionQryCarrierAgg("action","qryCarrierAgg"),	
	
	invTerm("invTerm",null),
	UUID("UUID",null),
	appID("appID",null),
	invNum("invNum",null),
	invDate("invDate",null),
	generation("generation","V2"),
	invType("type",null),

	timeStamp("timeStamp",null),
	expTimeStamp("expTimeStamp",null),
	startDate("startDate",null),
	endDate("endDate",null),
	onlyWinningInv("onlyWinningInv",null),
	uuid("uuid",null),
	serial("serial",null),
	signature("signature",null),

	cardType("cardType",null),
	cardNo("cardNo",null),
	cardEncrypt("cardEncrypt",null);
	
	
	
	private String name;

	private String value;
	
	private MOFParamEnum(String name,String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}	
}
