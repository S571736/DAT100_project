package no.hvl.dat100ptc.oppgave3;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

import java.util.Locale;

import static java.lang.Math.*;

public class GPSUtils {

    public static double findMax(double[] da) {

        double max;

        max = da[0];

        for (double d : da) {
            if (d > max) {
                max = d;
            }
        }

        return max;
    }

    public static double findMin(double[] da) {

        double min;

        // TODO - START

        min = da[0];

        for (double d : da) {
            if (d < min) {
                min = d;
            }

        }
        return min;
        // TODO - SLUT

    }

    public static double[] getLatitudes(GPSPoint[] gpspoints) {

        double[] latitudes = new double[gpspoints.length];
        // TODO - START

        for (int i = 0; i < gpspoints.length ; i++) {
            latitudes[i] = gpspoints[i].getLatitude();
        }

        return latitudes;
        // TODO - SLUTT
    }

    public static double[] getLongitudes(GPSPoint[] gpspoints) {

        // TODO - START
        double[] longitudes = new double[gpspoints.length];


        for (int i = 0; i < gpspoints.length ; i++) {
            longitudes[i] = gpspoints[i].getLongitude();
        }

        return longitudes;
        // TODO - SLUTT

    }

    private static int R = 6371000; // jordens radius

    public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

        double d;
        double latitude1, longitude1, latitude2, longitude2;

        latitude1 = gpspoint1.getLatitude();
        longitude1 = gpspoint1.getLongitude();

        latitude2 = gpspoint2.getLatitude();
        longitude2 = gpspoint2.getLongitude();

        // TODO - START
        latitude1 = Math.toRadians(latitude1);
        latitude2 = Math.toRadians(latitude2);
        longitude1 = Math.toRadians(longitude1);
        longitude2 = Math.toRadians(longitude2);

        double deltaPhi = latitude1 - latitude2;
        double deltaLambda = longitude1 - longitude2;
        double a = pow(sin(deltaPhi/2), 2) + cos(latitude1) * cos(latitude2) * pow(sin(deltaLambda/2), 2);
        double c = 2 * atan2(sqrt(a), sqrt((1-a)));
        d = R * c;

        return d;

        // TODO - SLUTT

    }

    public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

        int secs;
        double speed;

        // TODO - START

        secs = gpspoint2.getTime() - gpspoint1.getTime();

        speed = distance(gpspoint1, gpspoint2)/secs;

        speed *= 3.6;

        return speed;
        // TODO - SLUTT

    }

    public static String formatTime(int secs) {

        String timestr;
        String TIMESEP = ":";

        // TODO - START

        int hr = (int) secs /3600;
        int remainder = (int) secs - hr*3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        secs = remainder;



        timestr = "  " + String.format("%02d", hr) + TIMESEP + String.format("%02d", mins)
                + TIMESEP + String.format("%02d", secs);

        // TODO - SLUTT
        return timestr;

    }

    private static int TEXTWIDTH = 10;

    public static String formatDouble(double d) {

        String str;

        // TODO - START

        // Bug in locale and therefore must use locale.ENGLISH to get dot instead of comma
        str = String.format(Locale.ENGLISH,"%10.2f", d);


        return str;
        // TODO - SLUTT

    }
}
