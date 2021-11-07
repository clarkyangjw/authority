package net.metaverseapp.bbs.authority.biz.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pinda.log.entity.OptLogDTO;
import net.metaverseapp.bbs.authority.entity.common.OptLog;

public interface OptLogService extends IService<OptLog> {
    /**
     * 保存日志
     */
    boolean save(OptLogDTO entity);

}
