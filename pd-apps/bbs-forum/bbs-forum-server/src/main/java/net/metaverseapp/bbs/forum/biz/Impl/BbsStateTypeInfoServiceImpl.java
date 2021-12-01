package net.metaverseapp.bbs.forum.biz.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.dao.BbsStateTypeMapper;
import net.metaverseapp.bbs.forum.biz.service.BbsStateTypeInfoService;
import net.metaverseapp.bbs.forum.entity.BbsStateType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BbsStateTypeInfoServiceImpl extends ServiceImpl<BbsStateTypeMapper, BbsStateType> implements BbsStateTypeInfoService {
}
