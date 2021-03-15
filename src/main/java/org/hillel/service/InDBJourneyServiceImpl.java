package org.hillel.service;

import org.hillel.Journey;
import org.hillel.repository.DataConnect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
//@Scope("prototype")
public class InDBJourneyServiceImpl implements JourneyService {
    private DataConnect dataConnect = new DataConnect();
    private List<Journey> storage;



    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
/*
        System.out.println("Call of method find");
        System.out.println("===========================================");
*/
        storage = dataConnect.getBD();
        if (storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>();
        for (Journey item : storage) {
            if (item.getStationFrom().equals(stationFrom) && item.getStationTo().equals(stationTo)
                    && item.getDeparture().equals(dateFrom) && item.getArrival().equals(dateTo)) {
                out.add(item);
            }
        }
        System.out.println("size of collection is " + out.size());
        return Collections.unmodifiableList(out);
    }
}
