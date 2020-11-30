package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import java.util.concurrent.TimeUnit;

public class FareCalculatorService {
    private static final double ONE_HOUR_IN_MILLISECONDS = TimeUnit.HOURS.toMillis(1);
    private static final double HALF_AN_HOUR_IN_MILLISECONDS = TimeUnit.MINUTES.toMillis(30);

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime());
        }

        long duration = ticket.getOutTime().getTime() - ticket.getInTime().getTime();
       
        if(duration <= HALF_AN_HOUR_IN_MILLISECONDS) {
        	ticket.setPrice(0);
        	return;
        }
        
        
        switch (ticket.getParkingSpot().getParkingType()) {
            case CAR: {
            	if(ticket.isAReccuringUser == true) {
            		ticket.setPrice(duration / ONE_HOUR_IN_MILLISECONDS * Fare.DISCOUNT);
            		break;
            	}
            	ticket.setPrice(duration / ONE_HOUR_IN_MILLISECONDS * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
            	ticket.setPrice(duration / ONE_HOUR_IN_MILLISECONDS * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default:
                throw new IllegalArgumentException("Unkown Parking Type");
        }
        
      
    }
}