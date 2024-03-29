package com.learn.user;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.learn.MicroServicesCloudApplicationTests;

public class UserResourceControllerTest extends MicroServicesCloudApplicationTests{

	@Test
	public void shouldReturnAllUsers() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(jsonPath("$", hasSize(6) ));
	}
	
	@Test
	public void shouldReturnUserById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/101").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("Aaryan"));
	}
	
	@Test
	public void shouldReturn404StatusIfUserIdNotExists() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/users/12"))
				.andExpect(MockMvcResultMatchers.status().is(404))
				.andExpect(jsonPath("$.message").value("id : 12 not found"));
		
		
	}
	
	@Test
	public void shouldAddNewUser() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
				
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"ajay\", \"birthDate\": \"2018-03-29\"}")
//				.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/users/1"))
				.andDo(print());
	}
}
