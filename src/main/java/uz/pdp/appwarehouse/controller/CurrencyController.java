package uz.pdp.appwarehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.model.CurrencyDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CurrencyService service;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CurrencyDto>> save(@RequestBody CurrencyDto dto){
        return service.save(dto);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CurrencyDto>> update(@PathVariable(value = "id")Long id, @RequestBody CurrencyDto dto){
        return service.update(id,dto);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Currency>> get(@PathVariable(value = "id")Long id){
        return service.get(id);
    }

    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Currency>>> getList(){
        return service.getList();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<CurrencyDto>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
