package com.distributed.session.demo.distributedsession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class DistributedSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributedSessionApplication.class, args);
    }

}
