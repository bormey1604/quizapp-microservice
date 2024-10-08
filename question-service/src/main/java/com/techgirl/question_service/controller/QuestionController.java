package com.techgirl.question_service.controller;


import com.techgirl.question_service.model.Question;
import com.techgirl.question_service.model.QuestionWrapper;
import com.techgirl.question_service.model.Response;
import com.techgirl.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> findQuestionsByCategory(@PathVariable String category) {
        List<Question> questions = questionService.findQuestionsByCategory(category);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
       String addQuestion = questionService.addQuestion(question);

        return new ResponseEntity<>(addQuestion, HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generate(@RequestParam String category, @RequestParam Integer numQuestion) {
       List<Integer> randomQuestions = questionService.getQuestionsForQuiz(category,numQuestion);

        return new ResponseEntity<>(randomQuestions, HttpStatus.OK);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        List<QuestionWrapper> randomQuestions = questionService.getQuestionsFromId(questionIds);

        return new ResponseEntity<>(randomQuestions, HttpStatus.OK);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        int getScore = questionService.getScore(responses);

        return new ResponseEntity<>(getScore, HttpStatus.OK);
    }



}
