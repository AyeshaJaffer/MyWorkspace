package com.assignment.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.assignment.entity.PurchaseItem;
import com.assignment.entity.PurchaseOrderHeader;
import com.assignment.utils.Response;






@Repository
public class PurchaseOrderDao {
	
	private Configuration  configuration = new Configuration().configure("hibernate.cfg.xml");
	private SessionFactory sf=configuration.buildSessionFactory();
	private Session s;
	private Transaction tx;
	

                
                Response response=new Response();
                
                //adding only po
                public Response addPurchaseOrder(PurchaseOrderHeader purchase){
                                try{
                                                s=sf.openSession();
                                                tx=s.beginTransaction();
                                                s.save(purchase);
                                                response.setStatus("200");
                                                response.setMessage("succesfull");
                                                tx.commit();
                                }
                                catch (Exception e) {
                                                                System.err.println("Exception : " + e.getMessage());

                                                                response.setStatus("500");
                                                                response.setMessage("unsuccesfull");
                                                                tx.rollback();
                                                                
                                                } finally {
                                                                s.close();
                                                }
                                                return response;
                }
                
                //list of only po
                @SuppressWarnings({ "unchecked", "deprecation" })
                public List<PurchaseOrderHeader> GetPurchaseOrder(){
                                s=sf.openSession();
                                tx=s.beginTransaction();
                               // List<PurchaseOrderHeader> poList=s.createSQLQuery("select * from PurchaseOrderHeader").list();
                                
                                
                                List<PurchaseOrderHeader> polist=s.createCriteria(PurchaseOrderHeader.class).list();
/*                                List<PurchaseOrderHeader> polist=s.createSQLQuery("select itemname,itemprice,paymentterms,vendor,status,companycode from PuchaseItem left join  PurchaseOrderHeader on PuchaseItem.ponumber=PurchaseOrderHeader.ponumber").list();
*/                                tx.commit();
                               
                                return polist;
                }
               
                
                //list of only pi
                @SuppressWarnings({ "unchecked", "deprecation" })
                public List<PurchaseItem> getPurchaseItem(){
                                s=sf.openSession();
                               
/*                                List<PurchaseItem> items=s.createSQLQuery("select itemname,itemprice  from PuchaseItem").list();
*/                               List<PurchaseItem> items= s.createCriteria(PurchaseItem.class).list();
                              
                                return items;
                }
                
                //adding both po and pi
                public Response saveAll(PurchaseOrderHeader purchaseorder){
                                try{
                                	s = this.sf.openSession();
                               	 tx = s.beginTransaction();
                               		s.save(purchaseorder);
                                       for (int i=0; i<purchaseorder.getItemList().size();i++)
                                       {
                                                 purchaseorder.getItemList().get(i).setPurchaseorder(purchaseorder);
                                                 s.save(purchaseorder.getItemList().get(i));
                                       }
                                       response.setStatus("200");
                                       response.setMessage("succesfull");
                                       tx.commit();
                                      
                                       return response;
                               }
                              
                                catch(Exception e){
                                                
                                                response.setStatus("500");
                                                response.setMessage("unsuccesfull");
                                                tx.rollback();
                                                
                                }
                                finally{
                                                s.close();
                                }
                                return response;
                }
                
                //editing only po
                public Response editpurchaseOrder(PurchaseOrderHeader purchase){
                                try{
                                                s=sf.openSession();
                                                tx=s.beginTransaction();
                                                PurchaseOrderHeader orderEdit=s.get(PurchaseOrderHeader.class, purchase.getPonumber());
                                             
                                                if(purchase.getStatus()!=null)
                                                                orderEdit.setStatus(purchase.getStatus());
                                                if(purchase.getVendor()!=null)orderEdit.setVendor(purchase.getVendor());
                                
                                                s.update(orderEdit);
                                                tx.commit();
                                                response.setStatus("200");
                                                response.setMessage("updated");
                                }
                                catch (Exception e) {
                                                tx.rollback();
                                                response.setStatus("500");
                                                response.setMessage("not updated");
                                } finally {
                                                s.close();
                                }
                                return response;
                }
                
                //deleting po and pi together
                public Response deleteOrder(int ponumber){
                                try{
                                                s=sf.openSession();
                                
                                tx=s.beginTransaction();
                                
                               
                PurchaseOrderHeader purchase=s.get(PurchaseOrderHeader.class, ponumber);
                s.delete(purchase);
                tx.commit();
                response.setStatus("200");
                response.setMessage("deleted");
                                }
                                catch(Exception e){
                                                tx.rollback();
                                                response.setStatus("500");
                                                response.setMessage("not deleted");
                                }
                                finally
                                {
                                	s.close();
                                }
                return response;
                }
                
               
               
                
                
                

                
                //read both header and pi
                @SuppressWarnings("unchecked")
				public List<PurchaseOrderHeader> Allheaders()
                {
                                                s = sf.openSession();
                                                tx  = s.beginTransaction();
                                              
List<PurchaseOrderHeader> head =s.createSQLQuery("select PurchaseOrderHeader.ponumber,paymentterms, status,vendor,itemId,itemname,itemprice from PurchaseOrderHeader left outer join PuchaseItem  on PurchaseOrderHeader.ponumber=PuchaseItem.ponumber").list();
                                                
                                                System.err.println("PurchaseDao.Allheaders="+head);
                                                tx.commit();
                                               s.close();
                                                return head;
                }

                
                //update both header and pi
                public Response update(int itemId,String itemname,String itemprice,PurchaseOrderHeader purchaseorder)
                {
                	try{
                		
                		 s = sf.openSession();
                         tx = s.beginTransaction();
                
                  Query query=s.createQuery("update PuchaseItem inner join PurchaseOrderHeader on PurchaseOrderHeader.ponumber=PuchaseItem.ponumber set PurchaseOrderHeader.paymentterms='paymentterms',PuchaseItem.itemprice='itemprice' where PuchaseItem.ponumber='ponumber' ");
                	query.executeUpdate();
                	 tx.commit();
                     response.setMessage("Successfull");
                     response.setStatus("200");
                   
                     return response;
                	}
                	catch(Exception e){
                		  System.err.println("Exception : " + e.getMessage());
                          response.setMessage("UnSuccessfull");
                          response.setStatus("500");
                         return response;
                	}
                	finally{
                		s.close();
                		
                	}

                }  
                
                           	
                
                //deleting items
               
                public Response DeleteItems(int itemId) 
                {
                try 
                {
                                s = sf.openSession();
                                tx = s.beginTransaction();
                                PurchaseItem purchase=s.get(PurchaseItem.class,itemId);
                                s.delete(purchase);
                                tx.commit();
                                response.setStatus("200");
                                response.setMessage("deleted");
                               
                                return response;
                } 
                catch (Exception e)
                {
                                System.err.println("Exception : " + e.getMessage());
                                response.setMessage("UnSuccessfull");
                                response.setStatus("500");
                               return response;
               }finally{
            	   s.close();
               }
           
                }

             
				public Response updateById(PurchaseOrderHeader purchase)
    			{
                	try{
    				s=sf.openSession();
    				tx = s.beginTransaction();
    				 
    					
    			PurchaseOrderHeader header=(PurchaseOrderHeader) s.createSQLQuery("UPDATE puchaseitem,purchaseorderheader  SET puchaseitem.itemprice=itemprice,puchaseitem.itemname=itemname,purchaseorderheader.status=status,purchaseorderheader.vendor=vendor,purchaseorderheader.companycode=companycode where puchaseitem.ponumber=purchaseorderheader.ponumber and purchaseorderheader.ponumber=purchaseorderheader.ponumber ");	 
                    s.save(header);
    				tx.commit();
    				response.setStatus("200");
                    response.setMessage("successfull");
                    return response;
    				
    			}
                	catch(Exception e)
                	{
                		 response.setStatus("500");
                         response.setMessage("unsuccesfull");
                         tx.rollback();
                         return response;
                	}
                	
                	finally
                	{
                		s.close();
                	}
    			}
              
                
                //updating/editing pi only
                
                public Response editPurchaseItem(PurchaseItem purchaseItem){
                    try{
                                    s=sf.openSession();
                                    tx=s.beginTransaction();
                                    PurchaseItem itemEdit=s.get(PurchaseItem.class, purchaseItem.getItemId());
                                    if(purchaseItem.getItemname()!=null)
                                                    itemEdit.setItemname(purchaseItem.getItemname());
                                    if(purchaseItem.getItemprice()!=null)
                                                    itemEdit.setItemprice(purchaseItem.getItemprice());
                                    
                                    s.update(itemEdit);
                                    tx.commit();
                                    response.setStatus("200");
                                    response.setMessage("items edited");
                    }
                    catch(Exception e){
                                    tx.rollback();
                                    response.setStatus("500");
                                    response.setMessage("items not updated");
                    }
                    finally{
                                    s.close();              
                    }
                    return response;
    }

                
                @SuppressWarnings({ "deprecation", "unchecked" })
				public List<PurchaseOrderHeader> getPOandPI(int ponumber){
            		
                	try
                	{
                		s= sf.openSession();
                
            		tx=s.beginTransaction();
/*            		List<PurchaseOrderHeader> list= s.createSQLQuery("select * from purchaseorderheader where ponumber=ponumber").list();
*/          
            	Criteria ct=s.createCriteria(PurchaseOrderHeader.class);
                 ct.add(Restrictions.eq("ponumber", ponumber));
                 List<PurchaseOrderHeader> list=ct.list();
                 tx.commit();
                return list;
                	
                	}
                	finally{
                		s.close();
                	}
                }	
                	
          @SuppressWarnings("unused")
		public Response Updates(PurchaseOrderHeader header,PurchaseItem item)
                    {
                                
                                                try
                                                {
                                                	 s = sf.openSession();
                                                     tx = s.beginTransaction();
                                                                Query query1=s.createSQLQuery("update PurchaseOrderHeader set PurchaseOrderHeader.status=status,PurchaseOrderHeader.vendor=vendor,PurchaseOrderHeader.paymentterms=paymentterms where PurchaseOrderHeader.ponumber=ponumber");
                                                query1.executeUpdate();
                                               
                                                Query query2=s.createSQLQuery("update PuchaseItem set PuchaseItem.itemname=itemname where PuchaseItem.ponumber=ponumber");
              
                  
                                               // query2.executeUpdate();
                                               // s.save(query2);
                                                response.setMessage("Successfull");
                                response.setStatus("200");
                                s.close();

                                return response;
                                                }
                                                catch(Exception e){
                                                                e.getMessage();
                               tx.rollback();
                                                                response.setMessage("UnSuccessfull");
                                                response.setStatus("500");
                                                return response;
                                                }
                    finally
                    {
                    	s.close();

                
}}
            	
					
}
            	


