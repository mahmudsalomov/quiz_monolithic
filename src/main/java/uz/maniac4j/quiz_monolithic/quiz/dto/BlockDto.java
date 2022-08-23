package uz.maniac4j.quiz_monolithic.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.maniac4j.quiz_monolithic.quiz.model.BlockType;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockDto {
    private String name;
    private String description;
    private List<QuizDto> quizList=new ArrayList<>();
    private BlockType type;
    private boolean active;
    private long limit;
    private Long organization_id;
}
