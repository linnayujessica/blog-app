package com.blog;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.blog.controller.BlogController;
import com.blog.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BlogAppApplication.class)
class BlogAppApplicationTests {
	@Autowired
    private BlogController blog;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }
    
	@Test
	public void testAdminGetUser() throws JsonProcessingException, URISyntaxException {
		Geo geo = new Geo(-37.3159, 81.1496);
		Address address = new Address(
				"Kulas Light",
				"Apt. 556",
				"Gwenborough",
				"92998-3874",
				geo);
	    Company company = new Company(
	    		"Romaguera-Crona",
	    		"Multi-layered client-server neural-net",
	    	    "harness real-time e-markets");
	    User user = new User(1,
	    		  "Leanne Graham",
	    		  "Bret",
	    		  "Sincere@april.biz",
	    		  "1-770-736-8031 x56442",
	    		  "hildegard.org",
	    		  company,
	    		  address
	    		  );
	    Admin ad = new Admin(user, null);
		mockServer.expect(ExpectedCount.once(), 
		          requestTo(new URI("https://localhost:8080/admin/1")))
		          .andExpect(method(HttpMethod.GET))
		          .andRespond(withStatus(HttpStatus.OK)
		          .contentType(MediaType.APPLICATION_JSON)
		          .body(mapper.writeValueAsString(ad))
		        );                                   
		                       
		        Admin admin = blog.consumeAdmin(1);
		        mockServer.verify();
		        Assert.assertEquals(ad, admin);   	
	}

}
