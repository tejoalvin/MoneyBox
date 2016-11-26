package com.usertransaction;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/UserTransactionService")
public class UserTransactionService {

   TransactionDao userTransactionDao = new TransactionDao();

   @GET
   @Path("/transaction")
   @Produces(MediaType.APPLICATION_XML)
   public List<UserTransaction> getAllTransaction(){
      return userTransactionDao.getAllTransaction();
   }	
   
   @POST
   @Path("/update")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public UserTransaction updateTransaction(@FormParam("id") int id, @FormParam("amount") double amount, @FormParam("currency") String currency, @FormParam("description") String desc, @FormParam("merchant") String merchant, @Context HttpServletResponse servletResponse) throws IOException{
	   UserTransaction uT = new UserTransaction(id,amount, currency, desc, merchant);
	   return userTransactionDao.updateTransaction(uT);
   }
   
//   @POST
//   @Path("/transaction")
//   @Produces(MediaType.APPLICATION_XML)
//   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//   public String addNewTransaction(@FormParam("amount") double amount, @FormParam("currency") String currencyCode, @Context HttpServletResponse servletResponse) throws IOException{
//	   UserTransaction uTransaction = new UserTransaction(amount, currencyCode, new Date(), new Date(), new Date());
//	   return userTransactionDao.addTransaction(uTransaction);
//   }
   
   @POST
   @Path("/transaction")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String addNewTransaction(@FormParam("amount") double amount, @FormParam("currency") String currencyCode, @FormParam("description") String desc, @FormParam("merchant") String merchant, @Context HttpServletResponse servletResponse) throws IOException{
	   UserTransaction uTransaction = new UserTransaction(amount, currencyCode, new Date(), new Date(), new Date());
	   uTransaction.setMerchant(merchant);
	   uTransaction.setDesc(desc);
	   return userTransactionDao.addTransaction(uTransaction);
   }
   
   @GET
   @Path("/transaction/{transactionID}")
   @Produces(MediaType.APPLICATION_XML)
   public UserTransaction getTransaction(@PathParam("transactionID") int transactionID){
	   return userTransactionDao.getTransaction(transactionID);
   }
   
   @DELETE
   @Path("/transaction/{transactionID}")
   @Produces(MediaType.APPLICATION_XML)
   public String deleteTransaction(@PathParam("transactionID") int transactionID){
	   return userTransactionDao.deleteTransaction(transactionID);
   }
   
   
   
}