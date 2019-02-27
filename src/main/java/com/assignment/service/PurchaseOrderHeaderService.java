package com.assignment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.assignment.dao.PurchaseOrderDao;
import com.assignment.entity.PurchaseItem;
import com.assignment.entity.PurchaseOrderHeader;
import com.assignment.utils.Response;


@Service
@Transactional
public class PurchaseOrderHeaderService {
	
	PurchaseOrderDao dao=new PurchaseOrderDao();
	
	
	
	public Response addPurchaseOrder(PurchaseOrderHeader purchase)
	{
		return dao. addPurchaseOrder(purchase);
		
	}
	
	
	public List<PurchaseOrderHeader> getAllPo() {
		
		return dao.GetPurchaseOrder();
	}
	
	
	 public List<PurchaseItem> getPurchaseItem()
	 {
		 return dao.getPurchaseItem();
		 
	 }
	
	 
	  public Response saveAll(PurchaseOrderHeader purchaseOrderHeader)
	  {
		  return dao.saveAll(purchaseOrderHeader);
	  }
	  
	  
	  public Response editpurchaseOrder(PurchaseOrderHeader purchase)
	  {
		  return dao.editpurchaseOrder(purchase);
		  
	  }
	  
	  public Response deleteOrder(int ponumber)
	  {
		  return dao.deleteOrder(ponumber);
	  }
	  
	  public Response editPurchaseItem(PurchaseItem purchaseItem)
	  {
		  return dao.editPurchaseItem(purchaseItem);
	  }
	  
	  

      public List<PurchaseOrderHeader> Allheaders()
      {
    	  return dao.Allheaders();
      }
	
      public Response update(int itemId,String itemname,String itemprice,PurchaseOrderHeader purchaseorder)
      {
		return dao.update(itemId, itemname, itemprice, purchaseorder);
    	  
      }
      
    
     
      
      
      public Response DeleteItems(int itemId) 
      {
    	  return dao.DeleteItems(itemId);
      }
      
      
      public List<PurchaseOrderHeader> getPOandPI(int ponumber)
      {
    	  return dao.getPOandPI(ponumber);
      }
      
      
      public Response updateById(PurchaseOrderHeader purchase)
      {
    	 return dao.updateById(purchase); 
      }
      
      public Response Updates(PurchaseOrderHeader header,PurchaseItem item)
      {
    	  return dao.Updates(header, item);
      }
      }

