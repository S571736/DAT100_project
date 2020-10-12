package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

    private GPSPoint[] gpspoints;
    protected int antall = 0;

    public GPSData(int n) {

        // TODO - START

        this.gpspoints = new GPSPoint[n];
        this.antall = 0;


        // TODO - SLUTT
    }

    public GPSPoint[] getGPSPoints() {
        return this.gpspoints;
    }

    protected boolean insertGPS(GPSPoint gpspoint) {

        boolean inserted = false;

        // TODO - START

        if (antall < gpspoints.length) {
            gpspoints[antall] = gpspoint;
            antall++;
            inserted = true;
        }

        return inserted;
        // TODO - SLUTT
    }

    public boolean insert(String time, String latitude, String longitude, String elevation) {

        GPSPoint gpspoint;
        boolean inserted;
        // TODO - START

        gpspoint = GPSDataConverter.convert(time, latitude, longitude, elevation);
        inserted = insertGPS(gpspoint);

        return inserted;
        // TODO - SLUTT

    }

    public void print() {

        System.out.println("====== Konvertert GPS Data - START ======");

        // TODO - START

        for (int i = 0; i < antall; i++) {
            System.out.println(gpspoints[i].toString());
        }

        // TODO - SLUTT

        System.out.println("====== Konvertert GPS Data - SLUTT ======");

    }
}
