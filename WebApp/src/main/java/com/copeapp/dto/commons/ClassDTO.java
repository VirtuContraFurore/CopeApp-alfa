package com.copeapp.dto.commons;

import java.util.List;

import com.copeapp.entities.common.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ClassDTO {

	@NonNull
	private Integer classId;
	
	@NonNull
	private Integer classNumber;
	
	@NonNull
	private String classSection;
	
	@NonNull
	private List<User> teachers;
	
	@NonNull
	private List<User> students;

}
