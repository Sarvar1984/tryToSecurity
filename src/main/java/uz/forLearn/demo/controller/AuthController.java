package uz.forLearn.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.forLearn.demo.rest.request.AuthRequest;
import uz.forLearn.demo.rest.response.SingleResponse;
import uz.forLearn.demo.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register/partially")
    public ResponseEntity<?>registerPartially(@RequestBody AuthRequest.RegisterPartially  request){
        SingleResponse response=service.registerPartially(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }



}
