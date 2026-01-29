import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Signup() {
  const [form, setForm] = useState({ username: "", password: "" });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSignup = async () => {
    try {
      await axios.post("http://localhost:8080/api/users/signup", form);
      alert("회원가입 성공!");
      navigate("/");
    } catch (e) {
      alert("회원가입 실패");
    }
  };

  return (
    <div style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "80vh"
    }}>
      <div style={{
        width: "320px",
        padding: "30px",
        border: "1px solid #ddd",
        borderRadius: "10px",
        textAlign: "center",
        boxShadow: "0 4px 10px rgba(0,0,0,0.1)"
      }}>
        <h2 style={{ marginBottom: "30px" }}>회원가입</h2>

        <input
          name="username"
          placeholder="아이디"
          onChange={handleChange}
          value={form.username}
          style={{ width: "85%", padding: "10px", marginBottom: "0 auto 10px", margin: "0 auto 15px"}}
        />

        <input
          name="password"
          type="password"
          placeholder="비밀번호"
          onChange={handleChange}
          value={form.password}
          style={{ width: "85%", padding: "10px", marginBottom: "0 auto 10px", margin: "0 auto 30px" }}
        />

        <button
          onClick={handleSignup}
          style={{
            width: "90%",
            padding: "10px",
            backgroundColor: "#4CAF50",
            color: "white",
            border: "none",
            borderRadius: "5px",
            cursor: "pointer",
            fontWeight: "bold"
          }}
        >
          가입하기
        </button>
      </div>
    </div>
  );
}

export default Signup;
