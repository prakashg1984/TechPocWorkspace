package com.pg.address.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pg.AppConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan
@WebAppConfiguration
@SpringBootTest(classes = AppConfig.class)
public class AppConfigTest {

	@Test
	  public void applicationStarts() throws Exception {
		AppConfig.main(new String[] {});
	  }
}
