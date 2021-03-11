package org.hillel.service;

import org.hillel.Journey;
import org.hillel.dao.DataConnect;

import java.time.LocalDate;
import java.util.Collection;



public class InDBJourneyServiceImpl implements JourneyService{
   private DataConnect dataConnect;





    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }
}
