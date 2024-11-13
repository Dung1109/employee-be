package tayduong.com.employeebe.mapper;

import org.mapstruct.*;
import tayduong.com.employeebe.entities.KhachHang;
import tayduong.com.employeebe.entities.KhachHangDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface KhachHangMapper {
    KhachHang toEntity(KhachHangDto khachHangDto);

    KhachHangDto toDto(KhachHang khachHang);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    KhachHang partialUpdate(KhachHangDto khachHangDto, @MappingTarget KhachHang khachHang);
}