package com.wevioo.fileback.service;

import com.wevioo.fileback.exceptions.UserNotFoundException;
import com.wevioo.fileback.message.ResponseMessage;
import com.wevioo.fileback.model.User;
import com.wevioo.fileback.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@AllArgsConstructor
public class ImageService {

    private final UserRepository userRepository;

    public ResponseEntity<?> updateProfilePicture(MultipartFile img, Long id) throws IOException {

        Optional<User> u = userRepository.findById(id);

        if (u.isEmpty()) {
            return ResponseEntity.badRequest().body(new ResponseMessage("Impossible de modifier l'image !"));
        }

        User u1 = u.get();
        u1.setPic(compressBytes(img.getBytes()));
        userRepository.save(u1);
        return ResponseEntity.ok(new ResponseMessage("Image sauvegardée avec succés !"));
    }

    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    private byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
            ioe.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
