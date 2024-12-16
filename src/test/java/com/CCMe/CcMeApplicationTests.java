package com.CCMe;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.CCMe.Repository.JobRepository;

@SpringBootTest
class CcMeApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JobRepository jobRepo;

	@Test
	public void testPasswordEncoder() {
		assertNotNull(passwordEncoder);
		System.out.println(passwordEncoder.encode(("Yourmom666")));
	}

	@Test
	void testProfileJobs() {
		
	}

}
