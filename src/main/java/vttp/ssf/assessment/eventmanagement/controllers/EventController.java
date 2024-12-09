package vttp.ssf.assessment.eventmanagement.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	RedisRepository redisRepository;
	

	@GetMapping("/listing")
	public String displayEvents(Model model){

		List<Event> events = redisRepository.getAllEvents();

		// sort by eventId
		List<Event> sortedList = events.stream()
									.sorted(Comparator.comparing(Event::getEventId))
									.collect(Collectors.toList());

		model.addAttribute("events", sortedList);
		
		return "view0";
	}

}
