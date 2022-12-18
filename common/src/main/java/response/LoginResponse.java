package response;


import entity.Stuff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse implements Response {
    Stuff stuff;
}
