package com.tsri.stock.repository.entity;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "stock2_group", catalog = "thaisri_db")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class GroupEntity {

	@Getter @Setter @ToString.Include
	@Column(name = "grp_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer grpId;
	
	@Getter @Setter
	@Column(name = "grp_code")
	private String 	grpCode;
	
	@Getter @Setter
	@Column(name = "grp_name")
	private String 	grpName;
	
	@Getter @Setter
	@Column(name = "grp_regis_id", updatable = false)
	private String 	grpRegisId;
	
	@Getter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "grp_regis_dt", updatable = false)		
	private Date 	grpRegisDt;
	
	@Getter @Setter
	@Column(name = "grp_status")
	private String grpStatus;
}
