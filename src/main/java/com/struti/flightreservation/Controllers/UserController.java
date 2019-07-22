package com.struti.flightreservation.Controllers;

import com.struti.flightreservation.DAO.Repos.IUserRepository;
import com.struti.flightreservation.Models.User;
import com.struti.flightreservation.Services.ISecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    private BCryptPasswordEncoder encoder;
    private IUserRepository userRepository;
    private ISecurityService securityService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserRepository userRepository, BCryptPasswordEncoder encoder, ISecurityService securityService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.securityService = securityService;
    }

    @RequestMapping("/showRegistration")
    public String showRegistration() {
        LOGGER.info("Inside showRegistration()");
        return "login/registerUser";
    }

    @RequestMapping("/showLogin")
    public String showLogin() {
        LOGGER.info("Inside showLogin()");
        return "login/login";
    }


    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user) {
        LOGGER.info("Inside {} register() " + user);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {

        boolean loginResponse = securityService.login(email, password);
        LOGGER.info("Inside login() and the email is: " + email);
        if (loginResponse) {
            return "flights/findFlights";
        } else {
            String msg = "Invalid Username or password. Please Try again";
            wrongCredentials(msg, modelMap);
        }
        return "login/login";
    }

    @RequestMapping(value = "/login")
    public String wrongCredentials(String msg, ModelMap modelMap) {
        LOGGER.info("Inside wrongCredentials() Message is = " + msg);
        modelMap.addAttribute("msg", msg);

        return "login/login";
    }

}
