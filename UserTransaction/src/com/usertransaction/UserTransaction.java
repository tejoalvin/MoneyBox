package com.usertransaction;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "usertransaction")
public class UserTransaction implements Serializable {

   private static final long serialVersionUID = 1L;
   private int transactionID;
   private double transactionAmount;
   private String currencyCode;
   private Date transactionDate;
   private String desc; //optional
   private Date createdDate;
   private Date modifiedDate;
   private String merchant; //optional

   public UserTransaction(){
	   
   }
   
   //for update
   public UserTransaction(int transactionID, double transactionAmount, String currencyCode, String description, String merchant){
	   this.transactionAmount = transactionAmount;
	   this.currencyCode = currencyCode;
	   this.transactionID = transactionID;
	   this.desc = description;
	   this.merchant = merchant;
   }
   
   public UserTransaction(double transactionAmount, String currencyCode, Date transactionDate, Date createdDate, Date modifiedDate){
      this.transactionAmount = transactionAmount;
      this.currencyCode = currencyCode;
      this.transactionDate = transactionDate;
      this.createdDate = createdDate;
      this.modifiedDate = modifiedDate;
   }

	public int getTransactionID() {
		return transactionID;
	}
	
	@XmlElement
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
	@XmlElement
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	
	@XmlElement
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	@XmlElement
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDesc() {
		return desc;
	}

	@XmlElement
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	@XmlElement
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	@XmlElement
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getMerchant() {
		return merchant;
	}
	
	@XmlElement
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public void printData(){
		System.out.println("Transaction ID: " + this.transactionID);
		System.out.println("Merchant: " + this.merchant);
		System.out.println("Amount: " + this.transactionAmount);
		System.out.println("Currency: " + this.currencyCode);
		System.out.println("transactionDate: " + this.transactionDate.toString());
		System.out.println("Created Date: " + this.createdDate.toString());
		System.out.println("Modified Date: " + this.modifiedDate.toString());
		System.out.println("Description: " + this.desc);
		System.out.println("");
	}
	
}