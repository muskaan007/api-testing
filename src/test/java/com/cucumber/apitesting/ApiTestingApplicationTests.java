package com.cucumber.apitesting;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:Feature",
glue = {"com.cucumber.apitesting"})
class ApiTestingApplicationTests {

}
