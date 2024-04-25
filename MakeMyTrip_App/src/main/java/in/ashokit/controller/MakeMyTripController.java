package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.Passenger;
import in.ashokit.binding.Ticket;
import in.ashokit.service.MakeMyTripService;
import reactor.core.publisher.Mono;

@Controller
public class MakeMyTripController {

	@Autowired
	public MakeMyTripService makeService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("passenger", new Passenger());
		return "index";
	}

	@PostMapping("/ticket")
	public String bookTicket(Passenger p, Model model) {
		Mono<Ticket> mono = makeService.bookTicket(p);
		model.addAttribute("ticket", mono);
		return "viewTicket";
	}
//	
//	@GetMapping("/")
//	public String getTicket(Model model) {
//		makeService.getTicket();
//		return "";
//	}

	@GetMapping("/tickets")
	public String getAllTicket(Model model) {
		Mono<Ticket[]> tickets = makeService.getAllTickets();
		model.addAttribute("tickets", tickets);
		return "viewAllTicket";
	}
}
