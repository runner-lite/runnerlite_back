package ru.runnerlite.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.runnerlite.entities.RefCity;
import ru.runnerlite.entities.dto.RefCitiesDto;
import ru.runnerlite.services.interfaces.ICityService;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class RefCitiesController {
	
	@Autowired
	ICityService cityService;

	@GetMapping("/getAll")
	public List<RefCitiesDto> getAllCities() {
		return cityService.findAll();
	}


}
