package com.cucumber.apitesting;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@ContextConfiguration
@CucumberOptions(features = "classpath:Feature",
glue = {"com.cucumber.apitesting"})
public class CucumberIntegrationTest {

}
