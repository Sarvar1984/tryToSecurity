package uz.forLearn.demo.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.forLearn.demo.entity.enums.RoleName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String name;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RegisterPartially {
        private String fullName;
        private String phoneNumber;
        private String password;
        private String retypePassword;
        private RoleName roleName;
    }

}
