package com.harshit.question_service.util;

import com.harshit.question_service.model.Option;
import com.harshit.question_service.model.Question;
import com.harshit.question_service.model.QuestionWrapper;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CustomUtils {
    public List<QuestionWrapper> convertQuestionsToQuestionWrappers(List<Question> questions) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper();
            qw.setId(q.getId());
            qw.setQuestionTitle(q.getQuestionTitle());

            // options - just send content
            List<String> optionsContent = new ArrayList<>();
            List<Option> options = q.getOptions();
            for(Option o: options){
                optionsContent.add(o.getContent());
            }
            qw.setOptions(optionsContent);
            questionWrappers.add(qw);
        }
        return questionWrappers;
    }
}
