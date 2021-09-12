package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Input;
import uz.pdp.appwarehouse.domen.InputProduct;
import uz.pdp.appwarehouse.model.InputDto;
import uz.pdp.appwarehouse.model.InputProductDto;
import uz.pdp.appwarehouse.model.InputResDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface InputService {
    ResponseEntity<ApiResponse<InputResDto>> save(InputDto dto, InputProductDto inputProductDto);

    ResponseEntity<ApiResponse<InputResDto>> update(Long id, InputDto dto, InputProductDto toInputProduct);

    ResponseEntity<ApiResponse<List<InputProduct>>> get(Long id);

    ResponseEntity<ApiResponse<List<Input>>> getList(int size, int page);

    ResponseEntity<ApiResponse<InputDto>> delete(Long id);
}
