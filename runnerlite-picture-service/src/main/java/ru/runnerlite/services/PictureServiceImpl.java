package ru.runnerlite.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.runnerlite.interfaces.PictureServiceInterface;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;


@Service
public class PictureServiceImpl implements PictureServiceInterface {

    @Value("${file_storage}")
    private  String storagePath;

    private static final Logger logger = LoggerFactory.getLogger(PictureServiceImpl.class);

    @PostConstruct
    private void createFolders(){
        File folderTeam = new File(storagePath+"/team");
        File folderUser = new File(storagePath+"/user");
        if(folderTeam.mkdir()) logger.info("Create folder for user pictures");
        if(folderUser.mkdir()) logger.info("Create folder for team pictures");
    }

    @Override
    public String getMimeType(Long id,String path) throws IOException {
        Optional<String> findFileName =findFile(id,path);
        return findFileName.isPresent() ? Files.probeContentType(Path.of(storagePath +path+"/"+findFileName.get())) : "image/x-png";
    }


    @Override
    public Optional<byte[]> getAvatarDataById(Long id,String path) throws IOException {
        Optional<String> findFileName = findFile(id, path);
        String result = findFileName.isPresent() ? storagePath +path+"/"+findFileName.get() : storagePath + "/no-image.png";
        return Optional.of(Files.readAllBytes(Path.of(result)));
    }

    @Override
    public String createAvatar(byte[] file, Long id,String path) throws IOException {
        deleteAvatar(id,path); //удаляем файл если для пользователя он уже есть .
        InputStream is = new ByteArrayInputStream(file);
        String mimeType = URLConnection.guessContentTypeFromStream(is); // получаем тип файла
        String fileName = path+"-"+id+"."+mimeType.split("/")[1]; //формируем название для файла
        try (OutputStream os = Files.newOutputStream(Paths.get(storagePath +path+"/", fileName))) {
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
                Files.delete(Path.of(storagePath +path+"/"+ findFileName.get()));
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
        File file = new File(storagePath +path+"/");
        String ff = path+"-"+id.toString();
        return Arrays.stream(file.list())
                .filter(name -> name.contains(path+"-"+id.toString()+"."))
                .findFirst();
    }





}
