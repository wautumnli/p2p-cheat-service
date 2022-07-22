package com.ql.p2p.transform;

import com.ql.p2p.domain.Auth;
import com.ql.p2p.dto.AuthDto;
import com.ql.p2p.po.AuthPo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author wanqiuli
 * @date 2022/7/22 21:41
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    AuthMapper MAPPER = Mappers.getMapper(AuthMapper.class);

    AuthDto toDto(AuthPo authPo);

    AuthPo toPo(Auth auth);
}
