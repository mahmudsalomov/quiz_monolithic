package uz.maniac4j.quiz_monolithic.block;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/block")
@CrossOrigin
public class BlockController {

    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }


    @PostMapping("/add")
    public HttpEntity<?> add(BlockDto dto){
        return blockService.add(dto).response();
    }

    

}
