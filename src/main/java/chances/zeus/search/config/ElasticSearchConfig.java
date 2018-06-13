package chances.zeus.search.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
	@Bean
	public TransportClient client() throws UnknownHostException {
		// 设置端口名字
		InetSocketTransportAddress node = new InetSocketTransportAddress(InetAddress.getByName("192.168.220.127"),
				9300);
		// 设置集群名字
		Settings settings = Settings.builder().put("client.transport.ignore_cluster_name", true).build();

		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(node);
		return client;
	}
}
