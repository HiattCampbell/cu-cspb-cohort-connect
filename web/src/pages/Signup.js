import { useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";

import { apiRequest, setToken } from "../api";


const USER_TYPES = ['current_student', 'former_student']

function Signup({ onSignup }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [type, setType] = useState("");
  const [displayName, setDisplayName] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  // const [lookingForMentee, setLookingForMentee] = useState([]);
  // const [lookingForMentor, setLookingForMentor] = useState([]);
  // const [mentorTo, setMentoTo] = useState(null);

  async function handleSignup(e) {
    e.preventDefault();

    try {
      const data = await apiRequest("/api/auth/register", {
        method: "POST",
        body: JSON.stringify({
          email,
          password, 
          displayName,
          type,
        })

      })
      setToken(data.token);
      onSignup();
      navigate("/");
    } catch (err) {
      console.log("Error registering user: ", err);
      setError("failed to register user");
    }
  }

  return (
    <div>
      <form onSubmit={handleSignup}>
        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Type{" "}
            <select value={type} onChange={e => setType(e.target.value)}>
              {USER_TYPES.map(t => (
                <option key={t} value={t}>
                  {t == 'current_student' ? 'Current Student' : 'Former Student'}
                </option>
              ))}
            </select>
          </label>
        </div>

        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Email
            <br />
            <input
              type="email"
              value={email}
              onChange={e => setEmail(e.target.value)}
              maxLength={100}
              required
              style={{ width: "100%" }}
            />
          </label>
        </div>
        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Password
            <br />
            <input
              type="password"
              value={password}
              onChange={e => setPassword(e.target.value)}
              maxLength={100}
              required
              style={{ width: "100%" }}
            />
          </label>
        </div>

        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Display Name
            <br />
            <input
              type="text"
              value={displayName}
              onChange={e => setDisplayName(e.target.value)}
              maxLength={100}
              required
              style={{ width: "100%" }}
            />
          </label>
        </div>

        <button type="submit">Post</button>
      </form>

      <h2>Already have an account?</h2>
       <NavLink to='login'>Login</NavLink>
    </div>
  )

}

export default Signup;
