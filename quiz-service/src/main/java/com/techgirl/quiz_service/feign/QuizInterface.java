package com.techgirl.quiz_service.feign;

import com.techgirl.quiz_service.model.QuestionWrapper;
import com.techgirl.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
@Component
public interface QuizInterface {

    @GetMapping("/api/v1/questions/generate")
    public List<Integer> generate(@RequestParam String category, @RequestParam Integer numQuestion);


    @PostMapping("/api/v1/questions/getQuestions")
    public List<QuestionWrapper> getQuestionsFromId(@RequestBody List<Integer> questionIds);


    @PostMapping("/api/v1/questions/getScore")
    public Integer getScore(@RequestBody List<Response> responses);

}
