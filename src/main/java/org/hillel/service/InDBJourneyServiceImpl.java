package org.hillel.service;

import org.hillel.Journey;
import org.hillel.dao.DataConnect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class InDBJourneyServiceImpl implements JourneyService{
   private DataConnect dataConnect = new DataConnect();
   private List<Journey> storage;


   //for test
    public void  outBD(){
        storage = dataConnect.getBD();
        for (Journey item: storage
             ) {
            System.out.println(item.toString());
        }
    }



    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDate dateFrom, LocalDate dateTo) {
        storage = dataConnect.getBD();
        if(storage == null || storage.isEmpty()) return Collections.emptyList();
        List<Journey> out = new ArrayList<>();
        for(Journey item: storage){
            if(item.getDeparture().equals(dateFrom) && item.getArrival().equals(dateTo)){
                out.add(item);
            }
        }

        return Collections.unmodifiableList(out);
    }
}
