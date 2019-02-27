package com.assignment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="PurchaseOrderHeader")


public class PurchaseOrderHeader{
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name="ponumber")
    private int ponumber;
	
	@JsonBackReference
	@OneToMany( fetch = FetchType.LAZY,mappedBy = "purchaseorder",cascade = CascadeType.ALL) 
	private List<PurchaseItem> itemList;  
	 
	 
	
	


	public List<PurchaseItem> getItemList() {
		return itemList;
	}

	public int getPonumber() {
		return ponumber;
	}

	public void setPonumber(int ponumber) {
		this.ponumber = ponumber;
	}

	public void setItemList(List<PurchaseItem> itemList) {
		this.itemList = itemList;
	}

	public int getCompanycode() {
		return companycode;
	}

	public void setCompanycode(int companycode) {
		this.companycode = companycode;
	}

	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getPaymentterms() {
		return paymentterms;
	}

	public void setPaymentterms(String paymentterms) {
		this.paymentterms = paymentterms;
	}

	

	@Column(name="companycode",length=5,unique=true)
	private int companycode;
	
	@Column(name="status")
	private String status;
	
	@Column(name="vendor")
	private String vendor;
	
	@Column(name="paymentterms")
	private String paymentterms;
	
public PurchaseOrderHeader() {
		
	}

	
	
	
	public PurchaseOrderHeader(int ponumber, int companycode, String status, String vendor, String paymentterms) {
		
		this.ponumber = ponumber;
		this.companycode = companycode;
		this.status = status;
		this.vendor = vendor;
		this.paymentterms = paymentterms;
	}
	
}


