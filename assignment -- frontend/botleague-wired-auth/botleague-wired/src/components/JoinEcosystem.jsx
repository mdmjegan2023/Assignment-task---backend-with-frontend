import React, { useState } from "react";
import "./JoinEcosystem.css";
import { apiFetch } from "../api/api";

const ROLES = [
  { label: "BECOME A JUDGE",    value: "JUDGE" },
  { label: "VOLUNTEER",         value: "VOLUNTEER" },
  { label: "COMMUNITY MEMBER",  value: "COMMUNITY_MEMBER" },
];

function SignupCard({ role, label }) {
  const [form, setForm]       = useState({ name: "", location: "", enroll: "", email: "" });
  const [status, setStatus]   = useState(null); // "ok" | "err" | null
  const [msg, setMsg]         = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) =>
    setForm((f) => ({ ...f, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setStatus(null);
    try {
      await apiFetch("/api/ecosystem/signup", {
        method: "POST",
        body: JSON.stringify({ role, ...form }),
      });
      setStatus("ok");
      setMsg("Signed up successfully!");
      setForm({ name: "", location: "", enroll: "", email: "" });
    } catch (err) {
      setStatus("err");
      setMsg(err.message || "Something went wrong");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="join-card" onSubmit={handleSubmit}>
      <h3>{label}</h3>
      <input name="name"     value={form.name}     onChange={handleChange} type="text"  placeholder="Name"     required />
      <input name="location" value={form.location} onChange={handleChange} type="text"  placeholder="Location" required />
      <input name="enroll"   value={form.enroll}   onChange={handleChange} type="text"  placeholder="Enroll"   required />
      <input name="email"    value={form.email}    onChange={handleChange} type="email" placeholder="Email" />
      {msg && (
        <p style={{ color: status === "ok" ? "#4caf50" : "#f44336", fontSize: "0.85rem", margin: "4px 0" }}>
          {msg}
        </p>
      )}
      <button className="btn btn-primary btn-block" type="submit" disabled={loading}>
        {loading ? "Submitting…" : "SIGN UP"}
      </button>
    </form>
  );
}

export default function JoinEcosystem() {
  return (
    <section className="section join">
      <div className="container">
        <h2 className="section-heading join__title">Join The Ecosystem</h2>
        <div className="join__grid">
          {ROLES.map((r) => (
            <SignupCard key={r.value} role={r.value} label={r.label} />
          ))}
        </div>
      </div>
    </section>
  );
}
