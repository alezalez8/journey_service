package org.hillel.service;

import org.hillel.Journey;
import org.hillel.dao.DataConnect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class InDBJourneyServiceImpl implements JourneyService {
    private DataConnect dataConnect = new DataConnect();
    private List<Journey> storage;


    public static void main(String[] args) {
        InDBJourneyServiceImpl service = new InDBJourneyServiceImpl();
        System.out.println(service.find("Lviv", "Kiev", LocalDate.now(), LocalDate.now().plusDays(0)));
        // System.out.println(service.find("tutui", "tyiutui", LocalDate.now(), LocalDate.now().plusDays(1)));

    }

    private void testFind(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        find(stationFrom, stationTo, dateFrom, dateTo);
    }


    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        storage = dataConnect.getBD();
        if (storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>();
        for (Journey item : storage) {
            if (item.getStationFrom().equals(stationFrom) && item.getStationTo().equals(stationTo)
                    && item.getDeparture().equals(dateFrom) && item.getArrival().equals(dateTo)) {
                out.add(item);
            }
        }
        return Collections.unmodifiableList(out);
    }
}
