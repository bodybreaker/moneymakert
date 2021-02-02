package login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginModel {
    private String idEnc;
    private String pwdEnc;
    private String certPwdEnc;

}
