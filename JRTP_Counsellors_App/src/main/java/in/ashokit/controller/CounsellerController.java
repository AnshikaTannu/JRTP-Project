package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellers;
import in.ashokit.service.CounsellerService;
import in.ashokit.service.EnquirieService;
import in.ashokit.util.AppConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellerController {

	@Autowired
	private CounsellerService counsellerService;

	@Autowired
	private EnquirieService enquiriesService;

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute(AppConstant.COUNSLLER, new Counsellers());
		return "index";
	}

	@PostMapping("/login")
	public String handleLogin(Counsellers counseller, HttpServletRequest req, Model model) {
		Counsellers co = counsellerService.getCounsellers(counseller.getEmail(), counseller.getPwd());

		if (co == null) {
			model.addAttribute(AppConstant.COUNSLLER, new Counsellers());
			model.addAttribute("emsg", "Invalid Credential");
			return "index";
		} else {
			HttpSession session = req.getSession(true);
			session.setAttribute("cid", co.getCounsellerId());
			Dashboard dbInfo = enquiriesService.getDasboardInfo(co.getCounsellerId());
			model.addAttribute(AppConstant.DASH, dbInfo);
			return "dashboard";
		}
	}

	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		Integer cid = (Integer) session.getAttribute("cid");

		Dashboard dbInfo = enquiriesService.getDasboardInfo(cid);
		model.addAttribute(AppConstant.DASH, dbInfo);
		return "dashboard";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute(AppConstant.COUNSLLER, new Counsellers());
		return "registerview";
	}

	@PostMapping("/register")
	public String registerHandle(Counsellers c, Model model) {
		boolean status = counsellerService.saveCounseller(c);

		if (status) {
			model.addAttribute(AppConstant.COUNSLLER, new Counsellers());
			model.addAttribute("msg", "Counsellers Saved");
		} else {
			model.addAttribute(AppConstant.COUNSLLER, new Counsellers());
			model.addAttribute("emsg", "Filed to Saved");
		}
		return "registerview";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		session.invalidate();

		return "redirect:/";
	}

}
