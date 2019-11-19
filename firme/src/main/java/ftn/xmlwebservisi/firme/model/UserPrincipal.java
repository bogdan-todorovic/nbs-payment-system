package ftn.xmlwebservisi.firme.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal extends User implements UserDetails {

	private static final long serialVersionUID = -3795813165945363662L;
	
	public UserPrincipal(final User user) {
		super(user.getId(), user.getUsername(), user.getPassword(), user.getRoles());
	}
	
	// Spring security uses this to determine which authorization rights user has
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> userRoles = super.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : userRoles) {
			List<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {				
				authorities.add(new SimpleGrantedAuthority(permission.getName()));
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
