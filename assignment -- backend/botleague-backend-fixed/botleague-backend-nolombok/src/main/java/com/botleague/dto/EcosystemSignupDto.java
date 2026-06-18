package com.botleague.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EcosystemSignupDto {

    public static class Request {
        @NotBlank(message = "Role is required")
        private String role;
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Location is required")
        private String location;
        @NotBlank(message = "Enroll info is required")
        private String enroll;
        @Email
        private String email;

        public String getRole()              { return role; }
        public void setRole(String v)        { this.role = v; }
        public String getName()              { return name; }
        public void setName(String v)        { this.name = v; }
        public String getLocation()          { return location; }
        public void setLocation(String v)    { this.location = v; }
        public String getEnroll()            { return enroll; }
        public void setEnroll(String v)      { this.enroll = v; }
        public String getEmail()             { return email; }
        public void setEmail(String v)       { this.email = v; }
    }

    public static class Response {
        private String id, role, name, location, enroll, email, status, createdAt;

        public String getId()                { return id; }
        public void setId(String v)          { this.id = v; }
        public String getRole()              { return role; }
        public void setRole(String v)        { this.role = v; }
        public String getName()              { return name; }
        public void setName(String v)        { this.name = v; }
        public String getLocation()          { return location; }
        public void setLocation(String v)    { this.location = v; }
        public String getEnroll()            { return enroll; }
        public void setEnroll(String v)      { this.enroll = v; }
        public String getEmail()             { return email; }
        public void setEmail(String v)       { this.email = v; }
        public String getStatus()            { return status; }
        public void setStatus(String v)      { this.status = v; }
        public String getCreatedAt()         { return createdAt; }
        public void setCreatedAt(String v)   { this.createdAt = v; }
    }
}
