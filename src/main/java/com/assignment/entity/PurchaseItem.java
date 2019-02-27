package com.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="PuchaseItem")
public class PurchaseItem {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name="itemId")
	private int itemId;
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ponumber")
	private PurchaseOrderHeader purchaseorder;  

	
	@Column(name="itemname")
	private String itemname;
	
	@Column(name="itemprice")
	private String itemprice;

	

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public PurchaseOrderHeader getPurchaseorder() {
		return purchaseorder;
	}

	public void setPurchaseorder(PurchaseOrderHeader purchaseorder) {
		this.purchaseorder = purchaseorder;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemprice() {
		return itemprice;
	}

	public void setItemprice(String itemprice) {
		this.itemprice = itemprice;
	}
	
	
	
	public PurchaseItem() {
		
	}
	

	public PurchaseItem(int itemId, PurchaseOrderHeader purchaseorder, String itemname, String itemprice) {
		
		this.itemId = itemId;
		this.purchaseorder = purchaseorder;
		this.itemname = itemname;
		this.itemprice = itemprice;
	}
	

}
