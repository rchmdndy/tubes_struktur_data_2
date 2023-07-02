package org.strukdat;

import org.strukdat.graph.Graph;
import org.strukdat.queue.Queue;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// Inisialisasi antrian
		Queue Bus_antrian = new Queue();
		Queue Kereta_antrian = new Queue();
		Queue Pesawat_antrian = new Queue();
		Queue Bus_beli = new Queue(16);
		Queue Kereta_beli = new Queue(10);
		Queue Pesawat_beli = new Queue(8);
		Scanner str = new Scanner(System.in);
		Scanner itg = new Scanner(System.in);
		String nama, asal, tujuan;
		int jarak;
		Graph peta = new Graph();
		peta.initialize_data();
		
		// Data awal antrian
		Bus_antrian.enQueue("Dandy1", "Bus");
		Bus_antrian.enQueue("Dandy2", "Bus");
		Bus_antrian.enQueue("Zakky1", "Bus");
		Bus_antrian.enQueue("Zakky2", "Bus");
		Bus_antrian.enQueue("Zakky3", "Bus");
		
		Kereta_antrian.enQueue("Dandy1", "Kereta");
		Kereta_antrian.enQueue("Dandy2", "Kereta");
		Kereta_antrian.enQueue("Zakky1", "Kereta");
		Kereta_antrian.enQueue("Zakky2", "Kereta");
		Kereta_antrian.enQueue("Zakky3", "Kereta");
		
		Pesawat_antrian.enQueue("Dandy1", "Pesawat");
		Pesawat_antrian.enQueue("Dandy2", "Pesawat");
		Pesawat_antrian.enQueue("Zakky1", "Pesawat");
		Pesawat_antrian.enQueue("Zakky2", "Pesawat");
		Pesawat_antrian.enQueue("Zakky3", "Pesawat");
		
		do {
			switch (utama()) {
				// Tambah Antrian
				case 1 -> {
					System.out.print("Masukkan nama anda > ");
					nama = str.nextLine();
					switch (transportasi()) {
						case 1 -> Bus_antrian.enQueue(nama, "Bus");
						case 2 -> Kereta_antrian.enQueue(nama, "Kereta");
						case 3 -> Pesawat_antrian.enQueue(nama, "Pesawat");
					}
				}
				// Lihat antrian
				case 2 -> {
					switch (transportasi()) {
						case 1 -> {
							System.out.println("ANTRIAN BUS");
							Bus_antrian.cetakAntrian();
						}
						case 2 -> {
							System.out.println("ANTRIAN KERETA");
							Kereta_antrian.cetakAntrian();
						}
						case 3 -> {
							System.out.println("ANTRIAN PESAWAT");
							Pesawat_antrian.cetakAntrian();
						}
					}
				}
				// Kelola Kota
				case 3 -> {
					switch (pilihan_kota()){
						// Tambah Kota
						case 1 -> {
							System.out.print("Masukkan nama kota > ");
							peta.tambahKota(str.nextLine());
						}
						// Hapus Kota
						case 2 -> {
							System.out.print("Masukkan nama kota yang ingin dihapus > ");
							peta.hapusKota(str.nextLine());
						}
						// Lihat Kota
						case 3 -> peta.cetakKota();
					}
				}
				// Cari Kota
				case 4 ->{
					System.out.print("Masukkan nama kota > ");
					peta.cariKota(str.nextLine());
				}
				// Kelola Jalan
				case 5 -> {
				 switch (pilihan_jalan()){
					 // Tambah Jalan
                     case 1 -> {
						 System.out.print("Dari kota ? > ");
						 asal = str.nextLine();
						 System.out.print("Ke kota ? > ");
						 tujuan = str.nextLine();
						 System.out.print("Dengan jarak berapa KM ? > ");
						  do {
							 try {
								 jarak = itg.nextInt();
								 peta.tambahJalan(asal, jarak, tujuan);
								 break;
							 } catch (InputMismatchException e) {
								 System.out.println("Diperlukan angka desimal!");
								 itg.next();
							 }
						 }while (true);
					 }
					 case 2 -> {
						 System.out.print("Dari kota ? > ");
						 asal = str.nextLine();
						 System.out.print("Mengarah ke kota ? > ");
						 tujuan = str.nextLine();
						 peta.hapusJalan(asal, tujuan);
					 }
					 case 3 -> peta.cetakJalan();
				 }
				}
				// Cari Jalan
				case 6 -> {
					System.out.print("Ke kota ? > ");
					peta.cetakJalanKe(str.nextLine());
				}
				// Beli Tiket
				case 7 -> {
					System.out.print("Masukkan kota asal > ");
					String asalB = str.nextLine();
					if (peta.returnKota(asalB) != null) {
						peta.cariKota(asalB);
						System.out.print("Masukkan kota tujuan > ");
						String tujuanB = str.nextLine();
						if (peta.returnKota(tujuanB) != null) {
							if (peta.cekFirstJalan(peta.returnKota(asalB))) {
								if (peta.cekJalanBetween(peta.returnKota(asalB), peta.returnKota(tujuanB))) {
									switch (transportasi()) {
										case 1 -> {
											if (Bus_antrian.getFirst() != null) {
												if (Bus_beli.beliTiket(Bus_antrian.getFirst(), Bus_antrian.getFirst().getNama(), asalB, tujuanB, "Bus", peta)) Bus_antrian.deQueue();
											} else System.out.println("Belum ada antrian dari Bus");
										}
										case 2 -> {
											if (Kereta_antrian.getFirst() != null) {
												if(Kereta_beli.beliTiket(Kereta_antrian.getFirst(), Kereta_antrian.getFirst().getNama(), asalB, tujuanB, "Kereta", peta)) Kereta_antrian.deQueue();
											} else System.out.println("Belum ada antrian dari Kereta");
										}
										case 3 -> {
											if (Pesawat_antrian.getFirst() != null) {
												if (Pesawat_beli.beliTiket(Pesawat_antrian.getFirst(), Pesawat_antrian.getFirst().getNama(), asalB, tujuanB, "Pesawat", peta)) Pesawat_antrian.deQueue();
											} else System.out.println("Belum ada antrian dari Pesawat");
										}
										default -> System.out.println("Pilihan invalid");
									}
								} else System.out.printf("Belum ada jalur yang mengarah dari kota %s ke kota %s\n", asalB, tujuanB);
							} else System.out.println("Belum ada jalan dari kota " + asalB);
						}else System.out.println("Tidak ada kota" + tujuanB);
					}else System.out.println("Tidak ada kota " + asalB);
				}
				// Lihat penjualan Tiket
				case 8 -> {
					switch (transportasi()){
						case 1 -> Bus_beli.cetak();
                        case 2 -> Kereta_beli.cetak();
                        case 3 -> Pesawat_beli.cetak();
					}
				}default -> System.out.println("Pilihan invalid");
			}
		}while (confirm());
	}
	static int utama(){
		Scanner s = new Scanner(System.in);
		int pilihan;
		System.out.println("MENU");
		System.out.println("1. Tambah Antrian");
		System.out.println("2. Lihat Antrian");
		System.out.println("3. Kelola Kota");
		System.out.println("4. Cari Kota");
		System.out.println("5. Kelola Jalan");
		System.out.println("6. Cari Jalan");
		System.out.println("7. Beli Tiket");
		System.out.println("8. Lihat tiket terjual");
		System.out.print("Silahkan pilih pilihan anda > ");
		try {
			pilihan = s.nextInt();
			return pilihan;
		}catch (InputMismatchException e){
			System.out.println("Tipe data tidak sesuai!");
		}
		return 0;
	}
	static int transportasi(){
		Scanner s = new Scanner(System.in);
		System.out.println("PILIHAN TRANSPORTASI");
		System.out.println("1. BUS");
		System.out.println("2. KERETA");
		System.out.println("3. PESAWAT");
		System.out.print("Silahkan pilih pilihan anda > ");
		return s.nextInt();
	}
	static int pilihan_kota(){
		Scanner s = new Scanner(System.in);
		System.out.println("Pilihan Menu Kota");
		System.out.println("1. Tambah Kota");
		System.out.println("2. Hapus Kota");
		System.out.println("3. Lihat Kota");
		System.out.print("Silahkan pilih pilihan anda > ");
		return s.nextInt();
	}
	static int pilihan_jalan(){
		Scanner s = new Scanner(System.in);
		System.out.println("Pilihan Menu Jalan");
		System.out.println("1. Tambah Jalan");
		System.out.println("2. Hapus Jalan");
		System.out.println("3. Lihat Jalan");
		System.out.print("Silahkan pilih pilihan anda > ");
		return s.nextInt();
	}
	
	static boolean confirm(){
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.print("Input lagi ? [y/n] > ");
			String pilihan = s.nextLine();
			if (pilihan.equalsIgnoreCase("y")) {
				return true;
			} else if (pilihan.equalsIgnoreCase("n")) {
				return false;
			}else System.out.println("Pilihan tidak sesuai!");
		}
	}
}