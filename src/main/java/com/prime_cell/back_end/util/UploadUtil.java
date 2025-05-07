package com.prime_cell.back_end.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class UploadUtil {
    // Altere esta constante para um caminho absoluto fora de src/main/resources
    private static final String UPLOAD_DIR = "uploads/images/";

    public static String uploadImage(MultipartFile imagem) {
        try {
            // Verifica se a imagem não está vazia
            if (imagem.isEmpty()) {
                throw new IOException("Arquivo de imagem vazio");
            }

            // Gera um nome único para o arquivo
            String originalFilename = imagem.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + extension;

            // Cria o diretório se não existir
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Salva o arquivo
            Path filePath = uploadPath.resolve(newFileName);
            Files.copy(imagem.getInputStream(), filePath);

            return newFileName;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage());
        }
    }
}