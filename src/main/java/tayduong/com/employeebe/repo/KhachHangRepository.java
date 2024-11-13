package tayduong.com.employeebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tayduong.com.employeebe.entities.KhachHang;

import java.util.UUID;

public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
}