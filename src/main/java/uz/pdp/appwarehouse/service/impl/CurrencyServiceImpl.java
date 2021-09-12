package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domen.Currency;
import uz.pdp.appwarehouse.domen.Measurement;
import uz.pdp.appwarehouse.model.CurrencyDto;
import uz.pdp.appwarehouse.model.MeasurementDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.CurrencyRepo;
import uz.pdp.appwarehouse.repository.MeasurementRepo;
import uz.pdp.appwarehouse.service.CurrencyService;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepo currencyRepo;

    /**
     * Saves the Currency
     *
     * @return CurrencyDto
     */
    @Override
    public ResponseEntity<ApiResponse<CurrencyDto>> save(CurrencyDto dto) {
        Boolean byNameAndActiveTrue = currencyRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This currency already exists"), HttpStatus.NOT_FOUND);

        Currency currency = new Currency();
        CurrencyServiceImpl service = new CurrencyServiceImpl(currencyRepo);
        return service.setMeasurement(currency, dto);
    }

    /**
     * Updates Currency
     *
     * @param id
     * @return CurrencyDto
     */
    @Override
    public ResponseEntity<ApiResponse<CurrencyDto>> update(Long id, CurrencyDto dto) {
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (!optionalCurrency.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Currency not found"), HttpStatus.NOT_FOUND);

        Boolean byNameAndActiveTrue = currencyRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This currency already exists"), HttpStatus.NOT_FOUND);

        Currency currency = optionalCurrency.get();
        CurrencyServiceImpl service = new CurrencyServiceImpl(currencyRepo);
        return service.setMeasurement(currency, dto);
    }


    /**
     * Get a single list of the currency
     *
     * @param id
     * @return Currency
     */
    @Override
    public ResponseEntity<ApiResponse<Currency>> get(Long id) {
        Optional<Currency> optionalCurrency = currencyRepo.findByActiveTrueAndId(id);
        return optionalCurrency.map(currency -> new ResponseEntity<>(new ApiResponse<>(currency), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Currency not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Get a currency list
     *
     * @return Currency List
     */
    @Override
    public ResponseEntity<ApiResponse<List<Currency>>> getList() {
        List<Currency> currencyList = currencyRepo.findAll();
        return new ResponseEntity<>(new ApiResponse<>(currencyList), HttpStatus.OK);
    }

    /**
     * Delete a single currency list
     *
     * @param id
     * @return CurrencyDto
     */
    @Override
    public ResponseEntity<ApiResponse<CurrencyDto>> delete(Long id) {
        Optional<Currency> optionalCurrency = currencyRepo.findByActiveTrueAndId(id);
        if (!optionalCurrency.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Currency not found"), HttpStatus.NOT_FOUND);
        Currency currency = optionalCurrency.get();
        CurrencyDto dto = new CurrencyDto();
        dto.setActive(currency.isActive());
        dto.setName(currency.getName());
        currency.setActive(false);
        currencyRepo.save(currency);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    private ResponseEntity<ApiResponse<CurrencyDto>> setMeasurement(Currency currency, CurrencyDto dto) {
        currency.setActive(dto.isActive());
        currency.setName(dto.getName());
        currencyRepo.save(currency);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

}
