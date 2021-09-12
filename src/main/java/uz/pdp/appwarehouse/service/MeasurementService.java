package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.appwarehouse.domen.Measurement;
import uz.pdp.appwarehouse.model.MeasurementDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.util.List;

public interface MeasurementService {
    ResponseEntity<ApiResponse<List<Measurement>>> getList();

    ResponseEntity<ApiResponse<MeasurementDto>> delete(Long id);

    ResponseEntity<ApiResponse<MeasurementDto>> update(Long id, MeasurementDto dto);

    ResponseEntity<ApiResponse<Measurement>> get(Long id);

    ResponseEntity<ApiResponse<MeasurementDto>> save(MeasurementDto dto);
}
