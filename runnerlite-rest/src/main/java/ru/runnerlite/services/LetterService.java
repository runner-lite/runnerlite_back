package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.EmailSender;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.model.Letter;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamsManagementRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.ILetterService;

import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

@Service
public class LetterService implements ILetterService {

    private SecUserRepository secUserRepository;

    private TeamsManagementRepository teamsManagementRepository;

    private VolunteerRepository volunteerRepository;

    @Autowired
    public LetterService(SecUserRepository secUserRepository,
                         TeamsManagementRepository teamsManagementRepository,
                         VolunteerRepository volunteerRepository) {
        this.secUserRepository = secUserRepository;
        this.teamsManagementRepository = teamsManagementRepository;
        this.volunteerRepository=volunteerRepository;
    }

    @Override
    public void sendVolunteerRequestLetter(Volunteer volunteer, TeamsRunningCount teamsRunningCount) {
        EmailSender emailSender = new EmailSender();
        List<SecUser> secUser = teamsManagementRepository.findUserByTeamId(teamsRunningCount.getTeams().getId()).get();
        String topic = "Новая заявка на волонтерство. Номер забега "+teamsRunningCount.getNumber()+".";
        for (SecUser user : secUser) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getFullName()+" Вам на рассмотрение поступила заявка на волонтерство.\n");
            sb.append("Запрос пришел от "+volunteer.getSecUsers().getFullName()+" на позицию "+volunteer.getRefVolunteersPosition().getName()+" .\n");
            sb.append("Для подтверждения заявки зайдите в личный кабинет .");
            emailSender.sendEmail(new Letter(user.getEmail(), topic,sb.toString()));
        }
    }

    @Override
    public void sendVolunteerAcceptLetter(Integer volunteerId,String status) {
        EmailSender emailSender = new EmailSender();
        Optional<Volunteer> volunteer = volunteerRepository.findVolunteerVolunteersId(volunteerId);
        StringBuilder sb = new StringBuilder();
        if(volunteer.isPresent()){
            String position = volunteer.get().getRefVolunteersPosition().getName();
            Integer runNumber = volunteer.get().getTeamsRunningCount().getNumber();
            SecUser user = volunteer.get().getSecUsers();
            String topic = "Результат рассмотрения заявки на волонтерство. Номер забега "+runNumber+".";
            sb.append(user.getFullName()+" Ваша заявка на позицию "+position+" рассмотрена. Результат рассмотрения - Заявка "+status+".\n");
            sb.append("Дата забега "+volunteer.get().getTeamsRunningCount().getRunningDate().toString()+".\n");
            sb.append("Спасибо что Вы с нами!");
            emailSender.sendEmail(new Letter(user.getEmail(), topic,sb.toString()));
        }
    }
}
