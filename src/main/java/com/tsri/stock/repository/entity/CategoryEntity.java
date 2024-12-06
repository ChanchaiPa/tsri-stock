package com.tsri.stock.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "stock3_category", catalog = "thaisri_db")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class CategoryEntity {

	@Getter @Setter @ToString.Include
	@Column(name = "cat_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer	catId;
	
	@Getter @Setter
	@Column(name = "cat_code")
	private String 	catCode;
	
	@Getter @Setter
	@Column(name = "cat_name")
	private String 	catName;
	
	@Getter @Setter
	@Column(name = "cat_grp_id")
	private Integer catGrpId;
	
	@Getter @Setter
	@Column(name = "cat_regis_id", updatable = false)
	private String  catRegisId;
	
	@Getter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cat_regis_dt", updatable = false)	
	private Date 	catRegisDt;
	
	@Getter @Setter
	@Column(name = "cat_status")
	private String 	catStatus;

	@Getter
	@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "grp_id", referencedColumnName = "cat_grp_id", insertable=false, updatable=false)
	@JoinColumn(name = "cat_grp_id", insertable=false, updatable=false)
	private GroupEntity groupObj;
	
}
