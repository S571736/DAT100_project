package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.*;

public class ShowRoute extends EasyGraphics {

    private static int MARGIN = 50;
    private static int MAPXSIZE = 800;
    private static int MAPYSIZE = 800;

    private GPSPoint[] gpspoints;
    private GPSComputer gpscomputer;

    public ShowRoute() {

        String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
        gpscomputer = new GPSComputer(filename);

        gpspoints = gpscomputer.getGPSPoints();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void run() {

        makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

        showRouteMap(MARGIN + MAPYSIZE);

        showStatistics();
    }

    // antall x-pixels per lengdegrad
    public double xstep() {

        double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
        double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

        double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

        return xstep;
    }

    // antall y-pixels per breddegrad
    public double ystep() {

        double ystep;

        // TODO - START

        double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
        double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

        ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

        // TODO - SLUTT
        return ystep;

    }

    public void showRouteMap(int ybase) {

        // TODO - START

        int d = 3;
        int x, y, xs = 0, ys = 0;
        setColor(0, 255, 0);

        double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
        double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));


        for (int i = 0; i < gpspoints.length; i++) {
            x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlong) * xstep());
            y = (int) (ybase - (gpspoints[i].getLatitude() - minlat) * ystep());

            if (i == 0) {
                setColor(0, 255, 0);
            } else if (gpspoints[i].getElevation() >= gpspoints[i - 1].getElevation() && i != gpspoints.length) {
                setColor(0, 255, 0);
            } else if (gpspoints[i].getElevation() < gpspoints[i - 1].getElevation() && i != gpspoints.length) {
                setColor(255, 0, 0);
            }

            if (i == 0) {
                xs = x;
                ys = y;
            }

            drawLine(x, y, xs, ys);
            xs = x;
            ys = y;
            fillCircle(x, y, d);

        }

        // TODO - SLUTT
    }

    public void showStatistics() {

        int TEXTDISTANCE = 20;

        setColor(0, 0, 0);
        setFont("Courier", 12);

        // TODO - START

        drawString("==============================================", TEXTDISTANCE, 20);


        drawString(String.format("%-15s", "Total time") + ": " + GPSUtils.formatTime(gpscomputer.totalTime()), TEXTDISTANCE, 30);
        drawString(String.format("%-15s", "Total distance") + ": " + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km", TEXTDISTANCE, 40);
        drawString(String.format("%-15s", "Total elevation") + ": " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", TEXTDISTANCE, 50);
        drawString(String.format("%-15s", "Max speed") + ": " + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + " km/t", TEXTDISTANCE, 60);
        drawString(String.format("%-15s", "Average speed") + ": " + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + " km/t", TEXTDISTANCE, 70);
        drawString(String.format("%-15s", "Energy") + ": " + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal", TEXTDISTANCE, 80);

        drawString("==============================================", TEXTDISTANCE, 90);
        // TODO - SLUTT;
    }

}
