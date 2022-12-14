package uz.maniac4j.quiz_monolithic.category;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.maniac4j.quiz_monolithic.organization.Organization;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByIdAndOrganization(Long id, Organization organization);

    List<Category> findAllByOrganization(Organization organization);
}
