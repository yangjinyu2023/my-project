package com.example.demo.springcloud;

import feign.Param;
import feign.RequestLine;

public interface DataManageApi {
    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    String getString(@Param("param") String param);
}
