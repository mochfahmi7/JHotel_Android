package jhotel.com.jhotel_android_mochfahmi;

/**
 * Class ini merupakan class Lokasi, yaitu class untuk mengatur sisi client bagian lokasi hotel.
 * version 15/05/2018
 */
public class Lokasi {


    private double x_coord;
    private double y_coord;
    private String deskripsi;

    /**
     * Method ini merupakan method Hotel, yaitu merupakan Constructor.
     *
     * @param x_coord
     * @param deskripsi
     * @param y_coord
     */
    public Lokasi(double x_coord, double y_coord, String deskripsi) {
        this.x_coord = x_coord;
        this.y_coord = y_coord;
        this.deskripsi = deskripsi;
    }

    public double getX_coord() {
        return x_coord;
    }

    public double getY_coord() {
        return y_coord;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setX_coord() {
        this.x_coord = x_coord;
    }

    public void setY_coord() {
        this.y_coord = y_coord;
    }

    public void setDeskripsi() {
        this.deskripsi = deskripsi;
    }
}
