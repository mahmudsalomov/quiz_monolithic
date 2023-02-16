package uz.maniac4j.quiz_monolithic.attempt;

import lombok.*;
import uz.maniac4j.quiz_monolithic.exam.Exam;
import uz.maniac4j.quiz_monolithic.template.EntityLong;
import uz.maniac4j.quiz_monolithic.user.User;

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
public class Attempt extends EntityLong {

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private User participant;

    private boolean isStart;

    private Timestamp startDate;

    private Timestamp endDate;

//    private int count;

}
