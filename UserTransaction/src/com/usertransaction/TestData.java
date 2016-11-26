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
	private static final String SUCCESS_RESULT="<result>success</result>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestData test = new TestData();
		test.init();
		test.testGetAllTransactions();
//		test.deleteTransaction();
//		test.addNewTransaction();
//		test.testGetAllTransactions();
		test.getOneTransaction();
		test.updateTransaction();
	}

	private void init(){
	      this.client = ClientBuilder.newClient();
	}
	
	private void testGetAllTransactions(){
      GenericType<List<UserTransaction>> list = new GenericType<List<UserTransaction>>() {};
      List<UserTransaction> transactions = client
         .target(REST_SERVICE_URL)
         .path("transaction")
         .request(MediaType.APPLICATION_XML)
         .get(list);
      String result = PASS;
      if(transactions.isEmpty()){
         result = FAIL;
      }
      System.out.println("Test case name: testGetAllTransactions, Result: " + transactions);
	}
	
		private void addNewTransaction(){
			Form form = new Form();
			form.param("amount", "500.0");
			form.param("currency", "USD");
			form.param("merchant", "TestMerchant");
			
			String callResult = client
			         .target(REST_SERVICE_URL)
			         .path("transaction")
			         .request(MediaType.APPLICATION_XML)
			         .post(Entity.entity(form,
			            MediaType.APPLICATION_FORM_URLENCODED),
			            String.class);
		
		
		      System.out.println("Test case name: addNewTransaction (POST), Result: " + callResult );
		  }
	
		private void deleteTransaction(){
			String result = client.target(REST_SERVICE_URL).path("transaction/{transactionID}").resolveTemplate("transactionID", 7)
					.request(MediaType.APPLICATION_XML).delete(String.class);
			System.out.println("Test case name: deleteTransaction, Result: " + result);

		}
		
		private void getOneTransaction(){
			UserTransaction result = null;
					
			result = client.target(REST_SERVICE_URL).path("transaction/{transactionID}").resolveTemplate("transactionID", 5)
					.request(MediaType.APPLICATION_XML).get(UserTransaction.class);
			
			System.out.println("Test case name: getOneTransaction, Result: " + result);
		}
		
		private void updateTransaction(){
			Form form = new Form();
			form.param("id", "5");
//			form.param("currency", "IDR");
			form.param("amount", "5000000");
			form.param("merchant", "TestMerchant");
			
			UserTransaction result = null;
			result = client.target(REST_SERVICE_URL).path("update").request(MediaType.APPLICATION_XML)
					.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED), UserTransaction.class);
			
			System.out.println("Test case name: updateTransaction, Result: " + result.getCurrencyCode());
			
		}
	}
	

