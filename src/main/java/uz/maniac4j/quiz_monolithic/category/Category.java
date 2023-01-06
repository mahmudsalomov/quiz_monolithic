package uz.maniac4j.quiz_monolithic.category;

import lombok.*;
import uz.maniac4j.quiz_monolithic.organization.Organization;
import uz.maniac4j.quiz_monolithic.template.EntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
public class Category extends EntityLong {
    private String name;
    @Column(columnDefinition = "text")
    private String description;
//    @NotNull
    @ManyToOne
//    @Column(unique = true)
    private Organization organization;


    @Builder
    public Category(Long id, Timestamp createdAt, boolean deleted, String name, String description, Organization organization) {
        super(id, createdAt, deleted);
        this.name = name;
        this.description = description;
        this.organization = organization;
    }


    public void editer(Category category){
        name = category.name;
        description = category.description;
//        organization_id = category.organization_id;
    }
}
