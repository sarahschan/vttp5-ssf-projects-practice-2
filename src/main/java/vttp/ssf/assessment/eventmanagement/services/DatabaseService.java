package vttp.ssf.assessment.eventmanagement.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Service
public class DatabaseService {
    
    // Task 1: readFile(String fileName) returns a List<Event> object
    public List<Event> readFile(String fileName) throws FileNotFoundException{
        
        // Read file using file reader
        File eventsFile = new File(fileName);

        // File eventsFile = ResourceUtils.getFile(fileName);

        if (!eventsFile.exists() || !eventsFile.isFile()){
            throw new FileNotFoundException();
        }


        // Use JsonReader to read the file
        JsonReader jReader = Json.createReader(new FileReader(eventsFile));
        JsonArray jsonEventsArray = jReader.readArray();


        // Prepare a list to store the event POJOs
        List<Event> events = new ArrayList<>();

        // Process each event from the array
        for (JsonValue eventRaw : jsonEventsArray) {
            
            JsonObject eventJson = eventRaw.asJsonObject();
                // Extract details
                Integer eventId = eventJson.getJsonNumber("eventId").intValue();
                String eventName = eventJson.getString("eventName");
                Integer eventSize = eventJson.getJsonNumber("eventSize").intValue();
                Long eventDate = eventJson.getJsonNumber("eventDate").longValue();
                Integer participants =  eventJson.getJsonNumber("participants").intValue();

            
            // Create an event POJO using the extracted details
            Event event = new Event(eventId, eventName, eventSize, eventDate, participants);

            // add it to List<Event> events
            events.add(event);

        }

        return events;

    }


}
