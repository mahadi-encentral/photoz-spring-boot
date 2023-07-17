package com.mamt4real.spring.photoz.clone.services;

import com.mamt4real.spring.photoz.clone.models.Photo;
import com.mamt4real.spring.photoz.clone.repositories.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

//@Component
@Service
public class PhotozService {

    private final PhotoRepository photoRepository;

    public PhotozService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void deletePhoto(Integer id) {
        photoRepository.deleteById(id);
    }

    public Optional<Photo> getPhoto(Integer id) {
        return photoRepository.findById(id);
    }

    public Photo savePhoto(String fileName, String contentType,  byte[] data) {
        Photo photo = new Photo();
        photo.setFilename(fileName);
        photo.setData(data);
        photo.setContentType(contentType);
        return photoRepository.save(photo);
    }

    public List<Photo> getAllPhotoz() {
        return (List<Photo>) photoRepository.findAll();
    }
}
