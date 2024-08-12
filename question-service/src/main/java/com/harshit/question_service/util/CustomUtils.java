package com.harshit.question_service.util;

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
            qw.setOptions(q.getOptions());
            questionWrappers.add(qw);
        }
        return questionWrappers;
    }
}
