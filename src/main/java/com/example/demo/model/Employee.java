package com.example.demo.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@Table(name="kushal")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String fileName;
	private String fileType;
	@Lob
	private byte[] fileData;

}
