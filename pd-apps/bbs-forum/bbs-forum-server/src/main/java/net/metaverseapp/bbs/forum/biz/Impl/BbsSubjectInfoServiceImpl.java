package net.metaverseapp.bbs.forum.biz.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.dao.BbsSubjectMapper;
import net.metaverseapp.bbs.forum.biz.service.BbsSubjectInfoService;
import net.metaverseapp.bbs.forum.entity.BbsSubject;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BbsSubjectInfoServiceImpl extends ServiceImpl<BbsSubjectMapper, BbsSubject> implements BbsSubjectInfoService {
}
