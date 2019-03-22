package com.distributed.session.demo.distributedsession.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 问题：当web服务器搭建集群后，会导致服务器端的session不一致
 * 解决：1.web服务器配置session同步   2.session统一缓存
 *
 * Session统一redis缓存
 *  1.引入spring-boot-starter-data-redis和 spring-session-data-redis两个依赖
 *      其实底层引入了SessionRepositoryFilter过滤器，当调用request.getSession()获取session时，
 *      会走到SessionRepositoryFilter的getSession()方法，实现在redis上创建、获取session等一系列操作，达到共享session。
 *      在没有引入spring-session-data-redis之前，request.getSession()调用后会去到tomcat中创建、获取session等一系列操作
 *
 * @Author daituo
 * @Date 2019-3-22
 **/
@RestController
public class DistributedSessionController {

    @GetMapping(value = "/set/session")
    public Map<String, Object> firstResp (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        /** request.getSession()之后，服务端才会生成session */
        request.getSession().setAttribute("request Url", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }


    @GetMapping(value = "/get/session")
    public Object sessions (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("request Url", request.getSession().getAttribute("request Url"));
        return map;
    }
}
