package vttp.ssf.assessment.eventmanagement.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp.ssf.assessment.eventmanagement.constant.Constant;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired
	@Qualifier(Constant.TEMPLATE02)
	RedisTemplate<String, String> template;

	private static final String REDISKEY = "EVENT";
	
	// Write a function named saveRecord(Event event) that will save the list of event object to redis
	public void saveRecord(Event event) {
		
		// Create a JsonObject from the Event POJO
		JsonObject eventJson = Json.createObjectBuilder()
								.add("eventId", event.getEventId())
								.add("eventName", event.getEventName())
								.add("eventSize", event.getEventDate())
								.add("eventDate", event.getEventDate())
								.add("participants", event.getParticipants())
								.build();
		
		// Save to redis
		saveToRedis(String.valueOf(event.getEventId()), eventJson.toString());

	}


	public void saveToRedis(String hash, String value){
		template.opsForHash().put(REDISKEY, hash, value);
	}





	// TODO: Task 3


	// TODO: Task 4
}
