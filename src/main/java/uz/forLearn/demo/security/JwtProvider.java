package uz.forLearn.demo.security;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import uz.forLearn.demo.entity.User;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtProvider {
    private JwtEncoder jwtEncoder;

    @Value("${spring.jwt.expiration}")
    private Long jwtExpirationTimeInMilliSecond;
    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUserName(principal.getId(),principal.getUsername());
    }

    private String generateTokenWithUserName(Long id, String username) {
        JwtClaimsSet build = JwtClaimsSet.builder()
                .id(id.toString())
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusMillis(jwtExpirationTimeInMilliSecond))
                .subject(username)
                .claim("scope", "ROLE_USER")
                .claim("userId", id)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(build)).getTokenValue();
    }


}
