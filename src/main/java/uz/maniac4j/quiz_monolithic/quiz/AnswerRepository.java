package uz.maniac4j.quiz_monolithic.quiz;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.maniac4j.quiz_monolithic.quiz.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {


}
