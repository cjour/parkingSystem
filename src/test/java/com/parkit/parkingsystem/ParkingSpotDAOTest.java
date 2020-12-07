package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.model.ParkingSpot;

public class ParkingSpotDAOTest {
	
	
	
	@Test
	public void getNextAvailableSlotTest() {
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
		
		assertEquals(1, parkingSpotDAO.getNextAvailableSlot(ParkingType.CAR));
	}

	@Test
	public void updateParkingTest() {
		ParkingSpotDAO parkingSpotDAO = new ParkingSpotDAO();
		ParkingSpot parkingSpot = new ParkingSpot(1, ParkingType.CAR, true);
		assertEquals(true, parkingSpotDAO.updateParking(parkingSpot));		
 
	}

}
