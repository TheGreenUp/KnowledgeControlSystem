package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder
public class KnlgAnswers implements Serializable {
    int id, question_id;
    boolean is_correct;
    String answer_text;

    public KnlgAnswers(int question_id, String answer_text, boolean is_correct) {
        this.question_id = question_id;
        this.answer_text = answer_text;
        this.is_correct = is_correct;
    }
    public int intCorrectAnswer(){
        if (is_correct) return 1;
        else return 0;
    }
}
