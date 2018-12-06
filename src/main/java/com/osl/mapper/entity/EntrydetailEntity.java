package com.osl.mapper.entity;

import java.util.Date;

import com.osl.common.web.BaseEntity;

public class EntrydetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8846629377542417784L;

	private long id;
	private String detailId;
	private String sku;
	private int nums;
	private double packageSize;
	private int innerNums;
	private int innerGoodsNums;
	private String sendId;
	private long entryId;
	private int inputNums;
	private int inputDiff;
	private Date produceTime;
	private int maxNums;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public long getEntryId() {
		return entryId;
	}

	public void setEntryId(long entryId) {
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

	public int getMaxNums() {
		return maxNums;
	}

	public void setMaxNums(int maxNums) {
		this.maxNums = maxNums;
	}

}
