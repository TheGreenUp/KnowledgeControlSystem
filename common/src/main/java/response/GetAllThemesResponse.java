package response;

import entity.KnlgThemes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetAllThemesResponse implements Response{
    ArrayList<KnlgThemes> themes;
}
