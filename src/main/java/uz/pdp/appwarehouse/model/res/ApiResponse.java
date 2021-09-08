package uz.pdp.appwarehouse.model.res;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T>{

    T date;
    String errorMessage;
    boolean success;

    public ApiResponse(T date) {
        this.date = date;
        this.errorMessage="";
        this.success=true;
    }

    public ApiResponse(String errorMessage) {
        this.errorMessage = errorMessage;
        this.success=false;
        this.date=null;
    }
}
