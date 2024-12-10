package vttp.ssf.assessment.eventmanagement.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import vttp.ssf.assessment.eventmanagement.validation.ValidAge;

public class RegistrationForm {
    
        @NotBlank(message = "Name is mandatory") 
        @Size(min = 5, max = 25, message = "Name must be between 5 and 25 characters")
        @Pattern(regexp = "^[a-zA-Z@\\-\\s]*$", message = "Name can only contain alphabets, spaces, '@', or '-'")
    private String fullName;

        @NotNull(message = "Date of birth is mandatory")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        @Past(message = "Birth date must be a past date")
        @ValidAge
    private LocalDate dob;

        @NotBlank(message = "Email is mandatory")
        @Size(max = 50, message = "Email cannot be more than 50 characters long")
        @Email(message = "Please enter a valid email address")
    private String email;

        @NotBlank(message = "Phone number is mandatory")
        @Pattern(regexp = "^[89][0-9]{7}$", message = "Phone number start with 8 or 9, and consist of exactly 8 digits")
    private String phone;

        @NotNull(message = "Number of tickets requested is mandatory")
        @Min(value = 1, message = "You must request at least 1 ticket")
        @Max(value = 3, message = "You can request up to 3 tickets")
    private Integer ticketsRequested;


    public RegistrationForm() {
    }

    public RegistrationForm(String fullName, LocalDate dob, String email, String phone, Integer ticketsRequested) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.ticketsRequested = ticketsRequested;
    }


    @Override
    public String toString() {
        return fullName + "," + dob + "," + email + "," + phone + "," + ticketsRequested;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTicketsRequested() {
        return ticketsRequested;
    }

    public void setTicketsRequested(Integer ticketsRequested) {
        this.ticketsRequested = ticketsRequested;
    }

    
}
