package com.example.demo.pojo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class EmpPojo {
	private String firstName;
	private String lastName;
	private String email;
	private MultipartFile multipartFile;

}
