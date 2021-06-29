package com.example.springwork;

import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;

import static org.hamcrest.Matchers.containsString;

@SpringBootTest
class SpringWorkApplicationTests {

	@ClassRule
	public static OutputCaptureRule out = new OutputCaptureRule();
  
	@Test
	public void contextLoads() {
	  out.expect(containsString("2,Korea,KO,KR"));
	}

}
