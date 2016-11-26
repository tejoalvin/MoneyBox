package com.usertransaction;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class TestData {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/UserTransaction/rest/UserTransactionService/";

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestData test = new TestData();
		test.init();
		test.getAllTransactions();
		
		Form new1 = new Form();
		new1.param("amount", "500.0");
		new1.param("currency", "USD");
		new1.param("merchant", "TestMerchant");
		test.addNewTransaction(new1);
		
		Form new2 = new Form();
		new2.param("amount", "5000.0");
		new2.param("currency", "EUR");
		new2.param("merchant", "AnotherMerchant");
		new2.param("description", "Forza Ferrari");
		test.addNewTransaction(new2);
		
		test.getAllTransactions();
		
		Form update1 = new Form();
		update1.param("id", "2");
		update1.param("currency", "IDR");
		update1.param("amount", "5000000");
		update1.param("merchant", "TestMerchant");
		
		test.updateTransaction(update1);
		
		test.getAllTransactions();
		
		Form update2 = new Form();
		update2.param("id", "5");
		update2.param("currency", "IDR");
		update2.param("amount", "5000000");
		update2.param("merchant", "TestMerchant");

		test.updateTransaction(update2);
		
		test.getAllTransactions();
		
		test.getOneTransaction(3);

		test.deleteTransaction(3);
		
		test.getAllTransactions();
		
	}

	private void init(){
	      this.client = ClientBuilder.newClient();
	}
	
	private void getAllTransactions(){
      GenericType<List<UserTransaction>> list = new GenericType<List<UserTransaction>>() {};
      List<UserTransaction> transactions = client
         .target(REST_SERVICE_URL)
         .path("transaction")
         .request(MediaType.APPLICATION_XML)
         .get(list);

      if(transactions == null){
          System.out.println("All Transactions " + transactions + "\n");
      } else {
    	  System.out.println("All Transactions");
    	  for(UserTransaction t : transactions){
    		  t.printData();
    	  }
      }
	}
	
		private void addNewTransaction(Form form){
			
			String callResult = client
			         .target(REST_SERVICE_URL)
			         .path("transaction")
			         .request(MediaType.APPLICATION_XML)
			         .post(Entity.entity(form,
			            MediaType.APPLICATION_FORM_URLENCODED),
			            String.class);
		
		
		      System.out.println("Adding transaction " + callResult + "\n");
		  }
	
		private void deleteTransaction(int id){
			String result = client.target(REST_SERVICE_URL).path("transaction/{transactionID}").resolveTemplate("transactionID", id)
					.request(MediaType.APPLICATION_XML).delete(String.class);
			System.out.println("Delete transaction " + id + " " + result + "\n");

		}
		
		private void getOneTransaction(int id){
			UserTransaction result = null;
					
			result = client.target(REST_SERVICE_URL).path("transaction/{transactionID}").resolveTemplate("transactionID", id)
					.request(MediaType.APPLICATION_XML).get(UserTransaction.class);
			
			if(result == null){
				System.out.println("No Transaction with ID " + id + "\n");
			} else {
				System.out.println("Get Transaction \n");
				result.printData();
			}
			
		}
		
		private void updateTransaction(Form form){
			
			UserTransaction result = null;
			result = client.target(REST_SERVICE_URL).path("update").request(MediaType.APPLICATION_XML)
					.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED), UserTransaction.class);
			
			if(result == null){
				System.out.println("Update Transaction : Transaction Not Updated \n");
			} else {
				System.out.println("Transaction Updated");
				result.printData();
			}
			
		}
	}
	

