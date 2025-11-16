package id.afterlife.updater.misc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import java.io.InputStream;

public class UserInfoUtils {
    private static final String TAG = "UserInfoUtils";

    public static String getUserName(Context context) {
        try {
            String name = Settings.Secure.getString(
                    context.getContentResolver(), "afterlife_username");
            if (name != null && !name.isEmpty()) return name;
        } catch (Exception e) {
            Log.e(TAG, "Failed to read username", e);
        }
        return "Afterlife User";
    }

    public static Bitmap getUserAvatar(Context context) {
        try {
            String uriString = Settings.Secure.getString(
                    context.getContentResolver(), "afterlife_user_avatar_uri");
            if (uriString != null && !uriString.isEmpty()) {
                Uri uri = Uri.parse(uriString);
                try (InputStream in = context.getContentResolver().openInputStream(uri)) {
                    return BitmapFactory.decodeStream(in);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to load avatar", e);
        }
        return null;
    }
}
