package cn.itproject.crm.controller.viewbean;

public class SystemContext {
	private static final ThreadLocal<SystemInfo> sysContext=new ThreadLocal<SystemInfo>();

	public static SystemInfo getSyscontext() {
		return sysContext.get();
	}
	public static void setSysContext(SystemInfo systemInfo){
		sysContext.set(systemInfo);
	}
}
