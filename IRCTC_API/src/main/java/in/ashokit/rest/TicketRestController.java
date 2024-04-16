package in.ashokit.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Passenger;
import in.ashokit.entity.Ticket;
import in.ashokit.service.TicketService;

@RestController
public class TicketRestController {

	@Autowired
	private TicketService ticketService;

	@PostMapping(value = "/ticket", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Ticket> bookTicket(@RequestBody Passenger p) {
		Ticket bookTicket = ticketService.bookTicket(p);

		return new ResponseEntity<>(bookTicket, HttpStatus.CREATED);
	}

	@GetMapping(value = "/ticket/{tId}", produces = "application/json")
	public ResponseEntity<Ticket> getTicket(@PathVariable("tId") Integer tId) {
		Ticket ticket = ticketService.getTickets(tId);
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@GetMapping(value = "/tickets", produces = "application/json")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		List<Ticket> allTickets = ticketService.getAllTickets();
		return new ResponseEntity<>(allTickets, HttpStatus.OK);
	}

}
