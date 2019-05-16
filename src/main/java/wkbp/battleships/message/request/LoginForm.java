package wkbp.battleships.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Represents login form on the client side
 *
 * @author Patryk Kucharski
 * @author Wiktor Wrup
 */
public class LoginForm {
    @NotBlank
    @Size(min = 3, max = 60)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public LoginForm(@NotBlank @Size(min = 3, max = 60) String username, @NotBlank @Size(min = 6, max = 40) String password) {
        this.username = username;
        this.password = password;
    }

    public LoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    // usunięte settery jakby co nie hulało to wygenerować znowu
}