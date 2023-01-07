package uz.maniac4j.quiz_monolithic.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.category.CategoryRepository;
import uz.maniac4j.quiz_monolithic.payload.Payload;
import uz.maniac4j.quiz_monolithic.payload.Response;
import uz.maniac4j.quiz_monolithic.category.Category;
import uz.maniac4j.quiz_monolithic.user.RoleName;
import uz.maniac4j.quiz_monolithic.user.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CategoryRepository categoryRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
    }

    public Response<?> add(QuizDto dto, User user){
        if (user==null) return Payload.unauthorized();
        try {
            List<Category> categoryList = categoryRepository.findAllByOrganization(user.getOrganization());
            boolean idExists = categoryList.stream().anyMatch(item -> dto.getCategory().getId().equals(item.getId()));
            if (!idExists) return Payload.badRequest();
            Quiz quiz = Quiz
                    .builder()
                    .title(dto.getTitle())
                    .text(dto.getText())
                    .answer(dto.getAnswer())
                    .a(dto.getA())
                    .b(dto.getB())
                    .c(dto.getC())
                    .d(dto.getD())
                    .category(categoryList.stream().filter(c-> Objects.equals(c.getId(), dto.getCategory().getId())).findFirst().get())
                    .build();
            quiz = quizRepository.save(quiz);
            return Payload.ok(quiz);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }

    public Response<?> one(long id,User user){
        try {
            Optional<Quiz> quizOptional = quizRepository.findById(id);

            boolean idExists = user.getRoles().stream().anyMatch(item -> RoleName.ADMIN.equals(item.getRoleName()));
            if (idExists && quizOptional.isPresent()) return Payload.ok(quizOptional.get());

            if (quizOptional.isEmpty()||!user.getOrganization().equals(quizOptional.get().getCategory().getOrganization())) return Payload.notFound();

            return Payload.ok(quizOptional.get());

        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }



    public Response<?> all(User user){
        try {
            if (user==null) return Payload.unauthorized();
            if(user.getRoles().stream().anyMatch(item -> RoleName.ADMIN.equals(item.getRoleName()))) return Payload.ok(quizRepository.findAll());
            List<Category> categoryList = categoryRepository.findAllByOrganization(user.getOrganization());
            List<Quiz> quizzes=new ArrayList<>();

            for (Category category : categoryList) {
                List<Quiz> list = quizRepository.findAllByCategory(category);
                quizzes.addAll(list);
            }
            return Payload.ok(quizzes);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }




















    public Quiz one(Long id){
        Optional<Quiz> quiz = quizRepository.findById(id);
        return quiz.orElse(null);
    }

    public List<Quiz> all(){
        return quizRepository.findAll();
    }

//    public Quiz add(QuizDto dto){
//        try {
//            Optional<Category> category = categoryRepository.findById(dto.getCategory().getId());
//            if (category.isEmpty()) return null;
//            Quiz quiz = Quiz
//                    .builder()
//                    .rate(dto.getRate())
//                    .title(dto.getTitle())
//                    .text(dto.getText())
//                    .category(category.get())
//                    .build();
//            quiz = quizRepository.save(quiz);
//
//            for (AnswerDtoImpl answerDto:dto.getAnswers()) {
//                Answer answer = Answer
//                        .builder()
//                        .quiz(quiz)
//                        .text(answerDto.getText())
//                        .build();
//                answer=answerRepository.save(answer);
//                if (answerDto.isRight()) {
//                    quiz.setRight_answer_id(answer.getId());
//                    quiz = quizRepository.save(quiz);
//                }
//            }
//
//            return quiz;
//        }catch (Exception e){
//            return null;
//        }
//
//    }

    public void delete(Long id){
        if (id == null) return;
        quizRepository.deleteById(id);
    }

    public Quiz edit(Quiz quiz){
        Optional<Quiz> update = quizRepository.findById(quiz.getId());
        if (update.isPresent()){


            Quiz q = update.get();
            q.editing(q);
            return quizRepository.save(q);
        }
        return null;
    }

    public Response<?> edit(QuizDto quiz,User user){
        try {



            if (user==null) return Payload.unauthorized();
            if (quiz.getId() == null) return Payload.badRequest();

            Optional<Quiz> update = quizRepository.findById(quiz.getId());

            if (update.isEmpty()) return Payload.badRequest();

            if (!update.get().getCategory().getOrganization().equals(user.getOrganization())) return Payload.badRequest();

            if (!Objects.equals(update.get().getCategory().getId(), quiz.getCategory().getId())){
                Optional<Category> category = categoryRepository.findByIdAndOrganization(quiz.getCategory().getId(),user.getOrganization());
                if (category.isEmpty()) return Payload.badRequest();
                update.get().setCategory(category.get());
            }

            update.get().editing(quiz);

            return Payload.ok(quizRepository.save(update.get()));

        }catch (Exception e){
            return Payload.conflict();
        }
    }

    public Boolean check(Long quizId, AnswerEnum answer) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(quizId);
            if (quiz.isPresent()){
                return quiz.get().getAnswer().equals(answer);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
