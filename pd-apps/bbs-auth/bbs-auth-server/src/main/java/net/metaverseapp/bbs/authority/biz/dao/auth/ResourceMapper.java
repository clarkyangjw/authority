package net.metaverseapp.bbs.authority.biz.dao.auth;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.metaverseapp.bbs.authority.dto.auth.ResourceQueryDTO;
import net.metaverseapp.bbs.authority.entity.auth.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 查询用户拥有的资源
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}
