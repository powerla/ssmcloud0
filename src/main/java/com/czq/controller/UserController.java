package com.czq.controller;

import com.czq.pojo.File;
import com.czq.pojo.User;
import com.czq.service.FileService;
import com.czq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session, Model model) {
        User user = userService.queryUserByUsername(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            model.addAttribute("user", user.getUsername());
            return "redirect:/file/all";
        }
        model.addAttribute("messre", "账号或者密码错误，请重新输入");
        return "login";
    }

    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public String forget(String username, String phone, String email, Model model) {
        User user = userService.queryUser(username);
        if (user.getPhone().equals(phone) && user.getEmail().equals(email)) {
            model.addAttribute("user", user.getUsername());
            return "modify";
        }
        model.addAttribute("err", "账号或者密码错误，请重新输入");
        return "forget";
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(String username, String password, Model model) {
        System.out.println(username);
        String mess = userService.queryPassword(username, password);
        model.addAttribute("messre", mess);
        return "login";
    }

    //国际化
    @RequestMapping("/locale")
    public String locale(@RequestParam("local") String locale, HttpSession session) {
        if ("zh".equals(locale)) {
            Locale locale1 = new Locale("zh", "CN");
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale1);
        } else {
            Locale locale1 = new Locale("en", "US");
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale1);
        }
        return "login";
    }


    @RequestMapping("tiaozhuan")
    public String tiaozhuang() {
        return "tiaozhuan";
    }


    @RequestMapping("userMessage")
    public String userMessage(HttpSession session,Model model)
    {
        User user= (User) session.getAttribute("user");
        System.out.println(user);
        List<File> list1=fileService.ownerFind(user.getUsername(),1);
        List<File> list0=fileService.ownerFind(user.getUsername(),0);
        model.addAttribute("list1",list1);
        int count0=list0.size();
        int count1=list1.size();
        model.addAttribute("count0",count0);
        model.addAttribute("count1",count1);
        model.addAttribute("list0",list0);
        model.addAttribute("user", user);
        return "tousermessage";
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
    @RequestMapping("/toindex")
    public String toIndex() {
        return "index";
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
