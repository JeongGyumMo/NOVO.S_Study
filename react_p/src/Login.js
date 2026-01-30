import { useState } from "react";
import axios from "axios";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const res = await axios.post("http://localhost:8080/api/users/login", {
        username: username.trim(),
        password: password.trim(),
      },
      {
        withCredentials: true
      });

      alert("로그인 성공!");
      window.location.href = "/"; // 홈으로 이동
    } catch (err) {
      alert("아이디 또는 비밀번호가 틀렸습니다");
    }
  };

  return (
    <div>
      <h2>로그인</h2>
      <input
        type="text"
        placeholder="아이디"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
      />
      <br />
      <input
        type="password"
        placeholder="비밀번호"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
      />
      <br />
      <button onClick={handleLogin}>로그인</button>
    </div>
  );
}

export default Login;
