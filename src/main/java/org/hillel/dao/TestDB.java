package org.hillel.dao;

import org.hillel.Journey;

import java.util.ArrayList;
import java.util.List;

public class TestDB {


    public static void main(String[] args) {
        System.out.println("size of array " + printBD().size());
        for (Journey item : printBD()
        ) {
            System.out.println(item.toString());

        }
    }

    static List<Journey> printBD() {
        List<Journey> journeys = new ArrayList<>();
        journeys = new DataConnect().getBD();
        return journeys;
    }


}
