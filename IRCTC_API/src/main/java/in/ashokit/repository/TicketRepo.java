package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
