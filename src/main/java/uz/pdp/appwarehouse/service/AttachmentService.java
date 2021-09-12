package uz.pdp.appwarehouse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.model.AttachmentDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {

     ResponseEntity<List<AttachmentDto>> getList();

     ResponseEntity<ApiResponse<String>> uploadFiles(MultipartHttpServletRequest request) throws IOException;
}
