package com.wolfott.mangement.line.configs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:9009")
public interface UserServiceClient {

    @GetMapping("/api/v1/users/username")
    String getUsernameByMemberId(@RequestParam("memberId") Long memberId);
}
