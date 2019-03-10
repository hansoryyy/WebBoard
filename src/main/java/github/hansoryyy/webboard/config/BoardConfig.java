package github.hansoryyy.webboard.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import github.hansoryyy.webboard.util.AES256Util;

@Configuration
public class BoardConfig {

	@Value("${aes.key}") String key;
	
	@Bean
	public AES256Util create() {
		// System.out.println("key: " + this.key);
		char [] key = this.key.toCharArray();
		
		AES256Util enc = new AES256Util(key);
		Arrays.fill(key, '_');
		this.key = null;
		
		return enc;
	}
}
