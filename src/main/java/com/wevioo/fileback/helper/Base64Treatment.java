package com.wevioo.fileback.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Base64Treatment implements ImageHelper {

    private byte[] base64String;

    public byte[] compressBytes() {
        Deflater deflater = new Deflater();
        deflater.setInput(base64String);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(base64String.length);
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

    public byte[] decompressBytes() {
        Inflater inflater = new Inflater();
        inflater.setInput(base64String);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(base64String.length);
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
