package org.brightworks.friflow.ticketing.controller.attachment;

import org.brightworks.friflow.ticketing.domain.dto.AttachmentDTO;
import org.brightworks.friflow.ticketing.service.attachment.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Objects;


import static org.springframework.http.HttpStatus.OK;

/**
 * Created by kyel on 8/9/2016.
 */

//TODO: uncomment @RestController once service refactoring is done
//@RestController
public class AttachmentResource {

    @Autowired
    private AttachmentService attachmentService;

    /**
     * TODO: Add impl
     * @return
     */
    public ResponseEntity<Long> uploadAttachment() {
        return new ResponseEntity<>(1L,OK);
    }

    @RequestMapping("/attachment/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        AttachmentDTO dto = attachmentService.getAttachmenetMetaData(id);
        MediaType contentType = MediaType.parseMediaType(dto.getContentType());
        headers.setContentType(contentType);

        headers.set("Content-Disposition",
                "attachment; filename=" + dto.getFileName());
        return new ResponseEntity<>(dto.getFile(), headers, OK);
    }

    @RequestMapping("/attachment/remove/{id}")
    public ResponseEntity<Objects> deleteAttachment(@PathVariable("id") Long id) throws IOException {
        attachmentService.deleteAttachment(id);
        return new ResponseEntity<>(OK);
    }
}
