package tayduong.com.employeebe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link KhachHang}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhachHangDto implements Serializable {
    private UUID id;
    private String maKhachHang;
    private String tenKhachHang;
    private String diaChi;
    private String nhomKhNcc;
    private String maSoThue;
    private String dienThoai;
    private Boolean ngungTheoDoi;
}