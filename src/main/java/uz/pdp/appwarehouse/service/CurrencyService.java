package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.model.CurrencyDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface CurrencyService {
    ResponseEntity<ApiResponse<CurrencyDto>> save(CurrencyDto dto);

    ResponseEntity<ApiResponse<CurrencyDto>> update(Long id, CurrencyDto dto);

    ResponseEntity<ApiResponse<Currency>> get(Long id);

    ResponseEntity<ApiResponse<List<Currency>>> getList();

    ResponseEntity<ApiResponse<CurrencyDto>> delete(Long id);
}
