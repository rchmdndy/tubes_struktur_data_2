package org.strukdat.queue;

public class Manusia {
	protected String nama;
	protected  int nomerAntrian;
	protected int seat;
	protected String tipeKendaraan;
	protected String kotaAsal;
	protected String kotaTujuan;
	protected int hargaTiket;
	public Manusia(String nama){
		this.nama = nama;
		this.nomerAntrian = 0;
		this.seat = 0;
		this.tipeKendaraan = null;
		this.kotaAsal = null;
		this.kotaTujuan = null;
	}
	
}
