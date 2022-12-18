package response;

import entity.KnlgQuestions;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class GetQuestionsReponse implements Response{
    ArrayList<KnlgQuestions> questions;

}
