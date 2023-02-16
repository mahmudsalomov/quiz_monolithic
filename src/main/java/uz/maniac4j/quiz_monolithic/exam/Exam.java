package uz.maniac4j.quiz_monolithic.exam;

import lombok.*;
import uz.maniac4j.quiz_monolithic.block.Block;
import uz.maniac4j.quiz_monolithic.template.EntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Exam extends EntityLong {

    private String name;

    @Column(columnDefinition = "text")
    private String description;
    @ManyToOne
    private Block block;

    private boolean isStart;

    private Timestamp startDate;

    private Timestamp endDAte;

}
