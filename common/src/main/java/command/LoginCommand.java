package command;

import lombok.Data;

@Data
public class LoginCommand extends CommandDto{
    String email, password;
    public LoginCommand(String email, String password) {
        this.command = Command.LOGIN;
        this.email = email;
        this.password = password;
    }
}
