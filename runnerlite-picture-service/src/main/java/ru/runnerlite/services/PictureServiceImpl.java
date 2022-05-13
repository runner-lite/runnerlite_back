
package ru.runnerlite.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.interfaces.PictureServiceInterface;

import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;


@Service
public class PictureServiceImpl implements PictureServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    private static final String STORAGE_PATH ="picture/avatar/" ;

    @Autowired
    public PictureServiceImpl() {
    }


    @Override
    public Optional<byte[]> getAvatarDataById(Long id,String path){
        Optional<String> findFileName =findFile(id,path);
        if(findFileName.isPresent()){
            try {
                return Optional.of(Files.readAllBytes(Path.of(STORAGE_PATH+path+"/"+findFileName.get())));
            }
            catch (IOException ex){
                logger.error("Can't read file for avatar for user id " + id, ex);
                throw new RuntimeException(ex);
            }
        }
        else {
            logger.info("Not find file for user id"+ id);
            return Optional.empty();
        }
    }

    @Override
    public String createAvatar(byte[] file, Long id,String path) throws IOException {
        deleteAvatar(id,path); //удаляем файл если для пользователя он уже есть .
        InputStream is = new ByteArrayInputStream(file);
        String mimeType = URLConnection.guessContentTypeFromStream(is); // получаем тип файла
        String fileName = path+"-"+id+"."+mimeType.split("/")[1]; //формируем название для файла
        try (OutputStream os = Files.newOutputStream(Paths.get(STORAGE_PATH+path+"/", fileName))) {
            os.write(file); //пишем байты

        } catch (IOException ex) {
            logger.error("Can't write file", ex);
            throw new RuntimeException(ex);
        }
        return fileName;
    }

    @Override
    public boolean deleteAvatar(Long id,String path){
        Optional<String> findFileName =findFile(id,path);
        if(findFileName.isPresent()) {
            try {
                Files.delete(Path.of(STORAGE_PATH +path+"/"+ findFileName.get()));
                return true;
            } catch (IOException ex) {
                logger.error("File not find in picture folder ", ex);
                return false;
            }
        }
        else{
            return false;
        }
    }

    private Optional<String> findFile(Long id,String path){
        File file = new File(STORAGE_PATH+path+"/");
        return Arrays.stream(file.list())
                .filter(name -> name.contains(path+"-"+id.toString()+"."))
                .findFirst();
    }
}
