package org.woo.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//读取配置文件
public class ServerProperties {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerProperties.class);
	private static final String FILE_NAME = "/conf/server.properties";
	private static Properties prop;

	public ServerProperties() {
	}

	private static void init() {
		String filePath = FILE_NAME;
		FileInputStream ins = null;

		try {
			ins = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(ins);
		} catch (FileNotFoundException var7) {
			LOGGER.error("Can not find server properties, it may result working problem. path: " + filePath, var7);
		} catch (Exception var8) {
			LOGGER.error("init server properties failed", var8);
		} finally {
			IOUtils.closeQuietly(ins);
		}

	}

	public static String getProperty(String key) {
		return null != prop ? prop.getProperty(key) : null;
	}

	static {
		init();
	}
}
