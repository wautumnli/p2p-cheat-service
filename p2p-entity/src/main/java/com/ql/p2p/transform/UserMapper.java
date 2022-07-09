package com.ql.p2p.transform;

import com.ql.p2p.dto.UserDto;
import com.ql.p2p.po.UserPo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author wanqiuli
 * @date 2022/7/9 15:31
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserPo userPo);
}
