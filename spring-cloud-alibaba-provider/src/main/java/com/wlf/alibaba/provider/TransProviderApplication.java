package com.wlf.alibaba.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class TransProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransProviderApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        @Autowired
        private TranslateService testService;

        @GetMapping("/hello")
        public String hello() {
            return testService.hello();
        }

        @GetMapping("/hey")
        public String hey() throws InterruptedException {
            return testService.hey();
        }

    }

}
