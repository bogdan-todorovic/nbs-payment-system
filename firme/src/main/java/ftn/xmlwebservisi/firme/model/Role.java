package ftn.xmlwebservisi.firme.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
			fetch = FetchType.EAGER)
	@JoinTable(
			name = "role_permission", 
			joinColumns = @JoinColumn(name = "role_id"),
			inverseJoinColumns = @JoinColumn(name = "permission_id"))
	private List<Permission> permissions = new ArrayList<>();

	public Role() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public void addPermission(Permission permission) {
		permissions.add(permission);
		permission.getRoles().add(this);
	}
	
	public void removeRole(Permission permission) {
		permissions.remove(permission);
		permission.getRoles().remove(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(this instanceof Role)) return false;
		return id != null && id.equals(((Role)obj).id);
	}
	
	@Override
	public int hashCode() {
		return 45;
	}
	
}
