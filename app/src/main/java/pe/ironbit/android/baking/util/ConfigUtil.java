package pe.ironbit.android.baking.util;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Utility functions used for information related with the device.
 */
public final class ConfigUtil {
    /**
     * Unique private constructor.
     */
    private ConfigUtil() {
    }

    /**
     * Verify orientation of the device.
     *
     * @param context activity context.
     * @return true if it is portrait, false if it is landscape.
     */
    public static boolean isOrientationPortrait(Context context) {
        return (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT);
    }

    /**
     * Verify whether device is tablet or phone.
     *
     * @param context activity context.
     * @return true if it is a tablet, false if it is a phone.
     */
    public static boolean isDeviceTablet(Context context) {
        return ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE);
    }
}
