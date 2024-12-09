package vttp.ssf.assessment.eventmanagement.repositories;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.assessment.eventmanagement.constant.Constant;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Autowired
	@Qualifier(Constant.TEMPLATE02)
	RedisTemplate<String, String> template;

	private static final String REDISKEY = "EVENT";
	
	// Task 2
	// Write a function named saveRecord(Event event) that will save the list of event object to redis
	public void saveRecord(Event event) {
		
		// Create a JsonObject from the Event POJO
		JsonObject eventJson = Json.createObjectBuilder()
								.add("eventId", event.getEventId())
								.add("eventName", event.getEventName())
								.add("eventSize", event.getEventSize())
								.add("eventDate", event.getEventDate())
								.add("participants", event.getParticipants())
								.add("formattedDate", event.getFormattedDate().toString())
								.build();
		
		// Save to redis
		// System.out.println("Saving to redis: " + eventJson.toString());
		saveToRedis(String.valueOf(event.getEventId()), eventJson.toString());

	}



	// Task 3
	// Write a function named getNumberOfEvents() that returns the size of the event list in redis
	public Long getNumberOfEvents() {
		return template.opsForHash().size(REDISKEY);
	}


	// Task 4
	// Write a function named getEvent(Integer index) that returns an event object at the particular index from the event list in redis
	public Event getEvent(Integer index){
		
		String eventJsonString = getEventDetails(String.valueOf(index));

		// Read the string into a JsonObject
		JsonReader jReader = Json.createReader(new StringReader(eventJsonString));
		JsonObject eventJsonObject = jReader.readObject();
			// Extract details
			Integer eventId = eventJsonObject.getJsonNumber("eventId").intValue();
			String eventName = eventJsonObject.getString("eventName");
			Integer eventSize = eventJsonObject.getJsonNumber("eventSize").intValue();
			Long eventDate = eventJsonObject.getJsonNumber("eventDate").longValue();
			Integer participants =  eventJsonObject.getJsonNumber("participants").intValue();

		// Create the event POJO
		Event event = new Event(eventId, eventName, eventSize, eventDate, participants);

		return event;
	}



	// return all events as a List<Event>
	public List<Event> getAllEvents(){
		
		List<Event> events = new ArrayList<>();

		Map<Object, Object> entries = template.opsForHash().entries(REDISKEY);

		for (Map.Entry<Object, Object> entry : entries.entrySet()){
			
			String eventJsonString = entry.getValue().toString();

			// Read the string into a JsonObject
			JsonReader jReader = Json.createReader(new StringReader(eventJsonString));
			JsonObject eventJsonObject = jReader.readObject();
				// Extract details
				Integer eventId = eventJsonObject.getJsonNumber("eventId").intValue();
				String eventName = eventJsonObject.getString("eventName");
				Integer eventSize = eventJsonObject.getJsonNumber("eventSize").intValue();
				Long eventDate = eventJsonObject.getJsonNumber("eventDate").longValue();
				Integer participants =  eventJsonObject.getJsonNumber("participants").intValue();
	
			// Create the event POJO
			Event event = new Event(eventId, eventName, eventSize, eventDate, participants);


			// Add to the list
			events.add(event);
		}

		return events;
		
	}


	// Save a single event to redis
	public void saveToRedis(String eventId, String eventDetails){
		template.opsForHash().put(REDISKEY, eventId, eventDetails);
	}

	// Get a single event from redis
	public String getEventDetails(String eventId){
		return (String) template.opsForHash().get(REDISKEY, eventId);
	}


}
