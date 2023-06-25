package org.strukdat.graph;

public class Jalan {
	int jarak;
	Jalan nextJalan;
	Jalan prevJalan;
	Kota tujuan;
	
	public String getNamaKotaTujuan() {return tujuan.getNamaKota();}
	public int getJarak() {return jarak;}
	
	public Kota getTujuan() {return tujuan;}
	
	public Jalan(Kota tujuan, int jarak, Jalan nextJalan, Jalan prevJalan){
	 this.tujuan = tujuan;
     this.jarak = jarak;
     this.nextJalan = nextJalan;
     this.prevJalan = prevJalan;
	}
}
