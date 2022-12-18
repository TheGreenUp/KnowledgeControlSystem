package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class KnlgThemes implements Serializable {
    int id;
    String themeText, description;

    public KnlgThemes(String themeText, String description) {
        this.themeText = themeText;
        this.description = description;
    }
}
