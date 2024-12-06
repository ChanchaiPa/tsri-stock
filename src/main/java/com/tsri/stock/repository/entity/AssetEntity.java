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
@Table(name = "stock4_asset", catalog = "thaisri_db")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class AssetEntity {

	@Getter @Setter @ToString.Include
	@Column(name = "ast_id")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer	astId;
	
	@Getter @Setter
	@Column(name = "ast_code")
	private String	astCode;
	
	@Getter @Setter
	@Column(name = "ast_name")
	private String 	astName;
	
	@Getter @Setter
	@Column(name = "ast_grp_id")
	private Integer	astGrpId;
	
	@Getter @Setter
	@Column(name = "ast_cat_id")
	private Integer	astCatId;
	
	@Getter @Setter
	@Column(name = "ast_min_purchase")
	private Double	astMinPurchase;
	
	@Getter @Setter
	@Column(name = "ast_unit")
	private String  astUnit;
	
	@Getter @Setter
	@Column(name = "ast_regis_id", updatable = false)
	private String	astRegisId;
	
	@Getter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ast_regis_dt", updatable = false)	
	private Date	astRegisDt;
	
	@Getter @Setter
	@Column(name = "ast_status")
	private String	astStatus;
	
	//*******************************
	@Getter
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ast_grp_id", insertable=false, updatable=false)
	private GroupEntity groupObj;	
	
	@Getter
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ast_cat_id", insertable=false, updatable=false)
	private CategoryEntity categoryObj;

}
