package jwmatthys.rtcmix;

import android.util.Log;

public class MyLog
{
	
	public static void d(String tag, String msg)
	{
		if (Log.isLoggable(tag, Log.DEBUG))
			{
				Log.d(tag, msg);
			}
	}
	
	public static void i(String tag, String msg)
	{
		if (Log.isLoggable(tag, Log.INFO))
			{
				Log.i(tag, msg);
			}
	}
	
	public static void e(String tag, String msg)
	{
		if (Log.isLoggable(tag, Log.ERROR))
			{
				Log.e(tag, msg);
			}
	}
	
	public static void v(String tag, String msg)
	{
		if (Log.isLoggable(tag, Log.VERBOSE))
			{
				Log.v(tag, msg);
			}
	}
	
	public static void w(String tag, String msg)
	{
		if (Log.isLoggable(tag, Log.WARN))
			{
				Log.w(tag, msg);
			}
	}
}

