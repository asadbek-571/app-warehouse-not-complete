package uz.pdp.appwarehouse.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {


    private String  name;

    private boolean  active;

    private Long categoryId;

    private Long measurementId;


}
