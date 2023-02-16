package uz.maniac4j.quiz_monolithic.exam;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/exam")
@CrossOrigin
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

//    @PostMapping("/add")
//    public HttpEntity<?> add(ExamDto dto){
//        return examService.add(dto).responce();
//    }
}
