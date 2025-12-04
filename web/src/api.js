// web/src/api.js
// const API_BASE = "http://localhost:8080";
const API_BASE = "http://18.218.119.39:8080";



let token = localStorage.getItem("jwt") || null;

export function setToken(newToken) {
  token = newToken;
  if (newToken) {
    localStorage.setItem("jwt", newToken);
  } else {
    localStorage.removeItem("jwt");
  }
}

export function getToken() {
  if (!token) {
    token = localStorage.getItem("jwt");
  }
  return token;
}

export async function apiRequest(path, options = {}) {
  const jwt = getToken();

  const headers = {
    "Content-Type": "application/json",
    ...(options.headers || {}),
    ...(jwt ? { Authorization: `Bearer ${jwt}` } : {}),
  };

  const res = await fetch(`${API_BASE}${path}`, {
    ...options,
    headers,
  });

  if (!res.ok) {
    const text = await res.text().catch(() => "");
    console.error("API error", res.status, text);
    throw new Error(`HTTP ${res.status}`);
  }

  if (res.status === 204) return null;
  return res.json();
}
