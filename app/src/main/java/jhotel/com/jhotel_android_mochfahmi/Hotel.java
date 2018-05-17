package jhotel.com.jhotel_android_mochfahmi;

/**
 * Class ini merupakan class Hotel, yaitu class untuk mengatur sisi client bagian hotel.
 * version 15/05/2018
 */
public class Hotel {

    private String nama;
    private Lokasi lokasi;
    private int bintang;
    private int id;

    /**
     * Method ini merupakan method Hotel, yaitu merupakan Constructor.
     *
     * @param bintang
     * @param id
     * @param lokasi
     * @param nama
     */
    public Hotel(String nama, Lokasi lokasi, int bintang, int id) {
        this.nama = nama;//instance variable
        this.lokasi = lokasi;//instance variable
        this.bintang = bintang;//instance variable
        this.id = id;
    }

    /**
     * ini merupakan method getID, yang merupakan Accessor.
     *
     * @return id
     */
    public int getID() {
        return id;
    }

    /**
     * ini merupakan method getBintang, yang merupakan Accessor.
     *
     * @return bintang
     */
    public int getBintang() {
        return bintang;
    }

    /**
     * ini merupakan method getNama, yang merupakan Accessor.
     *
     * @return nama.
     */
    public String getNama() {
        return nama;
    }

    /**
     * ini merupakan method getLokasi, yang merupakan Accessor.
     *
     * @return lokasi.
     */
    public Lokasi getLokasi() {
        return lokasi;
    }

    /**
     * ini merupakan method setNama, yang merupakan Mutator.
     *
     * @param nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * ini merupakan method setLokasi, yang merupakan Mutator.
     */
    public void setLokasi(Lokasi lokasi) {
        this.lokasi = lokasi;
    }

    /**
     * ini merupakan method setBintang, yang merupakan Mutator.
     */
    public void setBintang(int bintang) {
        this.bintang = bintang;
    }

    /**
     * ini merupakan method sertID, yang merupakan Mutator.
     */
    public void setID(int id) {
        this.id = id;
    }

}
