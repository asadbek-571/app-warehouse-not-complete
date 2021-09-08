package uz.pdp.appwarehouse.common;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import uz.pdp.appwarehouse.domen.Users;
import uz.pdp.appwarehouse.domen.Warehouse;
import uz.pdp.appwarehouse.model.UserDto;
import uz.pdp.appwarehouse.model.WarehouseDto;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface MapstructMapper {

    Warehouse toWarehouse(WarehouseDto dto);

    @Mapping(target ="warehouseId" ,source = "warehouses.id")
    UserDto toUserDto(Users users);
}
