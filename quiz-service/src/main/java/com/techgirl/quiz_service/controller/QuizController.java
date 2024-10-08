package com.techgirl.quiz_service.controller;

import com.techgirl.quiz_service.model.QuestionWrapper;
import com.techgirl.quiz_service.model.Quiz;
import com.techgirl.quiz_service.model.QuizDto;
import com.techgirl.quiz_service.model.Response;
import com.techgirl.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        String response = quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        List<QuestionWrapper> questionList = quizService.getQuizQuestions(id);
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> responses) {
        int result = quizService.calculateResult(id, responses);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
