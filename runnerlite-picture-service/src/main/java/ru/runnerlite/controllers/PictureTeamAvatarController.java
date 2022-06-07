package ru.runnerlite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.runnerlite.interfaces.PictureServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/team-picture")
public class PictureTeamAvatarController {

    private final PictureServiceInterface pictureServiceInterface;

    @Autowired
    public PictureTeamAvatarController(PictureServiceInterface pictureServiceInterface) {
        this.pictureServiceInterface = pictureServiceInterface;
    }

    @GetMapping("/{teamId}")
    private void downloadAvatarForUser(@PathVariable("teamId") Long userId, HttpServletResponse response) throws IOException {
        Optional<byte[]> out = pictureServiceInterface.getAvatarDataById(userId,"team");
        if(out.isPresent()){
            response.setContentType(pictureServiceInterface.getMimeType(userId,"team"));
            response.getOutputStream().write(out.get());
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping("/{teamId}")
    private void deleteUserAvatar(@PathVariable("teamId") Long userId,HttpServletResponse response) throws IOException {
        if(pictureServiceInterface.deleteAvatar(userId,"team")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }else {response.setStatus(HttpServletResponse.SC_BAD_REQUEST);}
    }

    @PostMapping("/{teamId}")
    private void addAvatarToUser(@PathVariable("teamId") Long userId, HttpServletResponse response, byte[] picture) throws IOException {
        pictureServiceInterface.createAvatar(picture,userId,"team");
    }

}
