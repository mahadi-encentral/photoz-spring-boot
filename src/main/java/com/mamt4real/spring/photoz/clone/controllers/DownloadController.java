package com.mamt4real.spring.photoz.clone.controllers;

import com.mamt4real.spring.photoz.clone.models.Photo;
import com.mamt4real.spring.photoz.clone.services.PhotozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class DownloadController {

    private final PhotozService photozService;

    @Autowired
    public DownloadController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Integer id){
        Optional<Photo> photo = photozService.getPhoto(id);
        if (photo.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        byte[] data = photo.get().getData();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.get().getContentType()));
        headers.setContentDisposition(ContentDisposition
                .builder("inline") //displays it inline
//              .builder("attachment")
                .filename(photo.get().getFilename()).build());
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
