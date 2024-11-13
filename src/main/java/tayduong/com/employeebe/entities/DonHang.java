package tayduong.com.employeebe.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "don_hang")
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ma_don_hang", length = Integer.MAX_VALUE)
    private String maDonHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang maKhachHang;

    @Column(name = "ngay_dat")
    private LocalDate ngayDat;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @Column(name = "dieu_khoan_tt", length = Integer.MAX_VALUE)
    private String dieuKhoanTt;

    @Column(name = "tong_tien", precision = 18, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "tien_vat", precision = 18, scale = 2)
    private BigDecimal tienVat;

    @Column(name = "tong_thanh_toan", precision = 18, scale = 2)
    private BigDecimal tongThanhToan;

}