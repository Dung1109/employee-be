package tayduong.com.employeebe.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ma_khach_hang", length = Integer.MAX_VALUE)
    private String maKhachHang;

    @Column(name = "ten_khach_hang", length = Integer.MAX_VALUE)
    private String tenKhachHang;

    @Column(name = "dia_chi", length = Integer.MAX_VALUE)
    private String diaChi;

    @Column(name = "nhom_kh_ncc", length = Integer.MAX_VALUE)
    private String nhomKhNcc;

    @Column(name = "ma_so_thue", length = Integer.MAX_VALUE)
    private String maSoThue;

    @Column(name = "dien_thoai", length = Integer.MAX_VALUE)
    private String dienThoai;

    @Column(name = "ngung_theo_doi")
    private Boolean ngungTheoDoi;

}