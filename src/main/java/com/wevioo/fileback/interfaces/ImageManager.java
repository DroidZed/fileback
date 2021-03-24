package com.wevioo.fileback.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageManager {
    /**
     * Uploads the image to file system
     *
     * @param file   the file to upload
     * @param subdir the subdir to where it needs to save the picture
     */
    void uploadToLocalFileSystem(MultipartFile file, String subdir, String fileName);

    /**
     * Returns the pic data as byte array
     *
     * @param imageName the image's name
     * @param subdir    the subdirectory of the image, <br>
     *                  used to know if we're saving for user or category
     * @return byte representation of the image
     * @throws IOException if it can't find the picture or open the directory !
     */
    byte[] getImageWithMediaType(String imageName, String subdir) throws IOException;
}
