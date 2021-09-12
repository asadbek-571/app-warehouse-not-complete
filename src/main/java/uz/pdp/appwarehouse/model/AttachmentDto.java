package uz.pdp.appwarehouse.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import uz.pdp.appwarehouse.helpers.Utils;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachmentDto {

    Long id;

    String name;


    public String getName() {
        if (Utils.isEmpty(name))
            return "";

        return "C:\\Users\\Asus\\OneDrive\\Рабочий стол\\spring\\lesson10\\app-credit-shop\\files\\" + name;
    }
}
