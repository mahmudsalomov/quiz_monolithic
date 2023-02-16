package uz.maniac4j.quiz_monolithic.exam;

import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.block.Block;
import uz.maniac4j.quiz_monolithic.block.BlockRepository;
import uz.maniac4j.quiz_monolithic.payload.Payload;
import uz.maniac4j.quiz_monolithic.payload.Response;
import uz.maniac4j.quiz_monolithic.user.User;

import java.util.Optional;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final BlockRepository blockRepository;

    public ExamService(ExamRepository examRepository, BlockRepository blockRepository) {
        this.examRepository = examRepository;
        this.blockRepository = blockRepository;
    }


    public Response<?> add(ExamDto dto, User user){
        try {
            Optional<Block> block = blockRepository.findById(dto.getBlock().getId());
            if (block.isEmpty()) return Payload.badRequest();
            if (!block.get().getOrganization_id().equals(user.getOrganization().getId())) return Payload.badRequest();
            Exam exam = Exam
                    .builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .block(block.get())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .build();
            exam=examRepository.save(exam);
            return Payload.ok("Exam saved",exam);
        }catch (Exception e){
            return Payload.conflict();
        }
    }
}
