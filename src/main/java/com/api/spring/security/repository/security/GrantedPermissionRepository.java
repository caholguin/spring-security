package com.api.spring.security.repository.security;

import com.api.spring.security.model.security.GrantedPermission;
import com.api.spring.security.model.security.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GrantedPermissionRepository extends JpaRepository<GrantedPermission, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM GrantedPermission gp WHERE gp.role = :role")
    void deleteByRole(Role role);
}
