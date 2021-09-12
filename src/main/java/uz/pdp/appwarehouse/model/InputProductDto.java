package uz.pdp.appwarehouse.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputProductDto {


    private Double amount;

    private Double price;

    private Long productId;

//    private Long inputId;

    private String expireDate;

}
