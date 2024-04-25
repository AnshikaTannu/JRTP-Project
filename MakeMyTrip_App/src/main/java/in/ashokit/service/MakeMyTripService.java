package in.ashokit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.binding.Passenger;
import in.ashokit.binding.Ticket;
import reactor.core.publisher.Mono;

@Service
public class MakeMyTripService {

	private final String BOOK_TICKET_URL = "http://localhost:8081/ticket";

	private final String GET_TICKETS_URL = "http://localhost:8081/tickets";

//	private final String GET_TICKET_URL = "http://localhost:8081/tickets/{tId}";

	public Mono<Ticket> bookTicket(Passenger p) {
		WebClient webClient = WebClient.create();
		return webClient.post()
										 .uri(BOOK_TICKET_URL)
										 .body(BodyInserters.fromValue(p))
										 .retrieve()
										 .bodyToMono(Ticket.class);

	}

//	public Mono<Ticket> getTicket() {
//		WebClient webClient = WebClient.create();
//		return webClient.get()
//										 .uri(GET_TICKET_URL)
//										 .retrieve()
//										 .bodyToMono(Ticket.class);
//
//	}

	public Mono<Ticket[]> getAllTickets() {
		WebClient webClient = WebClient.create();

		return webClient.get()
										 .uri(GET_TICKETS_URL)
										 .retrieve()
										 .bodyToMono(Ticket[].class);

	}

}
