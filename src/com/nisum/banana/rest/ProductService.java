package com.nisum.banana.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nisum.banana.model.Product;

@Path("/productservice")
public class ProductService {
	
	private static Map<String, Product> products = new HashMap<String, Product>();
	
	static {
		
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Calvin Klein Suit");
        product1.setSellingPrice(new BigDecimal("345"));
        products.put(product1.getProductId(), product1);
        
        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Calvin Klein Brown shoe");
        product2.setSellingPrice(new BigDecimal("45.35"));
        products.put(product2.getProductId(), product2);
        
    }

	@GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello(){
        return "Let's Make an Offer !!!";    
    }
	
	@GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo(@PathParam("message")String message){
        return message;    
    }
	
	@GET
    @Path("/Products")
    @Produces("application/xml")
    public List<Product> listProducts(){
        return new ArrayList<Product>(products.values());
    }
	
	@GET
    @Path("/Product/{Productid}")
    @Produces("application/xml")
    public Product getProduct(@PathParam("Productid")String ProductId){
		return products.get(ProductId);		
    }
	
	@GET
    @Path("/json/Products/")
    @Produces("application/json")
    public List<Product> listProductsJSON(){
		return new ArrayList<Product>(products.values());
    }

	@GET
    @Path("/json/Product/{Productid}")
    @Produces("application/json")
    public Product getProductJSON(@PathParam("Productid")String ProductId){
		return products.get(ProductId);		
    }
	
	@POST
	@Path("/send")
	@Consumes("application/json")

	public Response consumeJSON( Product product ) {
		products.put(product.getProductId(), product);
		String output = product.toString();
		return Response.status(200).entity(output).build();
		}
}
