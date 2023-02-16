package uz.maniac4j.quiz_monolithic.exam;

import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.payload.ApiResponse;
import uz.maniac4j.quiz_monolithic.payload.Payload;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public ApiResponse<?> add(ExamDto dto){
        if (dto.getName().isEmpty()) return null;
        return examRepository.save(dto);
        }

        return
    }
}
