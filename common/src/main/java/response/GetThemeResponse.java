package response;

import entity.KnlgThemes;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetThemeResponse implements Response{
    KnlgThemes theme;
}
