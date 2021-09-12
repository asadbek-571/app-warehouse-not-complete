package uz.pdp.appwarehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Category;
import uz.pdp.appwarehouse.domen.Measurement;
import uz.pdp.appwarehouse.model.CategoryDto;
import uz.pdp.appwarehouse.model.MeasurementDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.CategoryService;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/api/measurement")
@RequiredArgsConstructor
public class MeasurementController {

    private final MeasurementService service;

    /**
     * Saves the Measurement
     *
     * @return MeasurementDto
     */
    @PostMapping("/save")
    public ResponseEntity<ApiResponse<MeasurementDto>> save(@RequestBody MeasurementDto dto) {
        return service.save(dto);
    }

    /**
     * Updates Measurement
     *
     * @param id
     * @return MeasurementDto
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<MeasurementDto>> update(@PathVariable(value = "id") Long id, @RequestBody MeasurementDto dto) {
        return service.update(id, dto);
    }

    /**
     * Get a single list of the measurement
     *
     * @param id
     * @return Measurement
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<Measurement>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    /**
     * Get a measurement list
     *
     * @return Measurement
     */
    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Measurement>>> getList() {
        return service.getList();
    }

    /**
     * Delete a single measurement list
     *
     * @param id
     * @return MeasurementDto
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<MeasurementDto>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }


}
