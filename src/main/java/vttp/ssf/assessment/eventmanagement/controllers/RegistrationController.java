package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.RegistrationForm;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    
    @Autowired
    RedisRepository redisRepository;

    @GetMapping("/{eventId}")
    public String registrationPage(@PathVariable("eventId") Integer eventId, Model model){

        Event event = redisRepository.getEvent(eventId);
        RegistrationForm form = new RegistrationForm();

        model.addAttribute("event", event);
        model.addAttribute("form", form);

        return "eventRegister";
    }

}
