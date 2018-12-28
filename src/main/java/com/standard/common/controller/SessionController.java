package com.standard.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ellien
 * @since 2018/12/05 11:44
 */
@RestController
public class SessionController {

    @RequestMapping("/session")
    public String getSession(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        session.setAttribute("name", "elien");
        return "hello";
    }

    @RequestMapping("/session/get")
    public String getSessionHello(HttpServletRequest request){
        return "hello";
    }

    @RequestMapping("/session/delete")
    public String deleteSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "hello";
    }
}
