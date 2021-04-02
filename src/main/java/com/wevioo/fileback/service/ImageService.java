package com.wevioo.fileback.service;

import com.wevioo.fileback.interfaces.ImageManager;
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
public class ImageService implements ImageManager {

    @Value(value = "${images.path}")
    private String storageDirectoryPath;

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

        try
        {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] getImageWithMediaType(String imageName, String subdir)
            throws IOException {
        Path destination = Paths.get(storageDirectoryPath + "\\" + subdir + "\\" + imageName);

        return IOUtils.toByteArray(destination.toUri());
    }
}
