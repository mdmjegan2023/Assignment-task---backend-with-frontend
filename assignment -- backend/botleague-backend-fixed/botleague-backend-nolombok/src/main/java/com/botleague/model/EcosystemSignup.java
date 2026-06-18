package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ecosystem_signups")
public class EcosystemSignup {

    @Id
    private String id;
    private String role;
    private String name;
    private String location;
    private String enroll;
    private String email;
    private String status = "PENDING";
    private LocalDateTime createdAt = LocalDateTime.now();

    public EcosystemSignup() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, role, name, location, enroll, email;
        private String status = "PENDING";
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(String v)              { id = v; return this; }
        public Builder role(String v)            { role = v; return this; }
        public Builder name(String v)            { name = v; return this; }
        public Builder location(String v)        { location = v; return this; }
        public Builder enroll(String v)          { enroll = v; return this; }
        public Builder email(String v)           { email = v; return this; }
        public Builder status(String v)          { status = v; return this; }
        public Builder createdAt(LocalDateTime v){ createdAt = v; return this; }

        public EcosystemSignup build() {
            EcosystemSignup s = new EcosystemSignup();
            s.id = id; s.role = role; s.name = name; s.location = location;
            s.enroll = enroll; s.email = email; s.status = status;
            s.createdAt = createdAt;
            return s;
        }
    }

    public String getId()                       { return id; }
    public void setId(String id)                { this.id = id; }
    public String getRole()                     { return role; }
    public void setRole(String role)            { this.role = role; }
    public String getName()                     { return name; }
    public void setName(String name)            { this.name = name; }
    public String getLocation()                 { return location; }
    public void setLocation(String location)    { this.location = location; }
    public String getEnroll()                   { return enroll; }
    public void setEnroll(String enroll)        { this.enroll = enroll; }
    public String getEmail()                    { return email; }
    public void setEmail(String email)          { this.email = email; }
    public String getStatus()                   { return status; }
    public void setStatus(String status)        { this.status = status; }
    public LocalDateTime getCreatedAt()         { return createdAt; }
    public void setCreatedAt(LocalDateTime t)   { this.createdAt = t; }
}
