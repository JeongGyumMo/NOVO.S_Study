package restapi.prac.controller;

import org.springframework.web.bind.annotation.*;
import restapi.prac.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    // 🔥 생성자 직접 작성
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest req) {
        userService.signup(req.getUsername(), req.getPassword());
        return "회원가입 완료";
    }

    static class SignupRequest {
        public String username;
        public String password;

        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
}
