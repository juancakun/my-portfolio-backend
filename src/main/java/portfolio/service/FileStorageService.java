package portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException{
        if(file.isEmpty()){
            throw new IOException("El Archivo esta vacio");
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if(originalFilename!=null){
            int dotIntex = originalFilename.lastIndexOf('.');
            if(dotIntex>0){
                extension = originalFilename.substring(dotIntex);
            }
        }

        String fileName = UUID.randomUUID().toString() + extension;

        //creación de la ruta

        Path filePath = Paths.get(uploadDir, fileName).normalize();

        //copia del archivo al destino
        Files.copy(file.getInputStream(), filePath);

        //retorno de la url relativa
        return "/img/projects/"+fileName;

    }

}
