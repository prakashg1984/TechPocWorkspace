package com.pg.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.cloud.deployer.spi.cloudfoundry.CloudFoundryDeployerAutoConfiguration;
import org.springframework.cloud.deployer.spi.kubernetes.KubernetesAutoConfiguration;
import org.springframework.cloud.deployer.spi.local.LocalDeployerAutoConfiguration;
import org.springframework.cloud.skipper.server.EnableSkipperServer;

@SpringBootApplication(exclude = {CloudFoundryDeployerAutoConfiguration.class, KubernetesAutoConfiguration.class, LocalDeployerAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class, SecurityAutoConfiguration.class, UserDetailsServiceAutoConfiguration.class, SessionAutoConfiguration.class})
@EnableSkipperServer
public class SpringCloudSkipperServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSkipperServerApplication.class, args);
	}

}
