package com.nl.ecommerce.controller;

import com.nl.ecommerce.repository.ImageRepository;
import com.nl.ecommerce.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/uploading")
    public String saveImages(@RequestParam("image") MultipartFile[] images){
        for (MultipartFile image: images){
            imageService.saveImages(image);
        }
        return "redirect:/";
    }

//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
//    @PostMapping("/adding/{productId}")
//    public ResponseEntity<?> addImageToProduct(@PathVariable("productId") Long productId,
//                                               @RequestParam("img") MultipartFile file) throws IOException {
//        return imageService.addImageToProduct(productId, file);
//    }
//    @GetMapping("/getting/{id}")
//    public ResponseEntity<?> getImageById(@PathVariable("id") Long id){
//        return imageService.getImageById(id);
//    }
//
//    @GetMapping("/gettingbyproductid/{productId}")
//    public ResponseEntity<?> getImageByProductId(@PathVariable("productId") Long productId){
//        return imageService.getImageByProductId(productId);
//    }






}
