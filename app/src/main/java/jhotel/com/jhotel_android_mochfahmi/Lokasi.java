package jhotel.com.jhotel_android_mochfahmi;

public class Lokasi {


    private double x_coord;
    private double y_coord;
    private String deskripsi;

    public Lokasi(double x_coord, double y_coord, String deskripsi)
    {
        this.x_coord=x_coord;
        this.y_coord=y_coord;
        this.deskripsi=deskripsi;
    }

    public double getX_coord(){
        return x_coord;
    }

    public double getY_coord(){
        return y_coord;
    }
    public String getDeskripsi(){
        return deskripsi;
    }
    public void setX_coord(){
        this.x_coord=x_coord;
    }
    public void setY_coord(){
        this.y_coord=y_coord;
    }
    public void setDeskripsi(){
        this.deskripsi=deskripsi;
    }
}
