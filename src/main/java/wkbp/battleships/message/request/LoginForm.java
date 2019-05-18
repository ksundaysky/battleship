package wkbp.battleships.message.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Represents login form on the client side
 *
 * @author Wiktor Rup
 * @author Patryk Kucharski
 * @author Krzysztof Niedzielski
 * @author Bartosz Kupajski
 */
@NoArgsConstructor
@Getter
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
}