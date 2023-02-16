package uz.maniac4j.quiz_monolithic.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.maniac4j.quiz_monolithic.block.Block;
import uz.maniac4j.quiz_monolithic.block.BlockDto;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamDto {
    private String name;

    private String description;

    private BlockDto block;

    private Timestamp startDate;

    private Timestamp endDate;
}
