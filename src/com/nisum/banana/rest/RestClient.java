package com.nisum.banana.rest;

import java.math.BigDecimal;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.nisum.banana.model.Product;


public class RestClient {

	public static void main(String[] args) {

		try {
			 Product product = new Product();
		     product.setProductId("3");
		     product.setProductName("Calvin Klein Tie");
		     product.setSellingPrice(new BigDecimal("30"));	
		     
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource webResource = client
					.resource("http://localhost:8080/BananaRestfulJersey/nisum/productservice/send");
			ClientResponse response = webResource.accept("application/json")
					.type("application/json").post(ClientResponse.class, product);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
				}

				String output = response.getEntity(String.class);
				System.out.println("Server response .... \n");
				System.out.println(output);
			}

			catch (Exception e) {

				e.printStackTrace();
			}
		}
}
