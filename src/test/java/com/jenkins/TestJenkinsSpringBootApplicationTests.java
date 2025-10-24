package com.jenkins;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jenkins.service.MainService;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestJenkinsSpringBootApplicationTests {


		@Test
	public void test()
	{
		MainService mainService=new MainService();
		int result=mainService.addTwo(10, 20);
		assertEquals(31,result);
	}

}
