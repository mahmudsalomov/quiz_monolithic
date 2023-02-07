package uz.maniac4j.quiz_monolithic.attempt;

import lombok.*;
import uz.maniac4j.quiz_monolithic.template.EntityLong;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attempt extends EntityLong {
    
}
