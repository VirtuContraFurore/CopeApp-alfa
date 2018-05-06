package com.copeapp.mappers.commons;

import org.mapstruct.Mapper;

import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.entities.common.Role;

@Mapper
public interface RoleMapper {

	RoleDTO roleToRoleDTO (Role role);
}
