package doubleCheck.practice.repo;

import doubleCheck.practice.model.BasicModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicModelRepository extends JpaRepository<BasicModel, String> {
}
