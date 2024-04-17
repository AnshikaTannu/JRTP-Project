package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Enquiries;
import in.ashokit.service.EnquirieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnqueryController {

	@Autowired
	private EnquirieService enquiriesService;

	// add enq - page display
	@GetMapping("/enquery")
	public String addEnq(Enquiries enq, Model model) {
		model.addAttribute("enq", new Enquiries());
		return "addEnq";
	}

	// save enq
	@PostMapping("/enquery")
	public String saveEnquiries(Enquiries enq, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);

		Integer Cid = (Integer) session.getAttribute("cid");
		boolean status = enquiriesService.addEnquiries(enq, Cid);

		if (status) {
			model.addAttribute("enq", new Enquiries());
			model.addAttribute("msg", "Saved Successfully");
		} else {
			model.addAttribute("enq", new Enquiries());
			model.addAttribute("emsg", "Failed to save");
		}

//		model.addAttribute("enq", new Enquiries());
		return "addEnq";
	}

	// view enq
	@GetMapping("/enquiries")
	public String getEnquiry(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer Cid = (Integer) session.getAttribute("cid");

		List<Enquiries> list = enquiriesService.getEnquiries(new Enquiries(), Cid);
		model.addAttribute("enqs", list);

		model.addAttribute("enq", new Enquiries());

		return "viewEnquiries";
	}

	// filter enq
	@PostMapping("/filter-enqs")
	private String filterEnqs(@ModelAttribute("enq")  Enquiries enq, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer Cid = (Integer) session.getAttribute("cid");

		List<Enquiries> list = enquiriesService.getEnquiries(enq, Cid);
		model.addAttribute("enqs", list);
//		model.addAttribute("enq", new Enquiries());
		return "viewEnquiries";
	}

	// edit & update enq
	@GetMapping("/edit")
	public String editEnquiry(@RequestParam("id") Integer enqId, Model model) {
		Enquiries enquiry = enquiriesService.updateEnquiries(enqId);
		model.addAttribute("enq", enquiry);
		return "addEnq";
	}

}
