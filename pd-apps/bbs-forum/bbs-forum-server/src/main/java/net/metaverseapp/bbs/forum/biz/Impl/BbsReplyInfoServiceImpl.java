package net.metaverseapp.bbs.forum.biz.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.metaverseapp.bbs.forum.biz.dao.BbsReplyMapper;
import net.metaverseapp.bbs.forum.biz.service.BbsReplyInfoService;
import net.metaverseapp.bbs.forum.entity.BbsReply;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BbsReplyInfoServiceImpl extends ServiceImpl<BbsReplyMapper, BbsReply> implements BbsReplyInfoService {
}
