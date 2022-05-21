package ru.runnerlite.interfaces;

import java.io.IOException;
import java.util.Optional;

public interface PictureServiceInterface {

    Optional<byte[]> getAvatarDataById(Long id,String path);

    String createAvatar(byte[] file, Long id,String path) throws IOException;

    boolean deleteAvatar(Long id,String path) throws IOException;

}