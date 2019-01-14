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
	
	/**
	 * 删除标识
	 * 0:未删除
	 * 1:删除
	 */
	//未删除
	public static final int DELETE_FLAG_FALSE = 0;
	//删除
	public static final int DELETE_FLAG_TRUE = 1;
	
	/**
	 * 商品品标识
	 * 0:良品
	 * 1:不良品
	 * 2:返品
	 */
	//良品
	public static final int GOODS_TYPE_GOOD = 0;
	//不良品
	public static final int GOODS_TYPE_BAD = 1;
	//返品
	public static final int GOODS_TYPE_BACK = 2;
	
	/**
	 * 组合品标识
	 * 0:单品
	 * 1:组合品
	 */
	//单品
	public static final int COMBINATION_TYPE_FALSE = 0;
	//组合品
	public static final int COMBINATION_TYPE_TRUE = 1;
	
	/**
	 * 出库时库存是否充足标识
	 * 0:不充足
	 * 1:充足
	 */
	//不充足
	public static final int OUTPUT_INSUFFICIENT_FALSE = 0;
	//充足
	public static final int OUTPUT_INSUFFICIENT_TRUE = 1;
	
	/**
	 * 出库状态常量定义
	 * 
	 * 0:出库请求
	 * 1:请求取消
	 * 2:出库中
	 * 3:出库取消
	 * 4:出库完成
	 * 5:返品
	 */
	//出库请求
	public static final int OUTPUT_STATUS_REQUEST = 0;	
	//请求取消
	public static final int OUTPUT_STATUS_REQUEST_CANCEL = 1;
	//出库中
	public static final int OUTPUT_STATUS_ING = 2;
	//出库取消
	public static final int OUTPUT_STATUS_CANCLE = 3;
	//出库完成
	public static final int OUTPUT_STATUS_FINISH = 4;
	//返品
	public static final int OUTPUT_STATUS_BACK = 5;
	
	/**
	 * 出库类型定义
	 * 0:贩卖
	 * 1:废弃
	 * 2:商品返回
	 */
	//贩卖
	public static final int OUTPUT_TYPE_SELL = 0;
	//废弃
	public static final int OUTPUT_TYPE_DISCARD = 1;
	//商品返回
	public static final int OUTPUT_TYPE_BACK = 2;
}
