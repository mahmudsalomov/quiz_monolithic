package uz.maniac4j.quiz_monolithic.category;

import org.springframework.stereotype.Service;
import uz.maniac4j.quiz_monolithic.organization.Organization;
import uz.maniac4j.quiz_monolithic.organization.OrganizationRepository;
import uz.maniac4j.quiz_monolithic.payload.Payload;
import uz.maniac4j.quiz_monolithic.payload.Response;
import uz.maniac4j.quiz_monolithic.user.Role;
import uz.maniac4j.quiz_monolithic.user.RoleName;
import uz.maniac4j.quiz_monolithic.user.User;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final OrganizationRepository organizationRepository;

    public CategoryService(CategoryRepository categoryRepository, OrganizationRepository organizationRepository) {
        this.categoryRepository = categoryRepository;
        this.organizationRepository = organizationRepository;
    }


    public Response<?> add(Category category, User user){
        try {
            // If user is participant, adding category is not allowed
            for (Role role : user.getRoles()) {
                if (role.getRoleName().equals(RoleName.PARTICIPANT)) return Payload.badRequest();
            }

            category.setOrganization(user.getOrganization());
            return Payload.ok(categoryRepository.save(category));
        }catch (Exception e){
            e.printStackTrace();
            return Payload.badRequest();
        }
    }

    public Response<?> one(long id, User user){
        try {

            // If user is participant, getting category is not allowed
            for (Role role : user.getRoles()) {
                if (role.getRoleName().equals(RoleName.PARTICIPANT)) return Payload.badRequest();
            }

            Optional<Category> byId = categoryRepository.findByIdAndOrganization(id,user.getOrganization());
            return byId.isPresent()?Payload.ok(byId.get()):Payload.notFound();
        }catch (Exception e){
            e.printStackTrace();
            return Payload.badRequest();
        }

    }


    public Response<?> all(User user){

        for (Role role : user.getRoles()) {
            // If user is admin, response contains all categories
            if (role.getRoleName().equals(RoleName.ADMIN)) return Payload.ok(categoryRepository.findAll());

            // If user is participant, getting category is not allowed
            if (role.getRoleName().equals(RoleName.PARTICIPANT)) return Payload.badRequest();

        }
        return Payload.ok(categoryRepository.findAllByOrganization(user.getOrganization()));
    }







    public Category one(Long id){
        if (id == null) return null;
        return categoryRepository.getById(id);
    }

    public List<Category> all(){
        return categoryRepository.findAll();
    }

    public Category add(Category category){
        if (category.getName()==null) return null;
        return categoryRepository.save(category);
    }

    public Category edit(Category category){
        if (category.getName() == null) return null;
        Optional<Category> update = categoryRepository.findById(category.getId());
        if (update.isPresent()){
            Category categorys = update.get();
            category.editer(category);
            return categoryRepository.save(category);
        }
        return null;
    }


    public Response<?> edit(CategoryDto dto, User user){
//        Optional<Organization> organization = organizationRepository.findById(dto.getId());
        Optional<Category> category = categoryRepository.findById(dto.getId());
        if (category.isPresent()&&category.get().getOrganization().equals(user.getOrganization())) return Payload.ok(edit(category.get()));

        return Payload.badRequest();
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

}
