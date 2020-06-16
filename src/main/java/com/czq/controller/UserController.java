package com.czq.controller;

import com.czq.pojo.User;
import com.czq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("User")
public class UserController {
    @Autowired
    private  UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session, Model model)
    {
        User user=userService.queryUserByUsername(username,password);
        if (user!=null)
        {
            session.setAttribute("user",user);
            model.addAttribute("user",user.getUsername());
            return "redirect:/User/index";
        }
        model.addAttribute("messre","账号或者密码错误，请重新输入");
        return "login";
    }

    @RequestMapping(value = "/forget",method = RequestMethod.POST)
    public String forget(String username, String phone,String email, Model model)
    {
        User user=userService.queryUser(username);
        if (user.getPhone().equals(phone)&&user.getEmail().equals(email))
        {
            model.addAttribute("user",user.getUsername());
            return "modify";
        }
        model.addAttribute("err","账号或者密码错误，请重新输入");
        return "login";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public String modify(String username, String password, Model model)
    {
        System.out.println(username);
        String mess=userService.queryPassword(username,password);
        model.addAttribute("messre",mess);
        return "login";
    }


    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session) {
        session.invalidate();
        return "login";
    }
    @RequestMapping("/toregister")
    public String toRegister() {
        return "register";
    }
    @RequestMapping("/toforget")
    public String toForget() {
        return "forget";
    }
    @RequestMapping("/tologin")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/register")
    public String register(String username, String password,String phone,String email,Model model) {
        String mess=userService.addUser(username,password,phone,email);
        model.addAttribute("messre",mess);
        return "login";
    }
}
