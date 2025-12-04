import { useEffect, useState } from "react";
import { apiRequest } from "../api";

const BULLETIN_TYPES = ["event", "announcement", "question", "misc"];

function Bulletins() {
  const [bulletins, setBulletins] = useState([]);
  const [error, setError] = useState(null);

  const [type, setType] = useState("misc");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  // load existing bulletins
  useEffect(() => {
    async function getBulletins() {
      const data = await apiRequest("/api/v1/bulletins");
      setBulletins(data);
    }

    getBulletins().catch(err => {
      console.log("Error fetching bulletins: ", err);
      setError("failed to load bulletins");
    });
  }, []); 

  async function handleCreate(e) {
    e.preventDefault();

    try {
      const newBulletin = await apiRequest("/api/v1/bulletins", {
        method: "POST",
        body: JSON.stringify({
          type,
          title,
          content,
        }),
      });

      setBulletins(prev => [newBulletin, ...prev]);
      setTitle("");
      setContent("");
      setType("misc");
    } catch (err) {
      console.log("Error creating bulletin: ", err);
      setError("failed to create bulletin");
    }
  }

  return (
    <div className="bulletin-board">
      <div className="post-box">
      <h3>Post a bulletin</h3>

      <form onSubmit={handleCreate}>
        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Type{" "}
            <select value={type} onChange={e => setType(e.target.value)}>
              {BULLETIN_TYPES.map(t => (
                <option key={t} value={t}>
                  {t}
                </option>
              ))}
            </select>
          </label>
        </div>

        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Title
            <br />
            <input
              type="text"
              value={title}
              onChange={e => setTitle(e.target.value)}
              maxLength={30}
              required
              style={{ width: "100%" }}
            />
          </label>
        </div>

        <div style={{ marginBottom: "0.5rem" }}>
          <label>
            Content
            <br />
            <textarea
              value={content}
              onChange={e => setContent(e.target.value)}
              maxLength={300}
              rows={4}
              style={{ width: "100%" }}
            />
          </label>
        </div>

        <button type="submit">Post</button>
      </form>
      </div>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <div className="bulletins">
      {bulletins.map(bulletin => (
        <div className="bulletin" key={bulletin.id}>
          <h2>{bulletin.title}</h2>
          <p>{bulletin.content}</p>
        </div>
      ))}
      </div>
    </div>
  );
}

export default Bulletins;
