package uz.maniac4j.quiz_monolithic.quiz;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.maniac4j.quiz_monolithic.security.CurrentUser;
import uz.maniac4j.quiz_monolithic.user.User;

@RestController
@RequestMapping("api/quiz")
@CrossOrigin
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody QuizDto quizDto, @CurrentUser User user){
        return quizService.add(quizDto,user).response();
    }

    @PostMapping("/edit")
    public HttpEntity<?> edit(@RequestBody QuizDto quizDto, @CurrentUser User user){
        return quizService.edit(quizDto,user).response();
    }

//    @GetMapping("/one/{}")
//    public HttpEntity<?> one(@PathVariable long id,@CurrentUser User user){
//        return quizService.one(id, user).response();
//    }

    @GetMapping("/one")
    public HttpEntity<?> one(@RequestParam long id,@CurrentUser User user){
        return quizService.one(id, user).response();
    }


    @GetMapping("/all")
    public HttpEntity<?> all(@CurrentUser User user){
        return quizService.all(user).response();
    }

}
