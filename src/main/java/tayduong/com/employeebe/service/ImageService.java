package tayduong.com.employeebe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tayduong.com.employeebe.entities.Image;
import tayduong.com.employeebe.repo.ImageRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private S3Service s3Service;

    public Image uploadImage(MultipartFile file, String userId) {
        String fileUrl = s3Service.uploadFile(file);

        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setFileUrl(fileUrl);
        image.setUserId(userId);
        image.setUploadDate(LocalDateTime.now());

        return imageRepository.save(image);
    }

    public List<Image> getUserImages(String userId) {
        return imageRepository.findByUserId(userId);
    }
}