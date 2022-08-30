package uz.maniac4j.quiz_monolithic.quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.maniac4j.quiz_monolithic.category.CategoryDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;
    private String title;
    private String text;
    private AnswerEnum answer;
    private String a;
    private String b;
    private String c;
    private String d;
    private CategoryDto category;
}
