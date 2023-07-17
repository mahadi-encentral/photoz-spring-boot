package com.mamt4real.spring.photoz.clone.controllers;

import com.mamt4real.spring.photoz.clone.models.Photo;
import com.mamt4real.spring.photoz.clone.services.PhotozService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class PhotozController {

    private final PhotozService photozService;

    @Autowired
    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/photoz")
    public List<Photo> getAllPhotoz() {
        return photozService.getAllPhotoz();
    }

    @PostMapping("/photoz")
    public Photo createPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.savePhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

    @GetMapping("/photoz/{id}")
    public Photo getOnePhoto(@PathVariable Integer id) {
        Optional<Photo> photo = photozService.getPhoto(id);
        if (photo.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo.get();
    }

    @DeleteMapping("/photoz/{id}")
    public void deletePhoto(@PathVariable Integer id) {
        photozService.deletePhoto(id);
    }
}
