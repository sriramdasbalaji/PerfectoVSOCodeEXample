package test.java;

public class PerfectoTestParams {

	String _repKey;
	device _device;
	String _platform;
	String _activityBandle;
	String _cloud = "https://microsoft.perfectomobile.com";
	String _user = "********** USER *************";
	String _password = "****** PASSWORD ********";

	public PerfectoTestParams(device device,String repKey,String platform,String ActivityBandle) {
		_device = device;
		_repKey = repKey;
		_platform =platform;
		_activityBandle = ActivityBandle;
	}

}
