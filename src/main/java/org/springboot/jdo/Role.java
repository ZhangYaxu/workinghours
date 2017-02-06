package org.springboot.jdo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色.
 * 
 * @author calvin
 */
@Entity
@Table(name = "role")
public class Role extends IdEntity {

	private String name;

	@Column(nullable = false, unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
