package com.botleague.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    @Indexed(unique = true)
    private String email;

    private String password;
    private String fullName;
    private String phone;
    private String city;
    private String state;
    private String institution;
    private String category;
    private Set<String> roles = new HashSet<>();
    private boolean enabled;
    private boolean emailVerified;
    private String emailVerificationToken;
    private String passwordResetToken;
    private LocalDateTime passwordResetTokenExpiry;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private int totalScore = 0;
    private Integer nationalRank;
    private String profileImageUrl;

    public User() {}

    public User(String id, String username, String email, String password,
                String fullName, String phone, String city, String state,
                String institution, String category, Set<String> roles,
                boolean enabled, boolean emailVerified,
                String emailVerificationToken, String passwordResetToken,
                LocalDateTime passwordResetTokenExpiry, LocalDateTime createdAt,
                LocalDateTime updatedAt, int totalScore, Integer nationalRank,
                String profileImageUrl) {
        this.id = id; this.username = username; this.email = email;
        this.password = password; this.fullName = fullName; this.phone = phone;
        this.city = city; this.state = state; this.institution = institution;
        this.category = category; this.roles = roles; this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.emailVerificationToken = emailVerificationToken;
        this.passwordResetToken = passwordResetToken;
        this.passwordResetTokenExpiry = passwordResetTokenExpiry;
        this.createdAt = createdAt; this.updatedAt = updatedAt;
        this.totalScore = totalScore; this.nationalRank = nationalRank;
        this.profileImageUrl = profileImageUrl;
    }

    // ── Builder ──────────────────────────────────────────────────────────
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String id, username, email, password, fullName, phone,
                city, state, institution, category, emailVerificationToken,
                passwordResetToken, profileImageUrl;
        private Set<String> roles = new HashSet<>();
        private boolean enabled, emailVerified;
        private LocalDateTime passwordResetTokenExpiry;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt;
        private int totalScore = 0;
        private Integer nationalRank;

        public Builder id(String v)                          { id = v; return this; }
        public Builder username(String v)                    { username = v; return this; }
        public Builder email(String v)                       { email = v; return this; }
        public Builder password(String v)                    { password = v; return this; }
        public Builder fullName(String v)                    { fullName = v; return this; }
        public Builder phone(String v)                       { phone = v; return this; }
        public Builder city(String v)                        { city = v; return this; }
        public Builder state(String v)                       { state = v; return this; }
        public Builder institution(String v)                 { institution = v; return this; }
        public Builder category(String v)                    { category = v; return this; }
        public Builder roles(Set<String> v)                  { roles = v; return this; }
        public Builder enabled(boolean v)                    { enabled = v; return this; }
        public Builder emailVerified(boolean v)              { emailVerified = v; return this; }
        public Builder emailVerificationToken(String v)      { emailVerificationToken = v; return this; }
        public Builder passwordResetToken(String v)          { passwordResetToken = v; return this; }
        public Builder passwordResetTokenExpiry(LocalDateTime v) { passwordResetTokenExpiry = v; return this; }
        public Builder createdAt(LocalDateTime v)            { createdAt = v; return this; }
        public Builder updatedAt(LocalDateTime v)            { updatedAt = v; return this; }
        public Builder totalScore(int v)                     { totalScore = v; return this; }
        public Builder nationalRank(Integer v)               { nationalRank = v; return this; }
        public Builder profileImageUrl(String v)             { profileImageUrl = v; return this; }

        public User build() {
            User u = new User();
            u.id = id; u.username = username; u.email = email;
            u.password = password; u.fullName = fullName; u.phone = phone;
            u.city = city; u.state = state; u.institution = institution;
            u.category = category; u.roles = roles; u.enabled = enabled;
            u.emailVerified = emailVerified;
            u.emailVerificationToken = emailVerificationToken;
            u.passwordResetToken = passwordResetToken;
            u.passwordResetTokenExpiry = passwordResetTokenExpiry;
            u.createdAt = createdAt; u.updatedAt = updatedAt;
            u.totalScore = totalScore; u.nationalRank = nationalRank;
            u.profileImageUrl = profileImageUrl;
            return u;
        }
    }

    // ── Getters & Setters ─────────────────────────────────────────────────
    public String getId()                              { return id; }
    public void setId(String id)                       { this.id = id; }
    public String getUsername()                        { return username; }
    public void setUsername(String username)           { this.username = username; }
    public String getEmail()                           { return email; }
    public void setEmail(String email)                 { this.email = email; }
    public String getPassword()                        { return password; }
    public void setPassword(String password)           { this.password = password; }
    public String getFullName()                        { return fullName; }
    public void setFullName(String fullName)           { this.fullName = fullName; }
    public String getPhone()                           { return phone; }
    public void setPhone(String phone)                 { this.phone = phone; }
    public String getCity()                            { return city; }
    public void setCity(String city)                   { this.city = city; }
    public String getState()                           { return state; }
    public void setState(String state)                 { this.state = state; }
    public String getInstitution()                     { return institution; }
    public void setInstitution(String institution)     { this.institution = institution; }
    public String getCategory()                        { return category; }
    public void setCategory(String category)           { this.category = category; }
    public Set<String> getRoles()                      { return roles; }
    public void setRoles(Set<String> roles)            { this.roles = roles; }
    public boolean isEnabled()                         { return enabled; }
    public void setEnabled(boolean enabled)            { this.enabled = enabled; }
    public boolean isEmailVerified()                   { return emailVerified; }
    public void setEmailVerified(boolean emailVerified){ this.emailVerified = emailVerified; }
    public String getEmailVerificationToken()          { return emailVerificationToken; }
    public void setEmailVerificationToken(String t)    { this.emailVerificationToken = t; }
    public String getPasswordResetToken()              { return passwordResetToken; }
    public void setPasswordResetToken(String t)        { this.passwordResetToken = t; }
    public LocalDateTime getPasswordResetTokenExpiry() { return passwordResetTokenExpiry; }
    public void setPasswordResetTokenExpiry(LocalDateTime t) { this.passwordResetTokenExpiry = t; }
    public LocalDateTime getCreatedAt()                { return createdAt; }
    public void setCreatedAt(LocalDateTime t)          { this.createdAt = t; }
    public LocalDateTime getUpdatedAt()                { return updatedAt; }
    public void setUpdatedAt(LocalDateTime t)          { this.updatedAt = t; }
    public int getTotalScore()                         { return totalScore; }
    public void setTotalScore(int totalScore)          { this.totalScore = totalScore; }
    public Integer getNationalRank()                   { return nationalRank; }
    public void setNationalRank(Integer nationalRank)  { this.nationalRank = nationalRank; }
    public String getProfileImageUrl()                 { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
}
