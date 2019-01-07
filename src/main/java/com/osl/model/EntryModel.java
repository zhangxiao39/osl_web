package com.osl.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.osl.common.web.BaseModel;

public class EntryModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -557330886626093237L;

	private long id;
	private String entryId;
	private Date operTime;
	private Date completeTime;
	private int skuNums;
	private int goodsNums;
	private int inputSkuNums;
	private int inputGoodsNums;
	private int status;
	private long businessId;
	private long warehouseId;
	private String oper;
	private MultipartFile applyFile;
	private Timestamp updateDate;
	
	//Ìí¼Ó×Ö¶Î
	private String entruIdStr;
	private String skuStr;
	private String barcode;
	private String goodsName;
	private List<String> skuList;
	private List<String >entruIdList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public int getSkuNums() {
		return skuNums;
	}
	public void setSkuNums(int skuNums) {
		this.skuNums = skuNums;
	}
	public int getGoodsNums() {
		return goodsNums;
	}
	public void setGoodsNums(int goodsNums) {
		this.goodsNums = goodsNums;
	}
	public int getInputSkuNums() {
		return inputSkuNums;
	}
	public void setInputSkuNums(int inputSkuNums) {
		this.inputSkuNums = inputSkuNums;
	}
	public int getInputGoodsNums() {
		return inputGoodsNums;
	}
	public void setInputGoodsNums(int inputGoodsNums) {
		this.inputGoodsNums = inputGoodsNums;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public MultipartFile getApplyFile() {
		return applyFile;
	}
	public void setApplyFile(MultipartFile applyFile) {
		this.applyFile = applyFile;
	}
	
	public String getEntruIdStr() {
		return entruIdStr;
	}
	public void setEntruIdStr(String entruIdStr) {
		this.entruIdStr = entruIdStr;
	}
	public String getSkuStr() {
		return skuStr;
	}
	public void setSkuStr(String skuStr) {
		this.skuStr = skuStr;
	}
	public List<String> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<String> skuList) {
		this.skuList = skuList;
	}
	public List<String> getEntruIdList() {
		return entruIdList;
	}
	public void setEntruIdList(List<String> entruIdList) {
		this.entruIdList = entruIdList;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	

}
