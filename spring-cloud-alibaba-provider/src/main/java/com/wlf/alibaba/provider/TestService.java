package com.wlf.alibaba.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    @SentinelResource(value = "hello", blockHandler = "helloBlock")
    public String hello() {
        return "hello";
    }

    public String helloBlock(BlockException ex) {
        return "hello block";
    }

    @SentinelResource(value = "hey", fallback = "heyFallback")
    public String hey() throws InterruptedException {
        Thread.sleep(2000);
        return "hey";
    }

    public String heyFallback() {
        return "hey fallback";
    }   

}
