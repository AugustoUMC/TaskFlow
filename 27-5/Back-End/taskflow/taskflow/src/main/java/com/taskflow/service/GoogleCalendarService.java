package com.taskflow.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

import com.taskflow.model.Task;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleCalendarService {

    public String createEvent(
            Task task,
            OAuth2AuthorizedClient client
    ) {

        try {

            AccessToken token =
                    new AccessToken(
                            client.getAccessToken().getTokenValue(),
                            null
                    );

            GoogleCredentials credentials =
                    GoogleCredentials.create(token)
                            .createScoped(
                                    Collections.singleton(
                                            CalendarScopes.CALENDAR
                                    )
                            );

            Calendar service =
                    new Calendar.Builder(
                            GoogleNetHttpTransport
                                    .newTrustedTransport(),

                            GsonFactory
                                    .getDefaultInstance(),

                            new HttpCredentialsAdapter(
                                    credentials
                            )
                    )
                            .setApplicationName(
                                    "TaskFlow"
                            )
                            .build();

            Event event = new Event();

            event.setSummary(
                    task.getTitle()
            );

            event.setDescription(
                    task.getDescription()
            );

            DateTime startDate =
                    new DateTime(
                            task.getDueDate()
                                    .toString()
                    );

            EventDateTime start =
                    new EventDateTime()
                            .setDate(startDate)
                            .setTimeZone(
                                    "America/Sao_Paulo"
                            );

            EventDateTime end =
                    new EventDateTime()
                            .setDate(
                                    new DateTime(
                                            task.getDueDate()
                                                    .plusDays(1)
                                                    .toString()
                                    )
                            )
                            .setTimeZone(
                                    "America/Sao_Paulo"
                            );

            event.setStart(start);

            event.setEnd(end);

            Event createdEvent =
                    service.events()
                            .insert(
                                    "primary",
                                    event
                            )
                            .execute();

            return createdEvent.getId();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao criar evento no Google Calendar"
            );
        }
    }
}