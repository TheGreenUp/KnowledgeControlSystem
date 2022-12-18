package response;

import command.GetAnswersCommand;
import entity.KnlgAnswers;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
@Data
@AllArgsConstructor
public class GetAnswersResponse implements Response{
    ArrayList<KnlgAnswers> answers;

}
