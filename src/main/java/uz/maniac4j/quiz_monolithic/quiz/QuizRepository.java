package uz.maniac4j.quiz_monolithic.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.maniac4j.quiz_monolithic.quiz.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
