package mytest;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;


@Configuration
@PropertySource("classpath:/META-INF/elastichsearch.properties")
public class Conf {

    Logger logger = LoggerFactory.getLogger(Conf.class);

    @Value("${ordertrace.es.cluster.name}")
    private String clusterName;
    @Value("${ordertrace.es.cluster.nodes}")
    private String clusterNodes;
//    @Value("${cluster.name}")
//    private String clusterName;
//    @Value("${cluster.nodes}")
//    private String clusterNodes;

    @Before
    @Bean
    public TransportClientFactoryBean orderTraceClient() {
        TransportClientFactoryBean transportClientFactoryBean = new TransportClientFactoryBean();
        transportClientFactoryBean.setClusterName(clusterName);
        transportClientFactoryBean.setClusterNodes(clusterNodes);
        logger.info("-------------Es连接客户端初始化完成，，，");
        return transportClientFactoryBean;
    }

    @Bean
    public EsServiceUtil esServiceUtil() {
        return new EsServiceUtil();
    }
}
