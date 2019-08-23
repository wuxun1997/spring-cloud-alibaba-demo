package com.wlf.alibaba.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class TransConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransConsumerApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        @Autowired
        private ApplicationApi applicationApi;

        @GetMapping("/sayhello")
        public String sayhello() {
            return "say: " + applicationApi.hello();
        }

        @GetMapping("/sayhey")
        public String sayhey() {
            return "say: " + applicationApi.hey();
        }

    }

    @FeignClient(name = "lxytrans-provider", fallback = TestFallback.class, configuration = FeignConfiguration.class)
    interface ApplicationApi {

        @GetMapping("/hello")
        String hello();

        @GetMapping("/hey")
        String hey();

    }

    class TestFallback implements ApplicationApi {

        @Override
        public String hello() {
            return "hello feign fallback.";
        }

        @Override
        public String hey() {
            return "hey feign fallback.";
        }
    }

    class FeignConfiguration {
        @Bean
        public TestFallback testFallback() {
            return new TestFallback();
        }
    }

}
