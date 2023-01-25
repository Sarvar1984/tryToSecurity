package uz.forLearn.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.forLearn.demo.entity.Employer;
import uz.forLearn.demo.entity.Expert;
import uz.forLearn.demo.entity.Role;
import uz.forLearn.demo.entity.User;
import uz.forLearn.demo.entity.enums.RoleName;
import uz.forLearn.demo.exception.RecourseNotFoundException;
import uz.forLearn.demo.rest.request.AuthRequest;
import uz.forLearn.demo.rest.response.SingleResponse;
import uz.forLearn.demo.service.AuthService;
import uz.forLearn.demo.service.repository.EmployerRepository;
import uz.forLearn.demo.service.repository.ExpertRepository;
import uz.forLearn.demo.service.repository.RoleRepository;
import uz.forLearn.demo.service.repository.UserRepository;
import uz.forLearn.demo.utils.Utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployerRepository employerRepository;
    private final ExpertRepository expertRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  repository.findByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
    }

    @Override
    public SingleResponse registerPartially(AuthRequest.RegisterPartially request) {
        if(request.getPassword().equals(request.getRetypePassword()))
            return new SingleResponse(false,"Parollar mos emas", HttpStatus.BAD_REQUEST);
        if(repository.existsByPhoneNumber(request.getPhoneNumber()))
            return new SingleResponse(false,"Bunday raqam tizimda mavjud!", HttpStatus.BAD_REQUEST);

        Role role = roleRepository.findByRoleName(request.getRoleName()).orElseThrow(() ->
                new RecourseNotFoundException("THIS ROLE NOT FOUND"));

        User user=new User();
        user.setFullName(request.getFullName());
        user.setPhoneNumber(passwordEncoder.encode(request.getPhoneNumber()));
        user.getRoles().add(role);
        user.setEnable(true);
        String verCode= Utils.generateVerificationCode(1);
        user.setVerificationCode(verCode);
        user.setExpireDate(Instant.now().plus(5, ChronoUnit.MINUTES));

        if(request.getRoleName()== RoleName.EMPLOYER){
            Employer employer=employerRepository.save(new Employer());
            user.setEmployer(employer);
        }
        if(request.getRoleName()==RoleName.EXPERT){
            Expert save = expertRepository.save(new Expert(request.getFullName()));
            user.setExpert(save);
        }

        User save = repository.save(user);


        return new SingleResponse(true,"chala royhat ok",save.getId());
    }
}
