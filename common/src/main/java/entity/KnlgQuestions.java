package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder
public class KnlgQuestions implements Serializable {
    int id, theme_id;
    String question;

    public KnlgQuestions(int theme_id, String question) {
        this.theme_id = theme_id;
        this.question = question;
    }
}
