package com.osl.mapper.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class BalanceEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4448412349604280810L;
	
	private long id;
	private String balance_id;
	private Date date;
	private BigDecimal total;
	private BigDecimal output_price;
	private BigDecimal input_price;
	private BigDecimal keep_price;
	private long business_id;
	private long warehouse_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBalance_id() {
		return balance_id;
	}

	public void setBalance_id(String balance_id) {
		this.balance_id = balance_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getOutput_price() {
		return output_price;
	}

	public void setOutput_price(BigDecimal output_price) {
		this.output_price = output_price;
	}

	public BigDecimal getInput_price() {
		return input_price;
	}

	public void setInput_price(BigDecimal input_price) {
		this.input_price = input_price;
	}

	public BigDecimal getKeep_price() {
		return keep_price;
	}

	public void setKeep_price(BigDecimal keep_price) {
		this.keep_price = keep_price;
	}

	public long getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(long business_id) {
		this.business_id = business_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

}
