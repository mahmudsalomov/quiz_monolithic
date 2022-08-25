package uz.maniac4j.quiz_monolithic.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.payload.Payload;
import uz.maniac4j.quiz_monolithic.payload.Response;
import uz.maniac4j.quiz_monolithic.quiz.dto.AnswerDtoImpl;
import uz.maniac4j.quiz_monolithic.quiz.dto.QuizDto;
import uz.maniac4j.quiz_monolithic.quiz.model.Answer;
import uz.maniac4j.quiz_monolithic.quiz.model.Category;
import uz.maniac4j.quiz_monolithic.quiz.model.Quiz;
import uz.maniac4j.quiz_monolithic.user.User;


import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CategoryRepository categoryRepository, AnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.answerRepository = answerRepository;
    }

    public Response<?> add(QuizDto dto, User user){

        try {
            List<Category> categoryList = categoryRepository.findAllByOrganization(user.getOrganization());
            boolean idExists = categoryList.stream().anyMatch(item -> dto.getId().equals(item.getId()));
            if (!idExists) return Payload.badRequest();
            Quiz quiz = Quiz
                    .builder()
                    .rate(dto.getRate())
                    .title(dto.getTitle())
                    .text(dto.getText())
                    .category(categoryList.stream().filter(c->c.getId() == dto.getId()).findFirst().get())
                    .build();
            quiz = quizRepository.save(quiz);
            return Payload.ok(quiz);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.badRequest();
        }


    }

    public Response<?> one(long id,User user){
        try {

        }catch (Exception e){
            e.printStackTrace();
            return Payload.badRequest();
        }
    }
























    public Quiz one(Long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }

    public List<Quiz> all(){
        return quizRepository.findAll();
    }

    public Quiz add(QuizDto dto){
        try {
            Optional<Category> category = categoryRepository.findById(dto.getCategory().getId());
            if (category.isEmpty()) return null;
            Quiz quiz = Quiz
                    .builder()
                    .rate(dto.getRate())
                    .title(dto.getTitle())
                    .text(dto.getText())
                    .category(category.get())
                    .build();
            quiz = quizRepository.save(quiz);

            for (AnswerDtoImpl answerDto:dto.getAnswers()) {
                Answer answer = Answer
                        .builder()
                        .quiz(quiz)
                        .text(answerDto.getText())
                        .build();
                answer=answerRepository.save(answer);
                if (answerDto.isRight()) {
                    quiz.setRight_answer_id(answer.getId());
                    quiz = quizRepository.save(quiz);
                }
            }

            return quiz;
        }catch (Exception e){
            return null;
        }

    }

    public void delete(Long id){
        if (id == null) return;
        quizRepository.deleteById(id);
    }

    public Quiz edit(Quiz quiz){
        if (quiz.getId() == null) return null;
        Optional<Quiz> update = quizRepository.findById(quiz.getId());
        if (update.isPresent()){
            Quiz quizies = update.get();
            quizies.editing(quizies);
            return quizRepository.save(quizies);
        }
        return null;
    }

    public Boolean check(Long quizId, Long answerId) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(quizId);
            if (quiz.isPresent()){
                return quiz.get().getRight_answer_id().equals(answerId);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
