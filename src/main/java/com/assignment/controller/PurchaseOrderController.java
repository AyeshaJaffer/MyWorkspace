package com.assignment.controller;

import java.util.List;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.PurchaseItem;
import com.assignment.entity.PurchaseOrderHeader;
import com.assignment.service.PurchaseOrderHeaderService;
import com.assignment.utils.Response;



@RestController
@RequestMapping(value="/map",produces="application/json")
public class PurchaseOrderController {
	
	PurchaseOrderHeaderService purchaseService=new PurchaseOrderHeaderService();
	
	@RequestMapping(value="/first",produces="application/json",consumes="application/json")
	public Response addPurchaseOrder(@RequestBody PurchaseOrderHeader purchase){
		return purchaseService.addPurchaseOrder(purchase);
	}

	@RequestMapping(value="/getPO",method=RequestMethod.GET,produces="application/json",consumes="application/json")
	public List<PurchaseOrderHeader> GetPurchaseOrder()
	{
		return purchaseService.getAllPo();
	}
	@RequestMapping(value="/getPurchaseItems",method=RequestMethod.GET,produces="application/json")
    public List<PurchaseItem> getPurchaseItem()
    { 
		return purchaseService.getPurchaseItem();
    }
	

	@RequestMapping(value="/getAll",method=RequestMethod.GET,produces="application/json")

    public List<PurchaseOrderHeader> Allheaders()
    {
		return purchaseService.Allheaders();
    }
	
	@RequestMapping(value="/updatePO",method=RequestMethod.POST,produces="application/json",consumes="application/json")

    public Response editpurchaseOrder(@RequestBody PurchaseOrderHeader purchase)
    {
		return purchaseService.editpurchaseOrder(purchase);
    }
	
	@RequestMapping(value="/updatePI",method=RequestMethod.POST,produces="application/json",consumes="application/json")
    public Response editPurchaseItem(@RequestBody PurchaseItem purchaseItem)
    {
		return purchaseService.editPurchaseItem(purchaseItem);
    }
	
	@RequestMapping(value="/delete",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	 public Response deleteOrder(@RequestBody PurchaseOrderHeader purchase)
	{
		return purchaseService.deleteOrder(purchase.getPonumber());
	}
	@RequestMapping(value="/getItemandHeader",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public Response savePurchaseOrderHeader(@RequestBody PurchaseOrderHeader purchaseorder)
	{
		return purchaseService.saveAll(purchaseorder);
		
	}
	
	
	@RequestMapping(value="/updateAll",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public Response updateALL(@RequestBody PurchaseOrderHeader purchase,PurchaseItem purchase1)
    	
	{
		return purchaseService.updateById(purchase);
    }
	
	
	
	
	
	@RequestMapping(value="/deletingItems",method=RequestMethod.POST,produces="application/json")
	 public Response deleteItems(@RequestBody PurchaseItem purchase)
	 {
		 return purchaseService.DeleteItems(purchase.getItemId());
	 }
	
	
	@RequestMapping(value = "/getPurchaseById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PurchaseOrderHeader> getFilters(@RequestBody PurchaseOrderHeader purchase) {
		return purchaseService.getPOandPI(purchase.getPonumber());
	}
	
	@RequestMapping(value="/updating",method=RequestMethod.POST,produces="application/json",consumes="application/json")
	public Response Updates(@RequestBody PurchaseOrderHeader header,PurchaseItem item)
    	
	{
		return purchaseService.Updates(header, item);
    }
	
}