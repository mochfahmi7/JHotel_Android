package jhotel.com.jhotel_android_mochfahmi;

/**
 * Class ini merupakan class Room, yaitu untuk mengatur sisi client pada bagian detail pesanan/pesanan selesai.
 * version 15/05/2018
 */
public class Room {
    private String tipeKamar;
    private String roomNumber;
    private String statusKamar;
    protected double dailyTariff;

    public Room(String roomNumber, String statusKamar, double dailyTariff, String tipeKamar) {
        this.roomNumber = roomNumber;
        this.statusKamar = statusKamar;
        this.dailyTariff = dailyTariff;
        this.tipeKamar = tipeKamar;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber() {
        this.roomNumber = roomNumber;
    }

    public String getStatusKamar() {
        return statusKamar;
    }

    public void setStatusKamar() {
        this.statusKamar = statusKamar;
    }

    public double getDailyTariff() {
        return dailyTariff;
    }

    public void setDailyTariff() {
        this.dailyTariff = dailyTariff;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar() {
        this.tipeKamar = tipeKamar;
    }

    public String toString() {
        return "\nNomor Kamar      : " + getRoomNumber() +
                "\nTipe Kamar           : " + getTipeKamar() +
                "\nHarga(/Malam)   : Rp" + getDailyTariff() +
                "\nStatus Kamar       : " + getStatusKamar();


    }

}
