package net.metaverseapp.bbs.forum.biz.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.dao.BbsTopicStateTypeMapper;
import net.metaverseapp.bbs.forum.biz.service.BbsTopicStateTypeInfoService;
import net.metaverseapp.bbs.forum.entity.BbsTopicStateType;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BbsTopicStateTypeInfoServiceImpl extends ServiceImpl<BbsTopicStateTypeMapper, BbsTopicStateType> implements BbsTopicStateTypeInfoService {
}
