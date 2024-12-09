package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.RegistrationForm;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping()
public class RegistrationController {
    
    @Autowired
    RedisRepository redisRepository;

    @GetMapping("/event/register/{eventId}")
    public String registrationPage(@PathVariable("eventId") Integer eventId, Model model){

        Event event = redisRepository.getEvent(eventId);
        RegistrationForm form = new RegistrationForm();

        model.addAttribute("event", event);
        model.addAttribute("form", form);

        return "eventRegister";
    }


    @PostMapping("/event/register/{eventId}")
    public String handleRegistration(@PathVariable("eventId") Integer eventId, @Valid @ModelAttribute("form") RegistrationForm form, BindingResult result, Model model){
        
        Event event = redisRepository.getEvent(eventId);

        if (result.hasErrors()) {
            model.addAttribute("event", event);
            model.addAttribute("form", form);
            return "eventRegister";
        }

        // Check if there is enough space in the event
        int availableSpots = event.getEventSize() - event.getParticipants();
        int requestedTickets = form.getTicketsRequested();
            // System.out.println("Spots available: " + availableSpots);
            // System.out.println("Requested tickets: " + requestedTickets);

        // If there are enough spots
        if (availableSpots >= requestedTickets) {
            return "redirect:/registration/register/" + eventId;

        } else {
            return "redirect:/registration/registererror/" + eventId;
        }

    }


    @GetMapping("/registration/register/{eventId}")
    public String successfulRegistration(@PathVariable("eventId") Integer eventId, Model model){
        Event event = redisRepository.getEvent(eventId);
        model.addAttribute("event", event);
        return "successRegistration.html";
    }

    @GetMapping("/registration/registererror/{eventId}")
    public String errorRegistration(@PathVariable("eventId") Integer eventId, Model model){
        Event event = redisRepository.getEvent(eventId);
        model.addAttribute("event", event);
        return "errorRegistration.html";
    }

}
