package com.wevioo.fileback.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
     *
     * @param file   the file to upload
     * @param subdir the subdir to where it needs to save the picture
     */
    public void uploadToLocalFileSystem(MultipartFile file, String subdir, String fileName) {

        Path storageDirectory = Paths.get(storageDirectoryPath + "\\" + subdir);

        if (!Files.exists(storageDirectory)) {
            try {
                Files.createDirectories(storageDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + "\\" + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the pic data as byte array
     *
     * @param imageName the image's name
     * @param subdir    the subdirectory of the image, <br>
     *                  used to know if we're saving for user or category
     * @return byte representation of the image
     * @throws IOException if it can't find the picture or open the directory !
     */
    public byte[] getImageWithMediaType(String imageName, String subdir)
            throws IOException {
        Path destination = Paths.get(storageDirectoryPath + "\\" + subdir + "\\" + imageName);

        return IOUtils.toByteArray(destination.toUri());
    }
}
