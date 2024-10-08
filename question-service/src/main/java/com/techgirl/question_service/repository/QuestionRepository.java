package com.techgirl.question_service.repository;

import com.techgirl.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "select id from Question where category = ?1 order by random() limit ?2", nativeQuery = true)
    List<Integer> findRandomQuestionByCategory(String category,Integer numQuestion);
}
