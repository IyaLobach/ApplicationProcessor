package com.example.ApplicationsProcessor.controllers;

import com.example.ApplicationsProcessor.services.ApplicationService;
import com.example.ApplicationsProcessor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

  @Autowired
  private UserService userService;

  @Autowired
  private ApplicationService applicationService;

  @GetMapping()
  public String index() {
    return "auth";
  }

//  @PostMapping("/{auth}")
//  public String auth(@RequestParam("email") String email, @RequestParam("password") String password,
//      Model model) {
//
//
//  }


}
