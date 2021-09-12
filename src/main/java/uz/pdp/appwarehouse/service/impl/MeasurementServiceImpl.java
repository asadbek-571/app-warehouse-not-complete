package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Measurement;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.MeasurementDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.MeasurementRepo;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {
    
    private final MeasurementRepo measurementRepo;

    /**
     * Saves the Measurement
     *
     * @return MeasurementDto
     */
    @Override
    public ResponseEntity<ApiResponse<MeasurementDto>> save(MeasurementDto dto) {
        Boolean byNameAndActiveTrue = measurementRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This measurement already exists"), HttpStatus.NOT_FOUND);

        Measurement measurement = new Measurement();
        MeasurementServiceImpl service = new MeasurementServiceImpl(measurementRepo);
        return service.setMeasurement(measurement, dto);
    }

    /**
     * Updates Measurement
     *
     * @param id
     * @return MeasurementDto
     */
    @Override
    public ResponseEntity<ApiResponse<MeasurementDto>> update(Long id, MeasurementDto dto) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findById(id);
        if (!optionalMeasurement.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Measurement not found"), HttpStatus.NOT_FOUND);

        Boolean byNameAndActiveTrue = measurementRepo.existsByNameAndActiveTrue(dto.getName());
        if (byNameAndActiveTrue)
            return new ResponseEntity<>(new ApiResponse<>("This measurement already exists"), HttpStatus.NOT_FOUND);

        Measurement measurement = optionalMeasurement.get();
        MeasurementServiceImpl service = new MeasurementServiceImpl(measurementRepo);
        return service.setMeasurement(measurement, dto);
    }


    /**
     * Get a single list of the measurement
     *
     * @param id
     * @return Measurement
     */
    @Override
    public ResponseEntity<ApiResponse<Measurement>> get(Long id) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findByActiveTrueAndId(id);
        return optionalMeasurement.map(measurement -> new ResponseEntity<>(new ApiResponse<>(measurement), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new ApiResponse<>("Measurement not found"), HttpStatus.NOT_FOUND));
    }

    /**
     * Get a measurement list
     *
     * @return Measurement List
     */
    @Override
    public ResponseEntity<ApiResponse<List<Measurement>>> getList() {
        return new ResponseEntity<>(new ApiResponse<>(measurementRepo.findAll()),HttpStatus.OK);
    }

    /**
     * Delete a single measurement list
     *
     * @param id
     * @return MeasurementDto
     */
    @Override
    public ResponseEntity<ApiResponse<MeasurementDto>> delete(Long id) {
        Optional<Measurement> optionalMeasurement = measurementRepo.findByActiveTrueAndId(id);
        if (!optionalMeasurement.isPresent())
            return new ResponseEntity<>(new ApiResponse<>("Measurement not found"), HttpStatus.NOT_FOUND);
        Measurement measurement = optionalMeasurement.get();
        MeasurementDto dto=new MeasurementDto();
        dto.setActive(measurement.isActive());
        dto.setName(measurement.getName());
        measurement.setActive(false);
        measurementRepo.save(measurement);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

    private ResponseEntity<ApiResponse<MeasurementDto>> setMeasurement(Measurement measurement, MeasurementDto dto) {
        measurement.setActive(dto.isActive());
        measurement.setName(dto.getName());
        measurementRepo.save(measurement);
        return new ResponseEntity<>(new ApiResponse<>(dto), HttpStatus.OK);
    }

}
