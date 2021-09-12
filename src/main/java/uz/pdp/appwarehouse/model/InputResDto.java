package uz.pdp.appwarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.appwarehouse.domen.Input;
import uz.pdp.appwarehouse.domen.InputProduct;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputResDto {


    private InputDto inputDto;

    private InputProductDto inputProductDto;


}
