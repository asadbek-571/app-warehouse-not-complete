package uz.pdp.appwarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appwarehouse.domen.Warehouse;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String  firstName;

    private String  lastName;

    private String  phoneNumber;

    private String  password;

    private boolean  active;

    private Set<Long> warehouseId;
}
