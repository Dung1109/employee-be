package tayduong.com.employeebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tayduong.com.employeebe.entities.Image;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

  List<Image> findByUserId(String userId);
}