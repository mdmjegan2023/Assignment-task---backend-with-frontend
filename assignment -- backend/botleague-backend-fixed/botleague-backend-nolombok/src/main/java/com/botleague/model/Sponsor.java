package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "sponsors")
public class Sponsor {

    @Id
    private String id;
    private String name;
    private String logoUrl;
    private String websiteUrl;
    private String tier;
    private boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Sponsor() {}

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, name, logoUrl, websiteUrl, tier;
        private boolean active = true;
        private LocalDateTime createdAt = LocalDateTime.now();

        public Builder id(String v)              { id = v; return this; }
        public Builder name(String v)            { name = v; return this; }
        public Builder logoUrl(String v)         { logoUrl = v; return this; }
        public Builder websiteUrl(String v)      { websiteUrl = v; return this; }
        public Builder tier(String v)            { tier = v; return this; }
        public Builder active(boolean v)         { active = v; return this; }
        public Builder createdAt(LocalDateTime v){ createdAt = v; return this; }

        public Sponsor build() {
            Sponsor s = new Sponsor();
            s.id = id; s.name = name; s.logoUrl = logoUrl;
            s.websiteUrl = websiteUrl; s.tier = tier;
            s.active = active; s.createdAt = createdAt;
            return s;
        }
    }

    public String getId()                       { return id; }
    public void setId(String id)                { this.id = id; }
    public String getName()                     { return name; }
    public void setName(String name)            { this.name = name; }
    public String getLogoUrl()                  { return logoUrl; }
    public void setLogoUrl(String logoUrl)      { this.logoUrl = logoUrl; }
    public String getWebsiteUrl()               { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl){ this.websiteUrl = websiteUrl; }
    public String getTier()                     { return tier; }
    public void setTier(String tier)            { this.tier = tier; }
    public boolean isActive()                   { return active; }
    public void setActive(boolean active)       { this.active = active; }
    public LocalDateTime getCreatedAt()         { return createdAt; }
    public void setCreatedAt(LocalDateTime t)   { this.createdAt = t; }
}
