import React, { useState } from "react";
import "./AuthModal.css";
import { apiFetch } from "../api/api";

export default function AuthModal({ onClose, defaultTab = "login" }) {
  const [tab, setTab] = useState(defaultTab);

  return (
    <div className="auth-overlay" onClick={(e) => e.target === e.currentTarget && onClose()}>
      <div className="auth-modal">
        <button className="auth-modal__close" onClick={onClose}>×</button>

        <h2 className="auth-modal__title">
          {tab === "login" ? "Welcome Back" : "Create Account"}
        </h2>
        <p className="auth-modal__sub">
          {tab === "login" ? "Sign in to your BotLeague account" : "Join the BotLeague ecosystem"}
        </p>

        <div className="auth-tabs">
          <button className={`auth-tab ${tab === "login" ? "active" : ""}`} onClick={() => setTab("login")}>LOGIN</button>
          <button className={`auth-tab ${tab === "register" ? "active" : ""}`} onClick={() => setTab("register")}>REGISTER</button>
        </div>

        {tab === "login"
          ? <LoginForm onClose={onClose} />
          : <RegisterForm onClose={onClose} />
        }
      </div>
    </div>
  );
}

function LoginForm({ onClose }) {
  const [form, setForm] = useState({ usernameOrEmail: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm((f) => ({ ...f, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    try {
      const data = await apiFetch("/api/auth/login", {
        method: "POST",
        body: JSON.stringify(form),
      });
      localStorage.setItem("token", data.accessToken);
      localStorage.setItem("user", JSON.stringify({
        username: data.username,
        fullName: data.fullName,
        email: data.email,
        roles: data.roles,
      }));
      window.dispatchEvent(new Event("auth-change"));
      onClose();
    } catch (err) {
      setError(err.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="auth-form" onSubmit={handleSubmit}>
      {error && <div className="auth-error">{error}</div>}
      <div className="auth-field">
        <label>Username or Email</label>
        <input name="usernameOrEmail" value={form.usernameOrEmail} onChange={handleChange} placeholder="Enter username or email" required />
      </div>
      <div className="auth-field">
        <label>Password</label>
        <input name="password" type="password" value={form.password} onChange={handleChange} placeholder="Enter password" required />
      </div>
      <button className="btn btn-primary btn-block auth-submit" type="submit" disabled={loading}>
        {loading ? "Signing in…" : "LOGIN"}
      </button>
    </form>
  );
}

function RegisterForm({ onClose }) {
  const [form, setForm] = useState({
    username: "", email: "", password: "", fullName: "",
    phone: "", city: "", state: "", institution: "", category: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm((f) => ({ ...f, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    setSuccess("");
    try {
      const data = await apiFetch("/api/auth/register", {
        method: "POST",
        body: JSON.stringify(form),
      });
      localStorage.setItem("token", data.accessToken);
      localStorage.setItem("user", JSON.stringify({
        username: data.username,
        fullName: data.fullName,
        email: data.email,
        roles: data.roles,
      }));
      setSuccess("Account created successfully!");
      window.dispatchEvent(new Event("auth-change"));
      setTimeout(() => onClose(), 1000);
    } catch (err) {
      setError(err.message || "Registration failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="auth-form" onSubmit={handleSubmit}>
      {error && <div className="auth-error">{error}</div>}
      {success && <div className="auth-success">{success}</div>}

      <div className="auth-row">
        <div className="auth-field">
          <label>Full Name *</label>
          <input name="fullName" value={form.fullName} onChange={handleChange} placeholder="John Doe" required />
        </div>
        <div className="auth-field">
          <label>Username *</label>
          <input name="username" value={form.username} onChange={handleChange} placeholder="johndoe" required />
        </div>
      </div>

      <div className="auth-field">
        <label>Email *</label>
        <input name="email" type="email" value={form.email} onChange={handleChange} placeholder="john@example.com" required />
      </div>

      <div className="auth-field">
        <label>Password *</label>
        <input name="password" type="password" value={form.password} onChange={handleChange} placeholder="Min 6 characters" required />
      </div>

      <div className="auth-row">
        <div className="auth-field">
          <label>Phone</label>
          <input name="phone" value={form.phone} onChange={handleChange} placeholder="+91 98765 43210" />
        </div>
        <div className="auth-field">
          <label>City</label>
          <input name="city" value={form.city} onChange={handleChange} placeholder="Delhi" />
        </div>
      </div>

      <div className="auth-row">
        <div className="auth-field">
          <label>State</label>
          <input name="state" value={form.state} onChange={handleChange} placeholder="Delhi" />
        </div>
        <div className="auth-field">
          <label>Institution</label>
          <input name="institution" value={form.institution} onChange={handleChange} placeholder="NIT Delhi" />
        </div>
      </div>

      <div className="auth-field">
        <label>Category</label>
        <select name="category" value={form.category} onChange={handleChange}>
          <option value="">Select category</option>
          <option value="Robo Minds">Robo Minds</option>
          <option value="Young Engineers">Young Engineers</option>
          <option value="Pro League">Pro League</option>
        </select>
      </div>

      <button className="btn btn-primary btn-block auth-submit" type="submit" disabled={loading}>
        {loading ? "Creating Account…" : "CREATE ACCOUNT"}
      </button>
    </form>
  );
}
