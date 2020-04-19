package br.com.oversight.ambienta.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {

   @Autowired
   private Cloudinary cloudinaryConfig;

   public Map uploadFile(MultipartFile file) {
      try {
         File uploadedFile = convertMultiPartToFile(file);
         return cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   private File convertMultiPartToFile(MultipartFile file) throws IOException {
      File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
      FileOutputStream fos = new FileOutputStream(convFile);
      fos.write(file.getBytes());
      fos.close();
      return convFile;
   }
}
