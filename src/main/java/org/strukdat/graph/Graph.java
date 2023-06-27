package org.strukdat.graph;

public class Graph {
	Kota firstKota;
	int jumlahKota;
	
	public Graph() {
        firstKota = null;
        jumlahKota = 0;
    }
	
	public void initialize_data() {
		for (Kota x : DataKotaAwal.data){
			tambahKota(x.getNamaKota());
		}
		// dari seoul ke 4 kota yang terhubung
		tambahJalan("Seoul", 48,"Incheon");
		tambahJalan("Seoul", 18,"Goyang");
		tambahJalan("Seoul",  22, "Namyangju");
		tambahJalan("Seoul",  20, "Seongnam");
		
		// dari incheon ke 3 kota yang terhubung
		tambahJalan("Incheon",  48, "Seoul");
		tambahJalan("Incheon",  34, "Goyang");
		tambahJalan("Incheon",  46, "Seongnam");
		
		// dari goyang ke 3 kota yang terhubung
		tambahJalan("Goyang",  18, "Seoul");
		tambahJalan("Goyang",  38, "Namyangju");
		tambahJalan("Goyang",  34, "Incheon");
		
		// dari namyangju ke 3 kota yang tehubung
		tambahJalan("Namyangju",  22, "Seoul");
		tambahJalan("Namyangju",  38, "Goyang");
		tambahJalan("Namyangju",  29, "Seongnam");
		
		// dari seongnam ke 3 kota yang terhubung
		tambahJalan("Seongnam",  20, "Seoul");
		tambahJalan("Seongnam",  29, "Namyangju");
		tambahJalan("Seongnam",  46, "Incheon");
	}
	
	public void tambahKota(String namaKota) {
		Kota newKota = new Kota(namaKota);
		boolean containNum = namaKota.matches(".*\\d+.*");
		String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		if (!containNum) {
			if (returnKota(namaKota) == null) {
				if (firstKota == null) {
					firstKota = newKota;
				} else {
					Kota pointer = firstKota;
					while (pointer.nextKota != null) {
						pointer = pointer.nextKota;
					}
					pointer.nextKota = newKota;
					newKota.prevKota = pointer;
				}
				if (!callingMethodName.equals("initialize_data"))
					System.out.printf("Berhasil menambah kota %s!\n", namaKota);
				jumlahKota++;
				if (!callingMethodName.equals("initialize_data"))
					System.out.println("Jumlah kota sekarang = " + jumlahKota);
			} else System.out.println("Kota sudah ada!");
		}else System.out.println("Nama kota tidak boleh menggunakan angka!");
	}
	
	public void tambahJalan(String asal, int jarak, String tujuan){
		Kota origin = returnKota(asal);
		Kota destination = returnKota(tujuan);
		if (origin == null) System.out.println("Kota asal tidak ada! Silahkan tambah kota terlebih dahulu!");
		else if (destination == null) System.out.println("Kota tujuan tidak ada! Silahkan tambah kota terlebih dahulu!");
		else if (cekJalanBetween(origin, destination)) System.out.printf("Jalan dari kota %s ke kota %s sudah ada dengan jarak %d KM\n", asal, tujuan, getJarakBetween(origin, destination));
		else{
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
			if (!callingMethodName.equals("initialize_data")) System.out.printf("Berhasil menambah jalan baru dari kota %s ke kota %s dengan jarak %d KM\n", origin.getNamaKota(), destination.namaKota, getJarakBetween(origin, destination));
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
		if ((cekFirstJalan(asal)) && (cekFirstJalan(tujuan))){
			while(pointer!= null) {
				if (pointer.getTujuan() == tujuan) {
					return true;
				}
				pointer = pointer.nextJalan;
			}
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
	
	public void hapusKota(String namaKota){
		Kota pointer = firstKota;
		while(pointer!= null){
			if (pointer.getNamaKota().equals(namaKota)){
				Kota pointer2 = firstKota;
				while(pointer2 != null){
					if (cekJalanBetween(pointer2, pointer)) hapusJalan(pointer2.getNamaKota(), namaKota);
					pointer2 = pointer2.nextKota;
				}
				if (pointer == firstKota){
					firstKota = pointer.nextKota;
					pointer.nextKota.prevKota = null;
					pointer.nextKota = null;
				}
				else if (pointer.nextKota == null){
					pointer.prevKota.nextKota = null;
                    pointer.prevKota = null;
				}
				else{
					pointer.prevKota.nextKota = pointer.nextKota;
                    pointer.nextKota.prevKota = pointer.prevKota;
                    pointer.nextKota = null;
                    pointer.prevKota = null;
				}
				System.out.printf("Kota %s berhasil dihapus!\n", namaKota);
				break;
			}
			pointer = pointer.nextKota;
		}
		if (pointer == null) System.out.println("Kota tidak ditemukan!");
	}
	
	public void hapusJalan(String kotaAsal, String kotaTujuan){
		Kota origin = returnKota(kotaAsal);
        Kota destination = returnKota(kotaTujuan);
        if ((origin != null) && (destination != null)){
			if (cekFirstJalan(origin)){
				Jalan pointer = origin.firstJalan;
				while(pointer != null){
					if (pointer.tujuan == destination){
						String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
						if (pointer == origin.firstJalan) {
							if (origin.firstJalan.nextJalan == null) origin.firstJalan = null;
							else {
								origin.firstJalan = pointer.nextJalan;
								pointer.nextJalan.prevJalan= null;
								pointer.nextJalan = null;
							}
						} else if (pointer.nextJalan == null) {
							pointer.prevJalan.nextJalan = null;
							pointer.prevJalan = null;
						}else{
							pointer.prevJalan.nextJalan = pointer.nextJalan;
							pointer.nextJalan.prevJalan = pointer.prevJalan;
							pointer.nextJalan = null;
							pointer.prevJalan = null;
						}
						pointer.tujuan = null;
						if (!callingMethodName.equals("hapusKota")) System.out.printf("Jalan dari kota %s ke kota %s berhasil dihapus\n", kotaAsal, kotaTujuan);
						break;
					}
                    pointer = pointer.nextJalan;
                }
				if (pointer == null) System.out.println("Tidak ada jalan dari kota %s menuju ke kota ");
			}else System.out.printf("Kota %s belum memiliki jalan\n", kotaAsal);
		}else System.out.println("Kota tidak ada / belum ditambah!");
	}
	
	public void cetakKota(){
		Kota pointer = firstKota;
		int x = 0;
		cetakGaris(25);
		System.out.printf("┃ %3s\t ┃ %8s\t ┃\n", "NO", "NAMA KOTA");
		cetakGaris(25);
		while(pointer != null){
			x++;
			System.out.printf("┃ %3d\t ┃ %-9s\t ┃\n", x, pointer.getNamaKota());
			pointer = pointer.nextKota;
		}
		cetakGaris(25);
	}
	void cetakGaris(int jumlahGaris){
		for (int i = 0; i <= jumlahGaris; i++) {
			System.out.print("━");
		}
		System.out.println();
	}
	
	public void cariKota(String namaKota){
		Kota origin = returnKota(namaKota);
		if (origin == null) System.out.println("Kota tidak ada!");
		else {
			System.out.println("Kota " + namaKota);
			if (!cekFirstJalan(origin)) System.out.printf("Belum memiliki jalur yang terhubung dari kota %s\n", namaKota);
			else {
				Kota pointer = firstKota;
				while (pointer != null) {
					if (cekJalanBetween(origin, pointer)) {
						System.out.printf("Memiliki jalur ke kota %s dengan jarak %d KM\n", pointer.getNamaKota(), getJarakBetween(origin, pointer));
					}
					pointer = pointer.nextKota;
				}
			}
		}
	}
	
	public void cetakJalan(){
		Kota pointer = firstKota;
		while (pointer != null) {
			System.out.println("KOTA " + pointer.getNamaKota());
			if (cekFirstJalan(pointer)) {
				cetakGaris(29);
				System.out.printf("┃ %-7s\t ┃ %-9s\t ┃\n", "KE KOTA", "DENGAN JARAK");
				cetakGaris(29);
				Kota pointer2 = firstKota;
				while (pointer2 != null) {
					if (cekJalanBetween(pointer, pointer2))
						System.out.printf("┃ %-7s\t ┃ %7d KM\t ┃\n", pointer2.getNamaKota(), getJarakBetween(pointer, pointer2));
					pointer2 = pointer2.nextKota;
				}
				cetakGaris(29);
				pointer = pointer.nextKota;
			}
			else {
				System.out.println("Belum ada jalan yang terhubung dari kota tersebut!");
				break;
				
			}
		}
	}
	
	public void cetakJalanKe(String namaKota){
		Kota origin = returnKota(namaKota);
        if (origin == null) System.out.println("Kota tidak ada!");
		else {
			System.out.println("Terdapat jalur ke kota " + namaKota);
			Kota pointer = firstKota;
			while (pointer!= null) {
				if (cekFirstJalan(pointer)){
					if (cekJalanBetween(pointer, origin)){
						System.out.printf("Dari kota \t%-10s\tdengan jarak\t %d KM\n", pointer.namaKota, getJarakBetween(pointer, origin));
					}
				}
				pointer = pointer.nextKota;
			}
		}
	}
}
