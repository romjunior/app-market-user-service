package com.appmarket.persistence;

import com.appmarket.persistence.mapper.UserMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ContextConfiguration;

@SpringBootApplication(scanBasePackages = "com.appmarket")
@ContextConfiguration(classes = UserMapper.class)
public class TestApplication {

}
