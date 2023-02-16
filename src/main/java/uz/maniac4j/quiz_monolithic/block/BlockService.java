package uz.maniac4j.quiz_monolithic.block;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.category.CategoryRepository;
import uz.maniac4j.quiz_monolithic.payload.Payload;
import uz.maniac4j.quiz_monolithic.payload.Response;
import uz.maniac4j.quiz_monolithic.quiz.QuizRepository;
import uz.maniac4j.quiz_monolithic.quiz.QuizDto;
import uz.maniac4j.quiz_monolithic.quiz.Quiz;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BlockService {

    private final BlockRepository blockRepository;
    private final QuizRepository quizRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BlockService(BlockRepository blockRepository, QuizRepository quizRepository, CategoryRepository categoryRepository) {
        this.blockRepository = blockRepository;
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<Block> all(){
        return blockRepository.findAll();
    }

    public Block one(Long id){
        return blockRepository.getById(id);
    }


    public Block add(Block block){
        if (block.getId()== null) return null;
        return blockRepository.save(block);
    }


    public Response<?> add(BlockDto dto){
        Set<Quiz> quizzes=new HashSet<>();
        for (QuizDto quizDto:dto.getQuizList()) {
            Optional<Quiz> quiz = quizRepository.findById(quizDto.getId());
            if (quiz.isEmpty()) return null;
            quizzes.add(quiz.get());
        }
        Block block = Block
                .builder()
                .description(dto.getDescription())
                .name(dto.getName())
                .type(BlockType.CUSTOM)
                .quizzes(quizzes)
                .active(dto.isActive())
                .limit(dto.getLimit())
                .organization_id(dto.getOrganization_id())
                .build();
//        return blocksRepository.save(block);
        return Payload.ok(blockRepository.save(block));
    }







    public void delete(Long id){
        if (id != null) blockRepository.deleteById(id);
    }

    public Block edit(Block block){
        if (block.getId() == null) return null;
        Optional<Block> update = blockRepository.findById(block.getId());
        if (update.isPresent()){
            Block blocks = update.get();
            blocks.edit(block);
            return blockRepository.save(block);
        }
        return null;
    }


    // Check block id and organization id is exists
    public boolean existsByBlockAndOrganization(long block_id, long organization_id){
        Optional<Block> optionalBlock = blockRepository.findById(block_id);
        if (optionalBlock.isEmpty()) return false;
        return optionalBlock.get().getOrganization_id() == organization_id;
    }

}
