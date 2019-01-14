package com.osl.mapper.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.osl.common.web.BaseEntity;

public class EntrydetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8846629377542417784L;

	private String detailId;
	private String goodsId;
	private int nums;
	private double packageSize;
	private int innerNums;
	private int innerGoodsNums;
	private String sendId;
	private String entryId;
	private int inputNums;
	private int inputDiff;
	private Date produceTime;
	private Date validityTime;
	private int maxNums;
	private int status;
	private Timestamp newDate;
	private Timestamp updateDate;
	private int deleteFlg;
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public double getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(double packageSize) {
		this.packageSize = packageSize;
	}
	public int getInnerNums() {
		return innerNums;
	}
	public void setInnerNums(int innerNums) {
		this.innerNums = innerNums;
	}
	public int getInnerGoodsNums() {
		return innerGoodsNums;
	}
	public void setInnerGoodsNums(int innerGoodsNums) {
		this.innerGoodsNums = innerGoodsNums;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getEntryId() {
		return entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	public int getInputNums() {
		return inputNums;
	}
	public void setInputNums(int inputNums) {
		this.inputNums = inputNums;
	}
	public int getInputDiff() {
		return inputDiff;
	}
	public void setInputDiff(int inputDiff) {
		this.inputDiff = inputDiff;
	}
	public Date getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(Date produceTime) {
		this.produceTime = produceTime;
	}
	public Date getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}
	public int getMaxNums() {
		return maxNums;
	}
	public void setMaxNums(int maxNums) {
		this.maxNums = maxNums;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getNewDate() {
		return newDate;
	}
	public void setNewDate(Timestamp newDate) {
		this.newDate = newDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public int getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
	
	
	

}
