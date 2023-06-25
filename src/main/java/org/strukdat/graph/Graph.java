package org.strukdat.graph;

public class Graph {
	Kota firstKota;
	int jumlahKota;
	
	public Graph() {
        firstKota = null;
        jumlahKota = 0;
    }
	
	public void tambahKota(String namaKota){
		Kota newKota = new Kota(namaKota);
		String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		if (returnKota(namaKota) == null){
			if (firstKota == null) firstKota = newKota;
			else{
				Kota pointer = firstKota;
				while(pointer.nextKota != null){
                    pointer = pointer.nextKota;
                }
				pointer.nextKota = newKota;
				newKota.prevKota = pointer;
				if (!callingMethodName.equals("initiate_data")) {
					System.out.printf("Berhasil menambah kota %s!\n", namaKota);
				}
			}
			jumlahKota++;
			if (!callingMethodName.equals("initiate_data")) System.out.println("Jumlah kota sekarang = " + jumlahKota);
		}else System.out.println("Kota sudah ada!");
	}
	
	public void tambahJalan(Kota origin, int jarak, Kota destination){
		if (origin == null) System.out.println("Kota asal tidak ada! Silahkan tambah kota terlebih dahulu!");
		else if (destination == null) System.out.println("Kota tujuan tidak ada! Silahkan tambah kota terlebih dahulu!");
		else {
			String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
			Jalan jalanBaru = new Jalan(destination, jarak, null, null);
			if (!cekFirstJalan(origin)){
				origin.firstJalan = jalanBaru;
			}else {
				Jalan pointer = origin.firstJalan;
				while(pointer.nextJalan!= null){
                    pointer = pointer.nextJalan;
                }
				pointer.nextJalan = jalanBaru;
				jalanBaru.prevJalan = pointer;
			}
			if (!callingMethodName.equals("initiate_data")) System.out.printf("Berhasil menambah jalan baru dari kota %s ke kota %s dengan jarak %d\n", origin.getNamaKota(), destination.namaKota, getJarakBetween(origin, destination));
		}
	}
	public Kota returnKota(String namaKota){
		Kota pointer = firstKota;
		while(pointer!= null){
			if (pointer.getNamaKota().equals(namaKota)){
			 return pointer;
			}
			pointer = pointer.nextKota;
		}
		return null;
	}
	
	public boolean cekJalanBetween(Kota asal, Kota tujuan){
		Jalan pointer = asal.firstJalan;
		while(pointer!= null){
			if (pointer.getTujuan() == tujuan){
				return true;
			}
			pointer = pointer.nextJalan;
		}
		return false;
	}
	
	public boolean cekFirstJalan(Kota namaKota){
		return namaKota.getFirstJalan() != null;
	}
	
	public int getJumlahKota(){return jumlahKota;}
	public int getJarakBetween(Kota asal, Kota tujuan){
		Jalan pointer = asal.firstJalan;
		while(pointer!= null){
		if (pointer.getNamaKotaTujuan().equals(tujuan.getNamaKota())){
                return pointer.jarak;
            }
            pointer = pointer.nextJalan;
		}
		return 0;
	}
}
