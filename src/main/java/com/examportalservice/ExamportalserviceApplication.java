package com.examportalservice;

import com.examportalservice.auth.services.AuthenticationService;
import com.examportalservice.auth.services.UserService;
import com.examportalservice.auth.user.entity.User;
import com.examportalservice.entity.Role;
import com.examportalservice.entity.UserRole;
import com.examportalservice.helper.UserFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class ExamportalserviceApplication {

		private final AuthenticationService authenticationService;

	public ExamportalserviceApplication(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExamportalserviceApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Hello Sathi Lets Start the for Code!!");
//		try {
//			User user = new User();
//			user.setUsername("Sharad.khanal");
//			user.setPassword("9898989nmn");
//			user.setFirstname("Sharad");
//			user.setLastname("Khanal");
//			user.setEmail("khanelsharad@gmail.com");
//			user.setPhone("9889898989");
//			user.setProfile("sdfdf.png");
//
//			System.out.println("user:" + user);
//
//			Role role1 = new Role();
//
//			role1.setRoleId(33L);
//			role1.setRoleName("ADMIN");
//
//
//			Set<UserRole> userRoleSet = new HashSet<>();
//			UserRole userRole = new UserRole();
//			userRole.setRole(role1);
//			userRole.setUser(user);
//
//
//			Optional<User> user1 = this.authenticationService.createUser(user, userRoleSet);
//		}catch (UserFoundException e){
//			e.printStackTrace();
//		}
//
//	}
}
