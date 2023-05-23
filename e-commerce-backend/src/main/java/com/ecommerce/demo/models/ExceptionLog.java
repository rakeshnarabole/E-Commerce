package com.ecommerce.demo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ExceptionLog")
public class ExceptionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;
	private Date createdDate;
	private String exceptionMessage;
	private int attribute;
	private String attritubeType;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public int getAttribute() {
		return attribute;
	}

	public void setAttribute(int attribute) {
		this.attribute = attribute;
	}

	public String getAttritubeType() {
		return attritubeType;
	}

	public void setAttritubeType(String attritubeType) {
		this.attritubeType = attritubeType;
	}

	public ExceptionLog(int logId, Date createdDate, String exceptionMessage, int attribute, String attritubeType) {
		super();
		this.logId = logId;
		this.createdDate = createdDate;
		this.exceptionMessage = exceptionMessage;
		this.attribute = attribute;
		this.attritubeType = attritubeType;
	}

	public ExceptionLog() {
		super();
		// TODO Auto-generated constructor stub
	}

}
