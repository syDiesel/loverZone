package com.lz.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SecQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sec_question", catalog = "loverzone")
public class SecQuestion implements java.io.Serializable {

	// Fields

	private Integer id;
	private String question;
	private Set<Security> securities = new HashSet<Security>(0);

	// Constructors

	/** default constructor */
	public SecQuestion() {
	}

	/** full constructor */
	public SecQuestion(String question, Set<Security> securities) {
		this.question = question;
		this.securities = securities;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "question", length = 65535)
	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "secQuestion")
	public Set<Security> getSecurities() {
		return this.securities;
	}

	public void setSecurities(Set<Security> securities) {
		this.securities = securities;
	}

}