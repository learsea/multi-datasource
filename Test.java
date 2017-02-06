package com.sibu.sixin.transaction.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.sibu.sixin.service.iface.IAService;
import com.sibu.sixin.service.iface.IBService;
import com.sibu.sixin.utils.sql.DataSource;

@Service
public class Test implements ApplicationContextAware {
	private Test t;
	@Autowired
	private IAService a;
	@Autowired
	private IBService b;

	@DataSource("dataSource1")
	public void a() {
		a.a();
		t.b();
	}

	@DataSource("dataSource2")
	public void b() {
		b.b();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		t = applicationContext.getBean(Test.class);
	}
}
