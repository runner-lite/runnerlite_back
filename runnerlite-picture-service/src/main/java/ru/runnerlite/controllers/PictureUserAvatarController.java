package ru.runnerlite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.runnerlite.interfaces.PictureServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/user-picture")
public class PictureUserAvatarController {

    private final PictureServiceInterface pictureServiceInterface;

    @Autowired
    public PictureUserAvatarController(PictureServiceInterface pictureServiceInterface) {
        this.pictureServiceInterface = pictureServiceInterface;
    }

    @GetMapping("/{userId}")
    private void downloadAvatarForUser(@PathVariable("userId") Long userId, HttpServletResponse response) throws IOException {
        Optional<byte[]> out = pictureServiceInterface.getAvatarDataById(userId,"user");
        if(out.isPresent()){
            response.setContentType(pictureServiceInterface.getMimeType(userId,"user"));
            response.getOutputStream().write(out.get());
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    private void deleteUserAvatar(@PathVariable("userId") Long userId,HttpServletResponse response) throws IOException {
        if(pictureServiceInterface.deleteAvatar(userId,"user")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else {response.setStatus(HttpServletResponse.SC_BAD_REQUEST);}
    }

    @PostMapping("/{userId}")
    private void addAvatarToUser(@PathVariable("userId") Long userId, HttpServletResponse response, byte[] picture) throws IOException {
        pictureServiceInterface.createAvatar(picture,userId,"user");
    }

}
