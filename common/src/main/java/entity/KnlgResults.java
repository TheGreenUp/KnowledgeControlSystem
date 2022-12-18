package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class KnlgResults implements Serializable {
    int id,stuff_id,correct_answers;
    String result;

    public KnlgResults(int stuff_id, int correct_answers, String result) {
        this.stuff_id = stuff_id;
        this.correct_answers = correct_answers;
        this.result = result;
    }
}
