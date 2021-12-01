package net.metaverseapp.bbs.forum.config;


import com.itheima.pinda.database.datasource.BaseMybatisConfiguration;
import com.itheima.pinda.database.properties.DatabaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class ForumServerMybatisAutoConfiguration extends BaseMybatisConfiguration {
    public ForumServerMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

}
