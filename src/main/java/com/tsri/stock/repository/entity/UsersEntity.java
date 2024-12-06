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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.RowId;
import org.hibernate.annotations.SelectBeforeUpdate;


@Entity
@Table(name = "user_data", catalog = "thaisri_db")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class UsersEntity {

	@Getter @Setter @ToString.Include
	@Column(name = "user_no")
	@Id //@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer	id;	
	
	@Getter @Setter
	@Column(name = "user_id")
	private String	userid;		
	
	@Getter @Setter
	@Column(name = "mail_username", unique = true)
	private String	username;	
	
	@Getter @Setter
	@Column(name = "password")
	private String	password;	
	
	@Getter @Setter
	@Transient
	private String	role = "USER";	
	
	@Getter @Setter
	@Column(name = "name_thai")
	private String	firstname;		
	
	@Getter @Setter
	@Column(name = "lastname_thai")
	private String	lastname;		
	
	@Getter @Setter
	@Column(name = "email")
	private String	email;	
	
	@Getter
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reg_dt", updatable = false)		
	private Date createDate;	
	
}
