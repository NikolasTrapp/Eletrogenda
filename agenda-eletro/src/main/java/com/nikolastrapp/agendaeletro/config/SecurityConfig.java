//package com.nikolastrapp.agendaeletro.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//	@Bean
//	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeHttpRequests()
//				.antMatchers("/schedulings/insertScheduling", "/classrooms/insertClassroom", "/teachers/insertTeacher", "/class/insertClass").authenticated()
//				.antMatchers("/schedulings", "/class", "/classroom").permitAll().and().formLogin().and().httpBasic();
//		return http.build();
//	}
//}
