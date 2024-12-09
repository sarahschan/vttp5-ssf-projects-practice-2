package vttp.ssf.assessment.eventmanagement.models;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class Event {
    
    private Integer eventId;
    private String eventName;
    private Integer eventSize;
    private Long eventDate; // event date in epoch miliseconds
    private Integer participants;
    private LocalDate formattedDate;

    
    public Event() {
    }

    public Event(Integer eventId, String eventName, Integer eventSize, Long eventDate, Integer participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
        this.formattedDate = dateConverter(eventDate);
    }
        
            
    private LocalDate dateConverter(Long eventDate) {
        return LocalDate.ofInstant(Instant.ofEpochMilli(eventDate), ZoneId.systemDefault());
    }
    
    
    @Override
    public String toString() {
        return eventId + "," + eventName + "," + eventSize + "," + eventDate + ", " + participants +  "," + formattedDate;
    }


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getEventSize() {
        return eventSize;
    }

    public void setEventSize(Integer eventSize) {
        this.eventSize = eventSize;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public LocalDate getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(LocalDate formattedDate) {
        this.formattedDate = formattedDate;
    }

    
    
}
