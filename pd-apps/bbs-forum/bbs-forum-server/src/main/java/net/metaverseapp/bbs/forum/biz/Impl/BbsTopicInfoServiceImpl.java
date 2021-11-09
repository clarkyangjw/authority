package net.metaverseapp.bbs.forum.biz.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.dao.BbsTopicMapper;
import net.metaverseapp.bbs.forum.biz.service.BbsTopicInfoService;
import net.metaverseapp.bbs.forum.entity.BbsTopic;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BbsTopicInfoServiceImpl extends ServiceImpl<BbsTopicMapper, BbsTopic> implements BbsTopicInfoService {
}
