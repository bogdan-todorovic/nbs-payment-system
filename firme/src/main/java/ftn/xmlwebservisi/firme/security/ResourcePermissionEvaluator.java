package ftn.xmlwebservisi.firme.security;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import ftn.xmlwebservisi.firme.model.UserPrincipal;
import ftn.xmlwebservisi.firme.service.PermissionService;

@Component
public class ResourcePermissionEvaluator implements PermissionEvaluator {
	
	private Logger logger = LoggerFactory.getLogger(ResourcePermissionEvaluator.class);	
	@Autowired
	PermissionService permissionService;
	
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if (!authentication.isAuthenticated()) {
			return false;
		}
		return permissionService.isAuthorized(authentication.getAuthorities(), permission.toString());
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		throw new UnsupportedOperationException();
	}

}
