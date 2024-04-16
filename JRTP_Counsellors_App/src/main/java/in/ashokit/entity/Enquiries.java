package in.ashokit.entity;

import java.time.LocalDate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquiries {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enqId;
	private String stuName;
	private Long phNo;
	private String mode;
	private String course;
	private String status;

	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updateDate;

	@ManyToOne
	@JoinColumn(name = "counseller_Id")
	private Counsellers counsellers;

	public Integer getEnqId() {
		return enqId;
	}

	public void setEnqId(Integer enqId) {
		this.enqId = enqId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Long getPhNo() {
		return phNo;
	}

	public void setPhNo(Long phNo) {
		this.phNo = phNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public Counsellers getCounsellers() {
		return counsellers;
	}

	public void setCounsellers(Counsellers counsellers) {
		this.counsellers = counsellers;
	}

}
