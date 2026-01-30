package restapi.prac.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restapi.prac.model.User;
import restapi.prac.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // 생성자
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {
        userService.signup(req.getUsername(), req.getPassword());
        return "회원가입 완료";
    }

    // 로그인 추가
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req, HttpServletRequest request) {

        User user = userService.findByUsername(req.getUsername());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 없음");
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 틀림");
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", user);

        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loginUser") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 안됨");
        }

        User user = (User) session.getAttribute("loginUser");
        return ResponseEntity.ok(user.getUsername());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 완료");
    }

    // 회원가입 DTO
    static class SignupRequest {
        public String username;
        public String password;

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }

    // 로그인 DTO 추가
    static class LoginRequest {
        public String username;
        public String password;

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
}
