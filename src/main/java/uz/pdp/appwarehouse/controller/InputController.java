package uz.pdp.appwarehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.domen.Input;
import uz.pdp.appwarehouse.domen.InputProduct;
import uz.pdp.appwarehouse.model.InputDto;
import uz.pdp.appwarehouse.model.InputProductDto;
import uz.pdp.appwarehouse.model.InputResDto;
import uz.pdp.appwarehouse.model.get_interface.GetInput;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/api/input")
@RequiredArgsConstructor
public class InputController {

    private final InputService service;

    /**
     * Saves the Input
     *
     * @return InputDto
     */
    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse<InputResDto>> save(@RequestParam("inputDto") String inputDto,
                                                         @RequestParam("inputProductDto") String inputProductDto) {
        InputController inputController = new InputController(service);
        InputDto toInputDto = inputController.toInputDto(inputDto);
        InputProductDto toInputProduct = inputController.toInputProduct(inputProductDto);
        return service.save(toInputDto, toInputProduct);
    }

    /**
     * Updates Input
     *
     * @param id
     * @return InputDto
     */
    @PutMapping(value = "/update/{id}/")
    public ResponseEntity<ApiResponse<InputResDto>> update(@PathVariable(value = "id") Long id,
                                                           @RequestParam("inputDto") String inputDto,
                                                           @RequestParam("inputProductDto") String inputProductDto) {
        InputController inputController = new InputController(service);
        InputDto toInputDto = inputController.toInputDto(inputDto);
        InputProductDto toInputProduct = inputController.toInputProduct(inputProductDto);
        return service.update(id, toInputDto, toInputProduct);
    }


    private InputDto toInputDto(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, InputDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputProductDto toInputProduct(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, InputProductDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get a single list of the Input
     *
     * @param id
     * @return Input
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<List<InputProduct>>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    /**
     * Get a Input list
     *
     * @return Input
     */
    @GetMapping("/getList")
    public ResponseEntity<ApiResponse<List<Input>>> getList(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                            @RequestParam(value = "size", defaultValue = "5", required = false) int size) {
        return service.getList(size, page);
    }

    /**
     * Delete a single Input list
     *
     * @param id
     * @return InputDto
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<InputDto>> delete(@PathVariable(value = "id") Long id) {
        return service.delete(id);
    }


}
