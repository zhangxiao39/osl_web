package com.osl.common;

/**
 * 常数定义
 */
public class UtilConst {
	// 空文字
	public static final String EMPTY_STRING = "";
	public static final String IMPORT_TEMPLATE_FOLER_PATH = "template/";
	public static final String DEFAULT_PWD = "123456";
	public static final int DEFAULT_PAGESIZE = 10;
	public static final int COURSESELECTED_PAGESIZE = 5;
	public static final int PAGESIZE_5 = 5;
	public static final int NO_PAGENO = 1;
	
	//生成表ID用标识
	//纳品表
	public static final String TABLE_KEY_TO_ENTRY = "NPC";
	//纳品明细表
	public static final String TABLE_KEY_TO_ENTRY_DETAIL = "NPD";
	//入库表
	public static final String TABLE_KEY_TO_INPUT = "RKC";
	//入库明细表
	public static final String TABLE_KEY_TO_INPUT_DETAIL = "RKD";
	//库存表
	public static final String TABLE_KEY_TO_STOCK = "KCC";
	//出库表
	public static final String TABLE_KEY_TO_OUTPUT = "CKC";
	//出库明细表
	public static final String TABLE_KEY_TO_OUTPUT_DETAIL = "CKD";
	//返品表
	public static final String TABLE_KEY_TO_RETURN = "FPC";
	//返品明细表
	public static final String TABLE_KEY_TO_RETURN_DETAIL = "FPD";
	//结算表
	public static final String TABLE_KEY_TO_BALANCE = "JSC";
	//结算明细表
	public static final String TABLE_KEY_TO_BALANCE_DETAIL = "JSD";
	// 商品表
	public static final String TABLE_KEY_TO_GOODS = "CP";
	
	/**
	 * 纳品9种状态常量定义
	 */
	//申请中
	public static final int ENTRY_STATUS_APPLYING = 1;	
	//库存不足
	public static final int ENTRY_STATUS_INVERNTORY_SHORT = 2;
	//商家承认完了
	public static final int ENTRY_STATUS_OPERATOR_ADMIT = 3;
	//取消
	public static final int ENTRY_STATUS_CANCLE = 4;
	//运输中
	public static final int ENTRY_STATUS_TRANSPORTING = 5;
	//入库完了
	public static final int ENTRY_STATUS_INPUT_COMPLETE = 6;
	//审核检品中
	public static final int ENTRY_STATUS_CHECKING = 7;
	//商家承认完了
	public static final int ENTRY_STATUS_MERCHANT_ADMIT = 8;
	//纳品完成
	public static final int ENTRY_STATUS_ENTRY_COMPLETE = 9;
}
