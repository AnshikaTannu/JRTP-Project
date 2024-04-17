package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.Dashboard;
import in.ashokit.entity.Enquiries;

public interface EnquirieService {
	
	public Dashboard getDasboardInfo(Integer  counsellerId);
	
	public boolean addEnquiries(Enquiries enqueries, Integer counsellerId);
	
	public List<Enquiries> getEnquiries(Enquiries enqueries ,Integer  counsellerId);
	
	public Enquiries updateEnquiries(Integer enqId);


}
