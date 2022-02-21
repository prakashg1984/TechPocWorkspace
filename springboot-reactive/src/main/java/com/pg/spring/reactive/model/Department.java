package com.pg.spring.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("department")
@Getter
@Setter
public class Department {
	@Id
	private Integer id;
	private String name;
	@Column("user_id")
	private Integer userId;
	private String loc;
}
