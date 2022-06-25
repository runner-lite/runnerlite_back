package ru.runnerlite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.runnerlite.EmailSender;
import ru.runnerlite.entities.SecUser;
import ru.runnerlite.entities.TeamsRunningCount;
import ru.runnerlite.entities.Volunteer;
import ru.runnerlite.entities.dto.RunningResultForEmailSendDto;
import ru.runnerlite.model.Letter;
import ru.runnerlite.repositories.RunningResultRepository;
import ru.runnerlite.repositories.SecUserRepository;
import ru.runnerlite.repositories.TeamsManagementRepository;
import ru.runnerlite.repositories.VolunteerRepository;
import ru.runnerlite.services.interfaces.ILetterService;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoField;
import java.util.*;

@Service
public class LetterService implements ILetterService {

    private SecUserRepository secUserRepository;

    private TeamsManagementRepository teamsManagementRepository;

    private VolunteerRepository volunteerRepository;

    private RunningResultRepository runningResultRepository;

    @Autowired
    public LetterService(SecUserRepository secUserRepository,
                         TeamsManagementRepository teamsManagementRepository,
                         VolunteerRepository volunteerRepository,
                         RunningResultRepository runningResultRepository) {
        this.secUserRepository = secUserRepository;
        this.teamsManagementRepository = teamsManagementRepository;
        this.volunteerRepository=volunteerRepository;
        this.runningResultRepository=runningResultRepository;
    }

    @Override
    public void sendVolunteerRequestLetter(Volunteer volunteer, TeamsRunningCount teamsRunningCount) {
        EmailSender emailSender = new EmailSender();
        List<SecUser> secUser = teamsManagementRepository.findUserByTeamId(teamsRunningCount.getTeams().getId()).get();
        String topic = "Новая заявка на волонтерство. Номер забега "+teamsRunningCount.getNumber()+".";
        for (SecUser user : secUser) {
            StringBuilder sb = new StringBuilder();
            sb.append(user.getFullName()+" Вам на рассмотрение поступила заявка на волонтерство, по забегу № " + teamsRunningCount.getNumber() + " запланированому " + formatData(teamsRunningCount.getRunningDate().toString()) + ".\n");
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
            sb.append(user.getFullName()+" Ваша заявка на позицию "+position+" по забегу № " +runNumber+ " рассмотрена. Результат рассмотрения - Заявка "+status+".\n");
            sb.append("Дата забега - "+formatData(volunteer.get().getTeamsRunningCount().getRunningDate().toString())+".\n");
            sb.append("Спасибо что Вы с нами!");
            emailSender.sendEmail(new Letter(user.getEmail(), topic,sb.toString()));
        }
    }

    @Override
    public void sendRunningResultsToRunner(Integer runningNumber, Integer teamId) {
        EmailSender emailSender = new EmailSender();
        List<RunningResultForEmailSendDto> runningResultList =  runningResultRepository.findAllResultByTeamRunningId(runningNumber,teamId);
        if(!runningResultList.isEmpty()){
            String topic = "Результаты забега "+runningResultList.get(0).getRunningNumber()+". Команда "+ runningResultList.get(0).getTeamName()+".";
            Integer runnerCount = runningResultList.size();
            for (RunningResultForEmailSendDto runningResultForEmailSendDto : runningResultList) {
                StringBuilder sb=new StringBuilder();
                sb.append("Здравствуйте "+runningResultForEmailSendDto.getRunnerName()+" !\n");
                sb.append("Вы участвовали в забеге "+ runningResultForEmailSendDto.getRunningNumber() +" команды "+runningResultForEmailSendDto.getTeamName()+" .\n");
                sb.append("Дата проведения забега - "+ formatData(runningResultForEmailSendDto.getRunningDate().toString())+" .\n");
                sb.append("Количество участников забега - "+runnerCount+" .\n");
                sb.append("Ваш результат - " + calculateInMinute(runningResultForEmailSendDto.getResultTime())+".\n");
                sb.append("Ваше место в турнирной таблице забега - "+ runningResultForEmailSendDto.getPosition()+" .\n");
                sb.append("Отличный результат! Ждем Вас на новых зебегах!");
                emailSender.sendEmail(new Letter(runningResultForEmailSendDto.getEmail(),topic,sb.toString()));
            }
        }
    }

    //перевод секунд в минуты и часы
    public String calculateInMinute(Integer seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, seconds);
        return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
    }

    //перевод даты в нужный формат
    public static String formatData(String date) {
        date = date.replace("T", " ");
        date = date.replace("Z", "");
        System.out.println(date);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date newDate = null;
        try {
            newDate = newDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        String result = newFormat.format(newDate);
        return result;
    }
}
