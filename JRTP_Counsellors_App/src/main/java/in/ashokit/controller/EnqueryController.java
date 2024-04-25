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
import in.ashokit.util.AppConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnqueryController {

	@Autowired
	private EnquirieService enquiriesService;

	@GetMapping("/enquery")
	public String addEnq(Enquiries enq, Model model) {
		model.addAttribute("enq", new Enquiries());
		return AppConstant.ADD_ENQ;
	}

	@PostMapping("/enquery")
	public String saveEnquiries(Enquiries enq, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);

		Integer cid = (Integer) session.getAttribute("cid");
		boolean status = enquiriesService.addEnquiries(enq, cid);

		if (status) {
			model.addAttribute("enq", new Enquiries());
			model.addAttribute("msg", "Saved Successfully");
		} else {
			model.addAttribute("enq", new Enquiries());
			model.addAttribute("emsg", "Failed to save");
		}

		return AppConstant.ADD_ENQ;
	}

	@GetMapping("/enquiries")
	public String getEnquiry(HttpServletRequest req, Model model) {

		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		List<Enquiries> list = enquiriesService.getEnquiries(new Enquiries(), cid);
		model.addAttribute("enqs", list);

		model.addAttribute("enq", new Enquiries());

		return "viewEnquiries";
	}

	@PostMapping("/filter-enqs")
	public String filterEnqs(@ModelAttribute("enq") Enquiries enq, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		List<Enquiries> list = enquiriesService.getEnquiries(enq, cid);
		model.addAttribute("enqs", list);
		return "viewEnquiries";
	}

	@GetMapping("/edit")
	public String editEnquiry(@RequestParam("id") Integer enqId, Model model) {
		Enquiries enquiry = enquiriesService.updateEnquiries(enqId);
		model.addAttribute("enq", enquiry);
		return AppConstant.ADD_ENQ;
	}

}
