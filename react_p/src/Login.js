import { useState } from "react";
import api from "./api/axios";


function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      await api.post("/users/login", {
        username: username.trim(),
        password: password.trim(),
      }, {
        withCredentials: true
      });

      alert("로그인 성공!");
      window.location.href = "/";
    } catch (err) {
      alert("아이디 또는 비밀번호가 틀렸습니다");
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

      <h2>로그인</h2>
      <input
        type="text"
        placeholder="아이디"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        style={{ width: "85%", padding: "10px", marginBottom: "0 auto 10px", margin: "0 auto 15px"}}
      />
      <br />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        style={{ width: "85%", padding: "10px", marginBottom: "0 auto 10px", margin: "0 auto 15px"}}
      />
      <br />
      <button onClick={handleLogin}
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
        로그인
      </button>
    </div>
   </div>
  );
}

export default Login;
