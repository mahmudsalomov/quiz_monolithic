package uz.maniac4j.quiz_monolithic.attempt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.maniac4j.quiz_monolithic.exam.Exam;
import uz.maniac4j.quiz_monolithic.user.User;

import javax.persistence.ManyToOne;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptDto {
    private Long id;
    private Exam exam;

    private User participant;

    private boolean isStart;

    private Timestamp startDate;

    private Timestamp endDate;
}
