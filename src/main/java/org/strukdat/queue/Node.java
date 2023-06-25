package org.strukdat.queue;

public class Node {
	Manusia manusia;
	Node next;
	/**KUMPULAN GETTER AND SETTER ATTRIBUTE MANUSIA*/
	public String getNama(){return manusia.nama;}
	public int getNomerAntrian(){return manusia.nomerAntrian;}
	public void setNomerAntrian(int nomer){manusia.nomerAntrian = nomer;}
	public int getSeat(){return manusia.seat;}
	public void setSeat(int seatNumber){manusia.seat = seatNumber;}
	public String getTipeKendaraan(){return manusia.tipeKendaraan;}
	public void setTipeKendaraan(String tipe){manusia.tipeKendaraan = tipe;}
	public String getKotaAsal(){return manusia.kotaAsal;}
	public void setKotaAsal(String kotaAsal){manusia.kotaAsal = kotaAsal;}
	public String getKotaTujuan(){return manusia.kotaTujuan;}
	public void setKotaTujuan(String kotaTujuan){manusia.kotaTujuan = kotaTujuan;}
	
	public int getHargaTiket(){return manusia.hargaTiket;}
	public void setHargaTiket(int hargaTiket){manusia.hargaTiket = hargaTiket;}
	public Node(String nama){
		manusia = new Manusia(nama);
		next = null;
	}
	public Node(String nama, String kotaAsal, String kotaTujuan, String tipeKendaraan, int seat, int hargaTiket){
		manusia = new Manusia(nama);
        manusia.seat = seat;
        manusia.tipeKendaraan = tipeKendaraan;
        manusia.kotaAsal = kotaAsal;
        manusia.kotaTujuan = kotaTujuan;
        manusia.hargaTiket = hargaTiket;
        next = null;
	}
}
