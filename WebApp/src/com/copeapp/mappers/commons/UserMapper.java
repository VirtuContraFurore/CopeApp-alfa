package com.copeapp.mappers.commons;

import org.mapstruct.Mapper;

import com.copeapp.dto.commons.UserDTO;
import com.copeapp.entities.common.User;

@Mapper(uses = RoleMapper.class)
public interface UserMapper {
	
	UserDTO userToUserDTO (User user);
}
