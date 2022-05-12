package ru.runnerlite.interfaces;

import java.io.IOException;
import java.util.Optional;

public interface PictureServiceInterface {

    Optional<byte[]> getAvatarDataById(Long id);

    String createAvatar(byte[] file, Long id) throws IOException;

    boolean deleteAvatar(Long id) throws IOException;

}