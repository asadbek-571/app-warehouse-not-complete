package uz.pdp.appwarehouse.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.domen.Attachment;
import uz.pdp.appwarehouse.model.AttachmentDto;
import uz.pdp.appwarehouse.model.res.ApiResponse;
import uz.pdp.appwarehouse.repository.AttachmentRepo;
import uz.pdp.appwarehouse.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {


    private final AttachmentRepo attachmentRepo;
    private static final String uploadDirectory = "files";

    @Override
    public ResponseEntity<ApiResponse<String>> uploadFiles(MultipartHttpServletRequest request) throws IOException {
        int counter = 0;
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                String originalName = file.getOriginalFilename();

                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setOriginalName(originalName);

                String[] split = new String[0];
                if (originalName != null) {
                    split = originalName.split("\\.");
                }

                String name = UUID.randomUUID() + "." + split[split.length - 1];
                attachment.setName(name);
                attachmentRepo.save(attachment);
                Path path = Paths.get(uploadDirectory + "/" + name);
                Files.copy(file.getInputStream(), path);
                counter++;
            }
        }
        if (counter > 0) return new ResponseEntity<>(new ApiResponse<>("Files saved"), HttpStatus.OK);

        return new ResponseEntity<>(new ApiResponse<>("Files not saved"), HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<List<AttachmentDto>> getList() {

        List<AttachmentDto> attachments = new ArrayList<>();
        for (Attachment item : attachmentRepo.findAll()) {
            AttachmentDto attachment = new AttachmentDto();
            attachment.setId(item.getId());
//            attachment.setSize(item.getSize());
//            attachment.setOriginalName(item.getOriginalName());
//            attachment.setContentType(item.getContentType());
            attachments.add(attachment);
        }
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }


    public void download(Long id, HttpServletResponse response) {

    }
}
