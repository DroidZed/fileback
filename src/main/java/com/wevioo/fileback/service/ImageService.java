package com.wevioo.fileback.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {

    @Value(value = "${images.path}")
    private String storageDirectoryPath;

    /**
     * Uploads the image to file system
     * @param file     the file to upload
     * @param endPoint the api endpoint to return
     * @param subdir   the subdir to where it needs to save the picture
     * @return         api url, you use it so we can download the image
    */
    public ResponseEntity<?> uploadToLocalFileSystem(MultipartFile file, String endPoint, String subdir, String fileName) {

        Path storageDirectory = Paths.get(storageDirectoryPath);

        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + "\\" + subdir + "\\" + fileName);

        try
        {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        // ! Gives you the link to download the image
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(endPoint + "/image/get/")
                .path(fileName)
                .toUriString();

        return ResponseEntity.ok(fileDownloadUri);
    }

    /**
    * Returns the pic data as byte array
     * @param imageName       the image's name
     * @param subdir          the subdirectory of the image, <br>
     *                        used to know if we're saving for user or category
     * @return                byte representation of the image
     * @throws IOException    if it can't find the picture or open the directory !
    */
    public byte[] getImageWithMediaType(String imageName, String subdir)
            throws IOException {
        Path destination = Paths.get(storageDirectoryPath + "\\" + subdir + "\\" + imageName);

        System.out.println("-------------------------");
        System.out.println(destination.toUri());
        System.out.println("-------------------------");

        return IOUtils.toByteArray(destination.toUri());
    }
}
