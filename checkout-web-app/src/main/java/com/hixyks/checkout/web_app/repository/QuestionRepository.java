package com.hixyks.checkout.web_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hixyks.checkout.web_app.entity.Question;

/**
 * 
 * @author xyks@yahoo.com
 *
 */
public interface QuestionRepository extends JpaRepository<Question,Integer>{
   Optional<Question> findByTitleIgnoreCase(String title);

   
   @Query( nativeQuery = true, value ="select * from question q  where not exists(select 1 from training t where t.pass =1 and t.u_id =?2 and q.id = t.q_id) and q.category_id in ?1 limit 0,10")
   List<Question> findPendingForTrainingQuestions(List<Integer> categoryIds, Integer userId);
   
   @Query( nativeQuery = true, value ="select * from question q  where not exists(select 1 from training t where t.pass =1 and t.u_id =?1 and q.id = t.q_id) and q.category_id is Null limit 0,10")
   List<Question> findPendingForTrainingQuestionsWithoutCategory(Integer userId);
   
   @Query( nativeQuery = true, value ="select * from question q  where not exists(select 1 from training t where t.pass =1 and t.u_id =?1 and q.id = t.q_id) limit 0,10")
   List<Question> findPendingForTrainingQuestionsWithAllCategory(Integer userId);
   
   List<Question> findByTitleContaining(String title);
   
  
   
}
