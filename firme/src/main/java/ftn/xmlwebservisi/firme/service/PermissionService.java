package ftn.xmlwebservisi.firme.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


@Service
public class PermissionService {

	public boolean isAuthorized(Collection<? extends GrantedAuthority> authorities, String permission) {
		return authorities.stream()
				.anyMatch(authority -> authority.getAuthority().equals(permission));
	}
	
}
