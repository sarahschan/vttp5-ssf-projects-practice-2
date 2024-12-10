package vttp.ssf.assessment.eventmanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;
import vttp.ssf.assessment.eventmanagement.services.DatabaseService;

@SpringBootApplication
public class EventmanagementApplication implements CommandLineRunner{


	@Autowired
	DatabaseService databaseService;

	@Autowired
	RedisRepository redisRepo;


	public static void main(String[] args) {
		SpringApplication.run(EventmanagementApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
	
		// Use readFile() in DatabaseService to return a List<Event>
		List<Event> events = databaseService.readFile("events.json");

		System.out.println("All events read from file to List<Event> events");
			// for (Event event : events) {
			// 	System.out.println(event);
			// }

		// Use saveRecord() in RedisRepository to save the events to redis
		for (Event event : events) {
			redisRepo.saveRecord(event);
		}

		System.out.println("All events saved to redis");

		
	}
	

}
