package com.usertransaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDao {
	
   public List<UserTransaction> getAllTransaction(){
      List<UserTransaction> transactionList = null;
      
      File file = new File("UsersTransaction.dat");
      if (file.exists()) {	
    	  transactionList = readTransactionList();
      }
     	
      return transactionList;
   } 
   
   public String addTransaction(UserTransaction transaction){
	   File file = new File("UsersTransaction.dat");
	   List<UserTransaction> transactionList = null;
			   
	   if(file.exists()){
		   transactionList = readTransactionList();
		   UserTransaction t = transaction;
		   t.setTransactionID(transactionList.get(transactionList.size()-1).getTransactionID() + 1);
		   transactionList.add(t);
		  
		   deleteSavedList();
		   saveTransactionList(transactionList);
		   
		   return "SUCCESS";
	   } else {
		   transactionList = new ArrayList<UserTransaction>();
		   UserTransaction t = transaction;
		   t.setTransactionID(1);
		   transactionList.add(t);

		   saveTransactionList(transactionList);
		   return "SUCCESS";
	   }
   }
   
   public UserTransaction getTransaction(int transactionID){
	   File file = new File("UsersTransaction.dat");
	   List<UserTransaction> transactionList = null;
	   UserTransaction transaction = null;
	   
	   if(file.exists()){
		   transactionList = readTransactionList();
		   for(UserTransaction uT : transactionList){
			   if(uT.getTransactionID() == transactionID){
				   return uT;
			   }
		   }
	   } 
	   
	   return transaction;
   }
   
   public String deleteTransaction(int transactionID){
	   File file = new File("UsersTransaction.dat");
	   List<UserTransaction> transactionList = null;
	   
	   if(file.exists()){
		   transactionList = readTransactionList();
		   
		   for(UserTransaction uT : transactionList){
			   if(uT.getTransactionID() == transactionID){
				   transactionList.remove(uT);
				   deleteSavedList();
				   saveTransactionList(transactionList);
				   return "SUCCESS";
			   }
		   }
		   
		   return "FAIL";
	   } else {
		   return "NO TRANSACTION";
	   }
   }
   
   public UserTransaction updateTransaction(UserTransaction uT){
	   File file = new File("UsersTransaction.dat");
	   List<UserTransaction> transactionList = null;
	   
	   if(file.exists()){
		   transactionList = readTransactionList();
		   
		   for(UserTransaction uTransaction : transactionList){
			   if(uTransaction.getTransactionID() == uT.getTransactionID()){
				   //update transaction
				   
				   uTransaction.setModifiedDate(new Date());
				   if(uT.getCurrencyCode() != null){
					   uTransaction.setCurrencyCode(uT.getCurrencyCode());
				   }
				   
				   if(uT.getMerchant() != null){
					   uTransaction.setMerchant(uT.getMerchant());
				   }
				   
				   if(uT.getDesc() != null){
					   uTransaction.setMerchant(uT.getDesc());
				   }
				   
				   if(uT.getTransactionAmount() != 0.0){
					   uTransaction.setTransactionAmount(uT.getTransactionAmount());
				   }
				   
				   deleteSavedList();
				   saveTransactionList(transactionList);
				   return uTransaction;
			   }
		   }
		   
	   } 
	   
	   return null;
   }
   
   private List<UserTransaction> readTransactionList(){
	   List<UserTransaction> transactionList = null;
	   File file = new File("UsersTransaction.dat");
	   
	   try {
		   FileInputStream fis = new FileInputStream(file);
		   ObjectInputStream ois = new ObjectInputStream(fis);
		   transactionList = (List<UserTransaction>) ois.readObject();
		   ois.close();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
	   
	   return transactionList;
   }
   
   private void deleteSavedList(){
	   File file = new File("UsersTransaction.dat");
	   file.delete();
   }
   
   private void saveTransactionList(List<UserTransaction> transactionList){
	      try {
	         File file = new File("UsersTransaction.dat");
	         FileOutputStream fos = new FileOutputStream(file);

	         ObjectOutputStream oos = new ObjectOutputStream(fos);
	         oos.writeObject(transactionList);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }   
	}