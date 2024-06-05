package com.duodev.duodevbackend.service;

import com.duodev.duodevbackend.enums.Status;
import com.duodev.duodevbackend.exceptions.ResourceNotFoundException;
import com.duodev.duodevbackend.model.Sessao;
import com.duodev.duodevbackend.repository.SessaoRepository;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "DuoDev";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = SessaoService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        return credential;
    }

    public static Calendar getCalendarService() throws IOException {
        final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();
        Credential credential = getCredentials(HTTP_TRANSPORT);

        return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

    }

    public String createSessao(Sessao novaSessao, String emailMentorado, String emailMentor) throws IOException {
        String retorno = "";
        if (novaSessao.getDataHoraInicial().isAfter(novaSessao.getDataHoraFinal())) {
            retorno = "A data de inicío não pode ser posterior que a data final.";
            return retorno;
        } else if (novaSessao.getDataHoraInicial().isBefore(java.time.LocalDateTime.now())) {
            retorno = "A data de inicío não pode ser anterior que a data atual.";
            return retorno;
        } else {
            Calendar service = getCalendarService();
            com.google.api.services.calendar.model.Event event = new com.google.api.services.calendar.model.Event()
                    .setSummary("DuoDev Mentoria")
                    .setLocation("Google Meet")
                    .setDescription("Encontro criado por DuoDev para a mentoria");


            // formato da data 2024-06-03T09:00:00-03:00
            String dataHoraInicial = novaSessao.getDataHoraInicial().toString() + ":00-03:00";

            DateTime startDateTime = new DateTime(dataHoraInicial);
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("America/Sao_Paulo");
            event.setStart(start);

            // formato da data 2024-06-03T09:00:00-03:00
            String dataHoraFinal = novaSessao.getDataHoraFinal().toString() + ":00-03:00";
            DateTime endDateTime = new DateTime(dataHoraFinal);
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("America/Sao_Paulo");
            event.setEnd(end);

            EventAttendee[] attendees = new EventAttendee[]{
                    new EventAttendee().setEmail(emailMentor),
                    new EventAttendee().setEmail(emailMentorado),
            };

            event.setAttendees(Arrays.asList(attendees));
            String calendarId = "primary";
            // cria a reunião no meet

            ConferenceSolutionKey conferenceSolutionKey = new ConferenceSolutionKey();
            conferenceSolutionKey.setType("hangoutsMeet");
            CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest();
            createConferenceRequest.setRequestId("sample-request-id");

            ConferenceData conferenceData = new ConferenceData();
            conferenceData.setCreateRequest(createConferenceRequest);
            event.setConferenceData(conferenceData);
            Event createdEvent = service.events().insert(calendarId, event).setConferenceDataVersion(1).execute();


            novaSessao.setStatus(Status.AGENDADO);
            novaSessao.setLinkMeet(createdEvent.getHangoutLink());
            novaSessao.setEventGoogleCalendarId(createdEvent.getId());
            novaSessao.setCalendarId(createdEvent.getId());
            sessaoRepository.save(novaSessao);


            retorno = createdEvent.getHtmlLink() + " " + createdEvent.getHangoutLink() + " " + createdEvent.getId()
            + " " + createdEvent.getConferenceData().getConferenceId() + " " + createdEvent.getConferenceData()
                    .getEntryPoints().get(0).getEntryPointType();
            return retorno;
        }

    }


    public List<Sessao> getAllSessoes() throws IOException {
        return sessaoRepository.findAll();
    }


    // tem que ver se deve estourar uma exceção mesmo
    public Sessao getSessaoById(int id) {
        return sessaoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sessão não encontrada"));
    }

    public String updateSessao(int idSessao, Sessao sessaoAtualizada) throws IOException {

        Sessao sessaoAtual = getSessaoById(idSessao);
        String eventId = sessaoAtual.getEventGoogleCalendarId();

        //transform object in string
        System.out.println("SESSÃO" + sessaoAtualizada);

        String retorno = "";
        // Verifica se a sessão tem um ID de evento válido
        if (eventId == null || eventId.isEmpty()) {
            retorno = "ID do evento inválido.";
            return retorno;
        }

        // Verifica se a sessão existe
        if (!sessaoRepository.existsById(sessaoAtual.getId())) {
            retorno = "Sessão não encontrada.";
            return retorno;
        } else if (sessaoAtual.getDataHoraInicial().isAfter(sessaoAtual.getDataHoraFinal())) {
            retorno = "A data de inicío não pode ser posterior que a data final.";
            return retorno;
        } else if (sessaoAtual.getDataHoraInicial().isBefore(java.time.LocalDateTime.now())) {
            retorno = "A data de inicío não pode ser anterior que a data atual.";
            return retorno;
        } else {
            sessaoAtual.setDataHoraInicial(sessaoAtualizada.getDataHoraInicial());
            sessaoAtual.setDataHoraFinal(sessaoAtualizada.getDataHoraFinal());
            sessaoAtual.setMentoria(sessaoAtualizada.getMentoria());
            sessaoAtual.setStatus(sessaoAtualizada.getStatus());
            sessaoAtual.setMentoria(sessaoAtualizada.getMentoria());


            Calendar service = getCalendarService();
            // Recupera o evento existente
            Event event = service.events().get("primary", eventId).execute();

            // Atualiza os detalhes do evento conforme necessário
            event.setSummary("DuoDev Mentoria Atualizada")
                    .setDescription("Descrição atualizada do encontro");

            // Atualiza a data e hora de início e fim, se necessário
            String dataHoraInicialCalendar = sessaoAtualizada.getDataHoraInicial().toString() + ":00-03:00";
            DateTime startDateTime = new DateTime(dataHoraInicialCalendar);
            EventDateTime start = new EventDateTime()
                    .setDateTime(startDateTime)
                    .setTimeZone("America/Sao_Paulo");
            event.setStart(start);

            String dataHoraFinalCalendar = sessaoAtualizada.getDataHoraFinal().toString() + ":00-03:00";
            DateTime endDateTime = new DateTime(dataHoraFinalCalendar);
            EventDateTime end = new EventDateTime()
                    .setDateTime(endDateTime)
                    .setTimeZone("America/Sao_Paulo");
            event.setEnd(end);


            // Envia a atualização para o Google Calendar
            Event updatedEvent = service.events().update("primary", eventId, event).setConferenceDataVersion(1)
                    .execute();


            // Atualiza a sessão com os novos detalhes
            sessaoAtualizada.setDataHoraFinal(LocalDateTime.parse(sessaoAtualizada.getDataHoraFinal().toString()));
            sessaoAtualizada.setDataHoraInicial(LocalDateTime.parse(sessaoAtualizada.getDataHoraInicial().toString()));
            sessaoAtualizada.setEventGoogleCalendarId(sessaoAtual.getEventGoogleCalendarId());
            sessaoAtualizada.setLinkMeet(sessaoAtual.getLinkMeet());


            sessaoRepository.save(sessaoAtualizada);
            retorno = updatedEvent.getHtmlLink() + " " + updatedEvent.getHangoutLink() + " " + updatedEvent.getId();

        }

        return retorno;
    }


    public void deleteSessao(Integer id) {
        Sessao sessao = getSessaoById(id);

        try {
            Calendar service = getCalendarService();
            service.events().delete("primary", sessao.getEventGoogleCalendarId()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sessao.setStatus(Status.CANCELADO);
        sessaoRepository.save(sessao);

    }
}



//    public Sessao updateSessao(int id, Sessao sessaoDetails) throws IOException {
//
//        Calendar service = getCalendarService();
//        com.google.api.services.calendar.model.Calendar calendar =
//                service.calendars().get("primary").execute();
//
//
//
//        // formato da data 2024-06-03T09:00:00-03:00
//        String dataHoraInicial = sessaoDetails.getDataHoraInicial().toString() + ":00-03:00";
//
//
//
//        com.google.api.services.calendar.model.Calendar updatedCalendar =
//                service.calendars().update(calendar.getId(), calendar).execute();
//
//
//
//
//
//
//
//        Sessao sessao = getSessaoById(id);
//        sessao.setDataHoraInicial(sessaoDetails.getDataHoraInicial());
//        sessao.setDataHoraFinal(sessaoDetails.getDataHoraFinal());
//        sessao.setStatus(sessaoDetails.getStatus());
//        sessao.setMentoria(sessaoDetails.getMentoria());
//
//        return sessaoRepository.save(sessao);
//    }
//
