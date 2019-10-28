package com.DoanHieu.controller;

import com.DoanHieu.model.PhoneNumber;
import com.DoanHieu.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @GetMapping("/user")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/validateUser")
    public ModelAndView checkValidation(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("index");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("result");
        return modelAndView;
    }

    //validing phone
    @Controller
    public class PhoneController {
        @GetMapping("/phone")
        public String showForm(Model model) {
            model.addAttribute("phonemunber", new PhoneNumber());
            return "index1";
        }
    }
    @PostMapping("/phonemunber-number")
    public String checkValidation (@Valid @ModelAttribute("phonemunber")PhoneNumber phoneNumber, BindingResult bindingResult, Model model){
        new PhoneNumber().validate(phoneNumber, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "index1";
        }
        else {
            model.addAttribute("phoneNumber", phoneNumber.getNumber());
            return "result1";
        }
    }

}

