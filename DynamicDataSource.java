package com.sibu.sixin.utils.sql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	// 保证线程安全
	private static final ThreadLocal<String> dataSourceNameHolder = new ThreadLocal<String>();

	public static void setDataSourceName(String name) {
		dataSourceNameHolder.set(name);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		String name = dataSourceNameHolder.get();
		// 获取之后将datasource设置成默认值
		dataSourceNameHolder.set(null);
		return name;
	}
}
