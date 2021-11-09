package net.metaverseapp.bbs.forum.controller;

import com.itheima.pinda.base.BaseController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping("/BbsTopicStateTypeInfo")
@Api(value = "BbsTopicStateTypeInfo", tags = "帖子与帖子状态的brigde信息")
public class BbsTopicStateTypeController extends BaseController {



}
