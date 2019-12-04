package com.learn.hello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.learn.MicroServicesCloudApplicationTests;

@SpringBootTest
public class HelloWorldControllerTest extends MicroServicesCloudApplicationTests{

	
	@Autowired
	private HelloWorldController helloController;
	
	@Test
	public void shouldReturnStringHello() {
		assertEquals("hello test", helloController.hello());
	}
	
	@Test
	public void shouldReturnDataWithNameGetHello() {
		String name ="ajay";
		assertEquals("Hello World "+name, helloController.getHello(name));
	}
}
