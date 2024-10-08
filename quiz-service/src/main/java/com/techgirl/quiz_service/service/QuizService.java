package com.techgirl.quiz_service.service;

import com.techgirl.quiz_service.feign.QuizInterface;
import com.techgirl.quiz_service.model.QuestionWrapper;
import com.techgirl.quiz_service.model.Quiz;
import com.techgirl.quiz_service.model.Response;
import com.techgirl.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;

    public String createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.generate(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();

        return quizInterface.getQuestionsFromId(questionIds);
    }

    public int calculateResult(int id, List<Response> responses) {

        int score =  quizInterface.getScore(responses);

        return score;
    }
}
