package uz.forLearn.demo.service;

import org.springframework.stereotype.Service;
import uz.forLearn.demo.rest.request.AuthRequest;
import uz.forLearn.demo.rest.response.SingleResponse;

@Service
public interface AuthService {
    SingleResponse registerPartially(AuthRequest.RegisterPartially request);
}
