package uz.maniac4j.quiz_monolithic.quiz;

import lombok.*;
import uz.maniac4j.quiz_monolithic.block.Block;
import uz.maniac4j.quiz_monolithic.category.Category;
import uz.maniac4j.quiz_monolithic.template.EntityLong;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Quiz extends EntityLong {

    private String title;
    @Column(columnDefinition = "text")
    private String text;

    @Enumerated(EnumType.STRING)
    private AnswerEnum answer;

    @ManyToOne
    private Category category;

    @ManyToMany
    @ToString.Exclude
    private Set<Block> blocks;


    @Column(columnDefinition = "text")
    private String a;
    @Column(columnDefinition = "text")
    private String b;
    @Column(columnDefinition = "text")
    private String c;
    @Column(columnDefinition = "text")
    private String d;


    @Builder
    public Quiz(Long id, Timestamp createdAt, boolean deleted, String title, String text, AnswerEnum answer, Category category, Set<Block> blocks, String a, String b, String c, String d) {
        super(id, createdAt, deleted);
        this.title = title;
        this.text = text;
        this.answer = answer;
        this.category = category;
        this.blocks = blocks;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }


    public Quiz(String title, String text, AnswerEnum answer, Category category, Set<Block> blocks, String a, String b, String c, String d) {
        this.title = title;
        this.text = text;
        this.answer = answer;
        this.category = category;
        this.blocks = blocks;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void editing(Quiz quiz){
        title = quiz.title;
        text = quiz.text;
        answer = quiz.answer;
        a = quiz.a;
        b = quiz.b;
        c = quiz.c;
        d = quiz.d;
        category = quiz.category;
        blocks = quiz.blocks;

    }

    public void editing(QuizDto quiz){
        title = quiz.getTitle();
        text = quiz.getText();
        answer = quiz.getAnswer();
        a = quiz.getA();
        b = quiz.getB();
        c = quiz.getC();
        d = quiz.getD();
    }
}
