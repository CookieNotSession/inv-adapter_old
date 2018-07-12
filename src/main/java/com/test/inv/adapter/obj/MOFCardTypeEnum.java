package com.test.inv.adapter.obj;

public enum MOFCardTypeEnum {
	
	MobileBarcode("3J0002","手機條碼"),
	EasyCard("1K0001","悠遊卡"),
	OneCard("1H0001","一卡通"),
	CitizenCard("CQ0001","自然人憑證條碼");
	
	
	
	private String name;

	private String desc;

	private MOFCardTypeEnum(String name,String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
		
	public String getDesc() {
		return desc;
	}

}
