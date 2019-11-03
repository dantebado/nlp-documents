package nlp.frba.utn.documents.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String credential;

    @NotBlank
    private String password;

    public String getUsernameOrEmail() {
        return credential;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.credential = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}