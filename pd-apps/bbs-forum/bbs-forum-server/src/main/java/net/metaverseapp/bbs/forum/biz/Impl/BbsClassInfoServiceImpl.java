package net.metaverseapp.bbs.forum.biz.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.dao.BbsClassMapper;
import net.metaverseapp.bbs.forum.biz.service.BbsClassInfoService;
import net.metaverseapp.bbs.forum.entity.BbsClass;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BbsClassInfoServiceImpl extends ServiceImpl<BbsClassMapper, BbsClass> implements BbsClassInfoService {
}
