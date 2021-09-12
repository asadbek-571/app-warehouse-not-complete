package uz.pdp.appwarehouse.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputDto {


    private String date;

    private String factureNumber;

    private Long warehouseId;

    private Long supplierId;

    private Long currencyId;


}
