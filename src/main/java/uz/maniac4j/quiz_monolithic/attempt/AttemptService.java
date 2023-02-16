package uz.maniac4j.quiz_monolithic.attempt;

import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.exam.Exam;
import uz.maniac4j.quiz_monolithic.exam.ExamRepository;
import uz.maniac4j.quiz_monolithic.payload.Payload;
import uz.maniac4j.quiz_monolithic.payload.Response;
import uz.maniac4j.quiz_monolithic.user.User;
import uz.maniac4j.quiz_monolithic.user.UserRepository;

import java.util.Optional;

@Service
public class AttemptService {
    private final AttemptRepository attemptRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;


    public AttemptService(AttemptRepository attemptRepository, ExamRepository examRepository, UserRepository userRepository) {
        this.attemptRepository = attemptRepository;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    private Response<?> add(AttemptDto dto, User user){
        try {
            Optional<Exam> exam = examRepository.findById(dto.getExam().getId());
            if (exam.isEmpty()) return Payload.badRequest();
            if (!exam.get().getBlock().getOrganization_id().equals(user.getOrganization().getId())) return Payload.unauthorized();
            Optional<User> participant = userRepository.findByUsername(dto.getParticipant().getUsername());
            if (participant.isEmpty()) return Payload.badRequest();

            Attempt attempt = Attempt
                    .builder()
                    .exam(exam.get())
                    .participant(participant.get())
                    .endDate(dto.getEndDate())
                    .startDate(dto.getStartDate())
                    .build();
            attempt=attemptRepository.save(attempt);
            return Payload.ok(attempt);
        }catch (Exception e){
            e.printStackTrace();
            return Payload.conflict();
        }
    }
}
