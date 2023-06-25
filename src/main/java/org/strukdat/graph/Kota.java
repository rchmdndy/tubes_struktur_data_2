package org.strukdat.graph;

public class Kota {
	String namaKota;
	
	public String getNamaKota() {return namaKota;}
	
	public Jalan getFirstJalan() {return firstJalan;}
	
	Kota nextKota;
	Kota prevKota;
	Jalan firstJalan;
	public Kota(String namaKota){
		this.namaKota = namaKota;
		this.nextKota = null;
		this.prevKota = null;
		firstJalan = null;
	}
}
