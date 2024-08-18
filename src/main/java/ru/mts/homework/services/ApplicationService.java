package ru.mts.homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mts.homework.dto.Application;
import ru.mts.homework.util.DateOperations;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class ApplicationService {
    @Autowired
    ApiService apiService;

    public Application makeVacationDecision(Application application) {
        List<Application> applications = apiService.getAllVacations();
        applications.removeIf(e -> e.getId().equals(application.getId()));  //Удаляем из выборки заявку, по которой принимаем решение
        long overlappedVacationsCount = applications.stream()       //Ищем количество заявок на отпуск, у которых временной промежуток хотя бы на 1 день пересекается с промежутком у обрабатываемой заявки
                .filter(s -> (ChronoUnit.DAYS.between(DateOperations.maxLocal(application.getStartDate(), s.getStartDate()), DateOperations.minLocal(application.getEndDate(), s.getEndDate())) >= 0)).count();

        if (overlappedVacationsCount > 0) {
            application.setStatus("declined");
            application.setComment("HR сервис отказал по заявке из-за пересечений дат отпуска с другими коллегами");
        } else {
            application.setStatus("approved");
            application.setComment("Отпуск утверждён");
        }
        return application;
    }
}
