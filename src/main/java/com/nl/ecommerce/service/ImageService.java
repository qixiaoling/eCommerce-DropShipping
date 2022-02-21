package com.nl.ecommerce.service;

import com.nl.ecommerce.model.Image;
import com.nl.ecommerce.model.Product;
import com.nl.ecommerce.repository.ImageRepository;
import com.nl.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;



//    public Optional<Image> getImage(Long id){
//        return imageRepository.findById(id);
//    }
//
//    public List<Image> getImages(){
//        return imageRepository.findAll();
//    }


    @Autowired
    ProductRepository productRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<?> addImageToProduct(Long productId, MultipartFile file) throws IOException {
        try{
            Optional<Product> possibleProduct = productRepository.findById(productId);
            if (possibleProduct.isPresent()){
                String fileName = file.getOriginalFilename();
                Image image = new Image(fileName, file.getContentType(), file.getBytes());
                image.setProduct(possibleProduct.get());//one side gives up all the mapping, therefore ask many side to set().
                imageRepository.save(image);
                return ResponseEntity.ok().body(image);
            }else {
                return ResponseEntity.badRequest().body("The product is unavailable.");
            }
        }catch (IOException e){
            return ResponseEntity.badRequest().body("The file does not exist.");
        }
//    public Image saveImages(MultipartFile file) {
//        String imageName = file.getOriginalFilename();
//        try{
//            Image image = new Image(imageName, file.getContentType(), file.getBytes());
//            return imageRepository.save(image);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    }
    public ResponseEntity<?> getImageById (Long id){
        Optional<Image> possibleImage = imageRepository.findById(id);
        if(possibleImage.isPresent()){
            return ResponseEntity.ok().body(possibleImage.get());
        }
        return null;
    }
    public ResponseEntity<?> getImageByProductId(Long productId){
        Product productDB = productRepository.findByProductId(productId);
        try{
            List<Image> images = productDB.getImages();
            return ResponseEntity.ok().body(images);
        }catch(NullPointerException e){
            return ResponseEntity.badRequest().body("There is no images for this product.");
        }
    }



}
