package com.lambert.jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambert.jpa.util.Message;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class HelloController {
    Message message;

    @GetMapping("/hello")
    public void hello(HttpServletResponse resp) throws IOException {
//        message.returnJson(200, "", "data", resp);
    }
}
