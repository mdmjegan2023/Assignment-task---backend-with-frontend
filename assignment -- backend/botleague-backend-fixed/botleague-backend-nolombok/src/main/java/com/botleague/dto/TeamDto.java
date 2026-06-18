package com.botleague.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class TeamDto {

    public static class CreateRequest {
        @NotBlank(message = "Team name is required")
        private String name;
        private String institution, category, city, state, logoUrl;

        public String getName()                { return name; }
        public void setName(String v)          { this.name = v; }
        public String getInstitution()         { return institution; }
        public void setInstitution(String v)   { this.institution = v; }
        public String getCategory()            { return category; }
        public void setCategory(String v)      { this.category = v; }
        public String getCity()                { return city; }
        public void setCity(String v)          { this.city = v; }
        public String getState()               { return state; }
        public void setState(String v)         { this.state = v; }
        public String getLogoUrl()             { return logoUrl; }
        public void setLogoUrl(String v)       { this.logoUrl = v; }
    }

    public static class UpdateRequest {
        private String name, institution, category, city, state, logoUrl;

        public String getName()                { return name; }
        public void setName(String v)          { this.name = v; }
        public String getInstitution()         { return institution; }
        public void setInstitution(String v)   { this.institution = v; }
        public String getCategory()            { return category; }
        public void setCategory(String v)      { this.category = v; }
        public String getCity()                { return city; }
        public void setCity(String v)          { this.city = v; }
        public String getState()               { return state; }
        public void setState(String v)         { this.state = v; }
        public String getLogoUrl()             { return logoUrl; }
        public void setLogoUrl(String v)       { this.logoUrl = v; }
    }

    public static class Response {
        private String id, name, captainId, captainName, institution,
                category, city, state, logoUrl;
        private List<String> memberIds, memberNames;
        private int totalScore;
        private Integer nationalRank;
        private boolean active;

        public String getId()                      { return id; }
        public void setId(String v)                { this.id = v; }
        public String getName()                    { return name; }
        public void setName(String v)              { this.name = v; }
        public String getCaptainId()               { return captainId; }
        public void setCaptainId(String v)         { this.captainId = v; }
        public String getCaptainName()             { return captainName; }
        public void setCaptainName(String v)       { this.captainName = v; }
        public List<String> getMemberIds()         { return memberIds; }
        public void setMemberIds(List<String> v)   { this.memberIds = v; }
        public List<String> getMemberNames()       { return memberNames; }
        public void setMemberNames(List<String> v) { this.memberNames = v; }
        public String getInstitution()             { return institution; }
        public void setInstitution(String v)       { this.institution = v; }
        public String getCategory()                { return category; }
        public void setCategory(String v)          { this.category = v; }
        public String getCity()                    { return city; }
        public void setCity(String v)              { this.city = v; }
        public String getState()                   { return state; }
        public void setState(String v)             { this.state = v; }
        public int getTotalScore()                 { return totalScore; }
        public void setTotalScore(int v)           { this.totalScore = v; }
        public Integer getNationalRank()           { return nationalRank; }
        public void setNationalRank(Integer v)     { this.nationalRank = v; }
        public String getLogoUrl()                 { return logoUrl; }
        public void setLogoUrl(String v)           { this.logoUrl = v; }
        public boolean isActive()                  { return active; }
        public void setActive(boolean v)           { this.active = v; }
    }

    public static class AddMemberRequest {
        @NotBlank private String userId;
        public String getUserId()              { return userId; }
        public void setUserId(String v)        { this.userId = v; }
    }
}
