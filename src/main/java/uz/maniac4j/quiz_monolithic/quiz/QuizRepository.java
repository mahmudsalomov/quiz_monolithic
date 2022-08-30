package uz.maniac4j.quiz_monolithic.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.maniac4j.quiz_monolithic.category.Category;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByCategory(Category category);
}
