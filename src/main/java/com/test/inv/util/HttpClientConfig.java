package com.test.inv.util;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.TrustStrategy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.apache.http.ssl.SSLContextBuilder;

@RefreshScope
@Configuration
public class HttpClientConfig {

	@Value("${http.maxTotal}")
	private Integer maxTotal;

	@Value("${http.defaultMaxPerRoute}")
	private Integer defaultMaxPerRoute;

	@Value("${http.connectTimeout}")
	private Integer connectTimeout;

	@Value("${http.connectionRequestTimeout}")
	private Integer connectionRequestTimeout;

	@Value("${http.socketTimeout}")
	private Integer socketTimeout;

	@Value("${http.staleConnectionCheckEnabled}")
	private boolean staleConnectionCheckEnabled;

	static final TrustStrategy trustAllStrategy = new TrustStrategy(){
		public boolean isTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			return true;
		}
	};

	/**
	 * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
	 * @return
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 */
	@Bean(name = "httpClientConnectionManager")
	public PoolingHttpClientConnectionManager getHttpClientConnectionManager() throws Exception{

		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, trustAllStrategy);
		SSLConnectionSocketFactory sslsf 
		= new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);


		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", new PlainConnectionSocketFactory())
				.register("https", sslsf)
				.build();

		PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
		//最大连接数
		httpClientConnectionManager.setMaxTotal(maxTotal);
		//并发数
		httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		return httpClientConnectionManager;
	}

	/**
	 * 注入连接池，用于获取httpClient
	 * @param httpClientBuilder
	 * @return
	 * @throws Throwable 
	 * @throws NoSuchAlgorithmException 
	 */
	@Bean
	public CloseableHttpClient getCloseableHttpClient(
			@Qualifier("httpClientConnectionManager")PoolingHttpClientConnectionManager httpClientConnectionManager) throws Throwable{
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, trustAllStrategy);
		SSLConnectionSocketFactory sslsf 
		= new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		//HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(httpClientConnectionManager);
		httpClientBuilder.setSSLSocketFactory(sslsf);
		return httpClientBuilder.build();

	}

	/**
	 * Builder是RequestConfig的一个内部类
	 * 通过RequestConfig的custom方法来获取到一个Builder对象
	 * 设置builder的连接信息
	 * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
	 * @return
	 */
	@Bean(name = "builder")
	public RequestConfig.Builder getBuilder(){
		RequestConfig.Builder builder = RequestConfig.custom();
		return builder.setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.setSocketTimeout(socketTimeout)
				.setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
	}

	/**
	 * 使用builder构建一个RequestConfig对象
	 * @param builder
	 * @return
	 */
	@Bean
	public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder){
		return builder.build();
	}
}