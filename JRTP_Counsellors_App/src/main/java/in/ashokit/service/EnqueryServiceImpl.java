
package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Counsellers;
import in.ashokit.entity.Enquiries;
import in.ashokit.repo.CounsellersRepo;
import in.ashokit.repo.EnquiriesRepo;

@Service
public class EnqueryServiceImpl implements EnquirieService {

	@Autowired
	private EnquiriesRepo enquiriesRepo;

	@Autowired
	private CounsellersRepo counsellerRepo;

	@Override
	public Dashboard getDasboardInfo(Integer counsellerId) {
		Long totalEnq = enquiriesRepo.getEnquries(counsellerId);
		Long openCnt = enquiriesRepo.getEnquries(counsellerId, "new");
		Long enrolledCnt = enquiriesRepo.getEnquries(counsellerId, "enrolled");
		Long lostCnt = enquiriesRepo.getEnquries(counsellerId, "lost");

		Dashboard d = new Dashboard();
		d.setTotalEnqs(totalEnq);
		d.setEnrolledEnqs(enrolledCnt);
		d.setOpenEnqs(openCnt);
		d.setLostEnqs(lostCnt);

		return d;
	}

	@Override
	public boolean addEnquiries(Enquiries enquiries, Integer counsellerId) {

		Counsellers counsellers = counsellerRepo.findById(counsellerId).orElseThrow();
		enquiries.setCounsellers(counsellers);

		Enquiries save = enquiriesRepo.save(enquiries);
		return save.getEnqId()!=null;
	}

	@Override
	public List<Enquiries>getEnquiries(Enquiries enquiries, Integer counsellerId) {
		Counsellers counsellers = new Counsellers();
		counsellers.setCounseller_Id(counsellerId);

		Enquiries searchCriteria = new Enquiries();
		searchCriteria.setCounsellers(counsellers);

		if (null!=enquiries.getCourse() && ! "".equals(enquiries.getCourse())) {
			searchCriteria.setCourse(enquiries.getCourse());
		}

		if (null!=enquiries.getMode() && ! "".equals(enquiries.getMode())) {
			searchCriteria.setMode(enquiries.getMode());
		}

		if (null!=enquiries.getStatus() && ! "".equals(enquiries.getStatus())) {
			searchCriteria.setStatus(enquiries.getStatus());
		}

		Example<Enquiries> of = Example.of(searchCriteria);
		return enquiriesRepo.findAll(of);
	}

	@Override
	public Enquiries updateEnquiries(Integer enqId) {
		
		Optional<Enquiries> id = enquiriesRepo.findById(enqId);
		Enquiries enquery=null;
		if(id.isPresent()) {
			enquery=id.get();
		}
		return enquery ;
	}


}
