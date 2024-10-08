package com.techgirl.question_service.service;


import com.techgirl.question_service.model.Question;
import com.techgirl.question_service.model.QuestionWrapper;
import com.techgirl.question_service.model.Response;
import com.techgirl.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> findQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "success";
    }

    public List<Integer> getQuestionsForQuiz(String category, Integer numQuestion) {
        return questionRepository.findRandomQuestionByCategory(category, numQuestion);
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        List<Question> questions = new ArrayList<>();
        List<QuestionWrapper> wrappers = new ArrayList<>();

        for (Integer questionId : questionIds) {
            Question question = questionRepository.findById(questionId).get();
            questions.add(question);
        }

        for (Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }

        return wrappers;
    }

    public int getScore(List<Response> responses) {
        int right = 0;

        for (Response response : responses) {
            Question question = questionRepository.findById(response.getId()).get();

            if(response.getResponse().equalsIgnoreCase(question.getRightAnswer())){
                right++;
            }
        }
        return right;
    }
}
