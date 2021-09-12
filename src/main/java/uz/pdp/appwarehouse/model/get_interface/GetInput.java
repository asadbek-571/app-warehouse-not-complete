package uz.pdp.appwarehouse.model.get_interface;


public interface GetInput {

    Long getId();

//    String getDate();
//
//    String getFactureNumber();
//
//    Long getWarehouseId();
//
//    Long getSupplierId();
//
//    Long getCurrencyId();

    Double getAmount();

    Double getPrice();

    Long getProductId();

    Long getInputId();

    String getExpireDate();
//
//    String getCode();
}
