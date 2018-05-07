package com.copeapp.mappers.commons;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.copeapp.dto.commons.RoleDTO;
import com.copeapp.dto.commons.UserDTO;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.User;

@Mapper//(uses = RoleMapper.class)
public interface UserMapper {
	
	UserMapper istance = Mappers.getMapper( UserMapper.class );
	
	UserDTO userToUserDTO (User user);
	
	RoleDTO roleToRoleDTO(Role role);
}
