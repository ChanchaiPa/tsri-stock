package com.tsri.stock.repository.entity;

import lombok.*;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "stock1_supplier", catalog = "thaisri_db")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class SupplierEntity {

	@Getter @Setter @ToString.Include
	@Column(name = "sup_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	supId;	
	
	@Getter @Setter
	@Column(name = "sup_code")
	private String supCode;
	
	@Getter @Setter
	@Column(name = "sup_name")
	private String	supName;	
	
	@Getter @Setter
	@Column(name = "sup_addr1")
	private String	supAddr1;	
	
	@Getter @Setter
	@Column(name = "sup_addr2")
	private String	supAddr2;	
	
	@Getter @Setter
	@Column(name = "sup_addr3")
	private String	supAddr3;		
	
	@Getter @Setter
	@Column(name = "sup_email")
	private String	supEmail;	
	
	@Getter @Setter
	@Column(name = "sup_tel")
	private String	supTel;	
	
	@Getter @Setter
	@Column(name = "sup_fax")
	private String	supFax;	
	
	@Getter @Setter
	@Column(name = "sup_contact_name")
	private String	supContactName;
	
	@Getter @Setter
	@Column(name = "sup_regis_id", updatable = false)
	private String	supRegisId;		
	
	@Getter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sup_regis_dt", updatable = false)		
	private Date supRegisDt;
	
	@Getter @Setter
	@Column(name = "sup_status")
	private String	supStatus;			
}
