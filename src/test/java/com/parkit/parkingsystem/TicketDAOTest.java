package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.InstanceOf;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

public class TicketDAOTest {

	public static final String vehicleReg = "JOJO";

	@Test
	public void saveTicketTest() {

		Ticket ticket = new Ticket();
		// ticket properties.
		ticket.setAReccuringUser(false);
		Date inTime = new Date();
		inTime.setTime(System.currentTimeMillis() - (60 * 60 * 1000));
		ticket.setInTime(inTime);
		ticket.setParkingSpot(new ParkingSpot(2, ParkingType.CAR, false));
		ticket.setVehicleRegNumber(vehicleReg);
		ticket.setPrice(0);

		TicketDAO ticketDAO = new TicketDAO();
		int result = ticketDAO.saveTicket(ticket);
		// That doesn't seem to be a clean way of making it.
		assertEquals(1, result);
	}

	@Test
	public void getTicketTest() {
		saveTicketTest();
		TicketDAO ticketDAO = new TicketDAO();
		Ticket ticketReturned = ticketDAO.getTicket(vehicleReg);
		assertEquals(ticketReturned.getClass(), Ticket.class);
	}

	@Test
	public void updateTicketTest() {
		saveTicketTest();
		TicketDAO ticketDAO = new TicketDAO();
		Ticket ticketReturned = ticketDAO.getTicket(vehicleReg);
		ticketReturned.setOutTime(new Date(System.currentTimeMillis()));
		assertEquals(true, ticketDAO.updateTicket(ticketReturned));
	}

}
