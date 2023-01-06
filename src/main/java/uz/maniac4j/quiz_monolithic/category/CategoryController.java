package uz.maniac4j.quiz_monolithic.category;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.maniac4j.quiz_monolithic.organization.OrganizationService;
import uz.maniac4j.quiz_monolithic.security.CurrentUser;
import uz.maniac4j.quiz_monolithic.user.User;

@RestController
@RequestMapping("api/category")
@CrossOrigin
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final OrganizationService organizationService;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository, OrganizationService organizationService) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.organizationService = organizationService;
    }

    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody CategoryDto category, @CurrentUser User user) {
        return categoryService.add(category, user).response();
    }

    @GetMapping("/all")
    public HttpEntity<?> all(@CurrentUser User user) {
        return categoryService.all(user).response();
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@RequestBody CategoryDto category, @CurrentUser User user) {
        return categoryService.edit(category, user).response();
    }
}
