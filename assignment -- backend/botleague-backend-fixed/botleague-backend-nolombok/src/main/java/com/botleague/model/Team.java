package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "teams")
public class Team {

    @Id
    private String id;
    private String name;
    private String captainId;
    private List<String> memberIds = new ArrayList<>();
    private String institution;
    private String category;
    private String city;
    private String state;
    private int totalScore = 0;
    private Integer nationalRank;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private String logoUrl;
    private boolean active = true;

    public Team() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, name, captainId, institution, category, city, state, logoUrl;
        private List<String> memberIds = new ArrayList<>();
        private int totalScore = 0;
        private Integer nationalRank;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt;
        private boolean active = true;

        public Builder id(String v)                    { id = v; return this; }
        public Builder name(String v)                  { name = v; return this; }
        public Builder captainId(String v)             { captainId = v; return this; }
        public Builder memberIds(List<String> v)       { memberIds = v; return this; }
        public Builder institution(String v)           { institution = v; return this; }
        public Builder category(String v)              { category = v; return this; }
        public Builder city(String v)                  { city = v; return this; }
        public Builder state(String v)                 { state = v; return this; }
        public Builder totalScore(int v)               { totalScore = v; return this; }
        public Builder nationalRank(Integer v)         { nationalRank = v; return this; }
        public Builder createdAt(LocalDateTime v)      { createdAt = v; return this; }
        public Builder updatedAt(LocalDateTime v)      { updatedAt = v; return this; }
        public Builder logoUrl(String v)               { logoUrl = v; return this; }
        public Builder active(boolean v)               { active = v; return this; }

        public Team build() {
            Team t = new Team();
            t.id = id; t.name = name; t.captainId = captainId;
            t.memberIds = memberIds; t.institution = institution;
            t.category = category; t.city = city; t.state = state;
            t.totalScore = totalScore; t.nationalRank = nationalRank;
            t.createdAt = createdAt; t.updatedAt = updatedAt;
            t.logoUrl = logoUrl; t.active = active;
            return t;
        }
    }

    public String getId()                          { return id; }
    public void setId(String id)                   { this.id = id; }
    public String getName()                        { return name; }
    public void setName(String name)               { this.name = name; }
    public String getCaptainId()                   { return captainId; }
    public void setCaptainId(String captainId)     { this.captainId = captainId; }
    public List<String> getMemberIds()             { return memberIds; }
    public void setMemberIds(List<String> v)       { this.memberIds = v; }
    public String getInstitution()                 { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getCategory()                    { return category; }
    public void setCategory(String category)       { this.category = category; }
    public String getCity()                        { return city; }
    public void setCity(String city)               { this.city = city; }
    public String getState()                       { return state; }
    public void setState(String state)             { this.state = state; }
    public int getTotalScore()                     { return totalScore; }
    public void setTotalScore(int totalScore)      { this.totalScore = totalScore; }
    public Integer getNationalRank()               { return nationalRank; }
    public void setNationalRank(Integer v)         { this.nationalRank = v; }
    public LocalDateTime getCreatedAt()            { return createdAt; }
    public void setCreatedAt(LocalDateTime t)      { this.createdAt = t; }
    public LocalDateTime getUpdatedAt()            { return updatedAt; }
    public void setUpdatedAt(LocalDateTime t)      { this.updatedAt = t; }
    public String getLogoUrl()                     { return logoUrl; }
    public void setLogoUrl(String logoUrl)         { this.logoUrl = logoUrl; }
    public boolean isActive()                      { return active; }
    public void setActive(boolean active)          { this.active = active; }
}
