package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.Passenger;
import in.ashokit.entity.Ticket;

public interface TicketService {

	public Ticket bookTicket(Passenger passenger);

	public Ticket getTickets(Integer ticketId);

	public List<Ticket> getAllTickets();

}
