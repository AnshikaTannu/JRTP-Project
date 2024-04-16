package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Passenger;
import in.ashokit.entity.Ticket;
import in.ashokit.repository.TicketRepo;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepo ticketRepo;

	@Override
	public Ticket bookTicket(Passenger passenger) {
		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(passenger, ticket);

		ticket.setTicketStatus("Confermed");
		Ticket save = ticketRepo.save(ticket);
		return save;
	}

	@Override
	public Ticket getTickets(Integer ticketId) {
		Optional<Ticket> Id = ticketRepo.findById(ticketId);
		if (Id.isPresent()) {
			return Id.get();
		}
		return null;
	}

	@Override
	public List<Ticket> getAllTickets() {
		return ticketRepo.findAll();
	}

}
