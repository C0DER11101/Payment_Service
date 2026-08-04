package com.jsp.payment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
info of our project that we provide to an external library
is called configuration.
 */

@Configuration // -> that's we provide this annotation
@ComponentScan(basePackages = "com.jsp.payment")
public class AppConfig {
}