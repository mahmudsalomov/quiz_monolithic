package uz.maniac4j.quiz_monolithic.organization;


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
public class Organization extends EntityLong {
    private String name;
    private String description;
    private Long ownerId;
}
