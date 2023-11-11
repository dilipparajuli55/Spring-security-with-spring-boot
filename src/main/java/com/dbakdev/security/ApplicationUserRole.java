package com.dbakdev.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	
				STUDENT(Sets.newHashSet()),
				
				ADMIN(Sets.newHashSet( ApplicationUserPermission.COURSE_READ,
										ApplicationUserPermission.COURSE_WRITE,
										ApplicationUserPermission.STUDENT_READ,
										ApplicationUserPermission.STUDENT_WRITE)),
				
				ADMIN_TRAINEE(Sets.newHashSet(ApplicationUserPermission.STUDENT_READ));
				
		private final Set<ApplicationUserPermission> permissions;

		private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
			this.permissions = permissions;
		}
	
		
}
