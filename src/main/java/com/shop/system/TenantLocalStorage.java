package com.shop.system;

public class TenantLocalStorage {

	private static final ThreadLocal<String> TENANT = new ThreadLocal<>();

	public static void setTenantName(String tenantName) {
		TENANT.set(tenantName);
	}

	public static String getTenantName() {
		return TENANT.get();
	}

}
