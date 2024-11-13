package tayduong.com.employeebe.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ma_san_pham", length = Integer.MAX_VALUE)
    private String maSanPham;

    @Column(name = "ten_san_pham", length = Integer.MAX_VALUE)
    private String tenSanPham;

    @Column(name = "don_vi", length = Integer.MAX_VALUE)
    private String donVi;

    @Column(name = "don_gia", precision = 18, scale = 2)
    private BigDecimal donGia;

}