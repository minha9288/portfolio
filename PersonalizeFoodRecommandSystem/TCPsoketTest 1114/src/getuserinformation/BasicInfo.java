package getuserinformation;

import java.text.SimpleDateFormat;
import android.graphics.Bitmap;
import com.facebook.android.Facebook;

public class BasicInfo {
	
	public static final int REQ_CODE_FACEBOOK_LOGIN = 1001;
	
	public static boolean FacebookLogin = false;
	public static boolean RetryLogin = false;
	
	public static Facebook FacebookInstance = null;
	
	public static String[] FACEBOOK_PERMISSIONS = {"publish_stream", "read_stream", "user_photos", "email"};
	
	public static String FACEBOOK_ACCESS_TOKEN = "";
	public static String FACEBOOK_APP_ID = "342460412545537";
	//public static String FACEBOOK_API_KEY = "224a8cee1a78a9fbb040bdad7153674a";
	public static String FACEBOOK_APP_SECRET = "35fb760e9f63bd04dcdad505559130b9";
	
	public static String FACEBOOK_NAME = "";

	public static SimpleDateFormat OrigDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
	public static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

	public static Bitmap BasicPicture = null;
}
