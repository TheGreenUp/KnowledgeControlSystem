package response.stuffManagment;

import entity.Stuff;
import lombok.AllArgsConstructor;
import lombok.Data;
import response.Response;

import java.util.List;

@Data
@AllArgsConstructor
public class StuffListResponse implements Response {
    List<Stuff> stuff;

}
