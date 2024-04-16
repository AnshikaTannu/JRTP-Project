package in.ashokit.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Counsellers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counseller_Id;
	private String name;

	private String email;

	private String pwd;

	private Long phno;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	@OneToMany(mappedBy = "counsellers", cascade = CascadeType.ALL)
	private List<Enquiries> enquiries;

	

	public Integer getCounseller_Id() {
		return counseller_Id;
	}

	public void setCounseller_Id(Integer counseller_Id) {
		this.counseller_Id = counseller_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Enquiries> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(List<Enquiries> enquiries) {
		this.enquiries = enquiries;
	}

}
