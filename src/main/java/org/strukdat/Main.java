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
			switch (Menu.utama()) {
				// Tambah Antrian
				case 1 -> {
					System.out.print("Masukkan nama anda > ");
					nama = str.nextLine();
					switch (Menu.transportasi()) {
						case 1 -> Bus_antrian.enQueue(nama, "Bus");
						case 2 -> Kereta_antrian.enQueue(nama, "Kereta");
						case 3 -> Pesawat_antrian.enQueue(nama, "Pesawat");
					}
				}
				// Lihat antrian
				case 2 -> {
					switch (Menu.transportasi()) {
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
					switch (Menu.pilihan_kota()){
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
				 switch (Menu.pilihan_jalan()){
					 // Tambah Jalan
                     case 1 -> {
						 System.out.print("Dari kota ? > ");
						 asal = str.nextLine();
						 System.out.print("Ke kota ? > ");
						 tujuan = str.nextLine();
						 System.out.print("Dengan jarak berapa KM ? > ");
						 try {
							 int jarak = itg.nextInt();
							 peta.tambahJalan(asal, jarak, tujuan);
						 }catch (InputMismatchException e){
							 System.out.println("Diperlukan angka desimal!");
						 }
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
				// Cetak Jalan
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
									switch (Menu.transportasi()) {
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
					switch (Menu.transportasi()){
						case 1 -> Bus_beli.cetak();
                        case 2 -> Kereta_beli.cetak();
                        case 3 -> Pesawat_beli.cetak();
					}
				}default -> System.out.println("Pilihan invalid");
			}
		}while (Menu.confirm());
	}
}