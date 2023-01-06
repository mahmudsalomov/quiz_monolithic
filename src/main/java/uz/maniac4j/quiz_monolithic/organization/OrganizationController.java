package uz.maniac4j.quiz_monolithic.organization;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.maniac4j.quiz_monolithic.security.CurrentUser;
import uz.maniac4j.quiz_monolithic.user.User;


@RequestMapping("api/organization")
@RestController
@CrossOrigin
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody Organization organization, @CurrentUser User user){
        return organizationService.create(organization,user).response();
    }

    @GetMapping("/test")
    public String test(){
        return "Salom";
    }
}
