package com.david.client.service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by sc on 2019-03-05.
 */
@FeignClient(name = "spring-cloud-server-application")
public interface SayingService {

    @GetMapping("/say")
    String say(@RequestParam("message") String message);

}
