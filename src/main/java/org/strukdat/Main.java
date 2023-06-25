package org.strukdat;

import org.strukdat.graph.Graph;
import org.strukdat.queue.Queue;
import java.util.Scanner;

public class Main {
	public static Graph peta = new Graph();
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
		peta.initialize_data();
		do {
			switch (Menu.utama()) {
				// Tambah Antrian
				case 1 -> {
					System.out.print("Masukkan nama anda > ");
					nama = str.nextLine();
					switch (Menu.transportasi()) {
						case 1 -> Bus_antrian.enQueue(nama);
						case 2 -> Kereta_antrian.enQueue(nama);
						case 3 -> Pesawat_antrian.enQueue(nama);
					}
				}
				// Lihat antrian
				case 2 -> {
					switch (Menu.transportasi()) {
						case 1 -> {
							System.out.println("ANTRIAN BUS");
							Bus_antrian.cetak();
						}
						case 2 -> {
							System.out.println("ANTRIAN KERETA");
							Kereta_antrian.cetak();
						}
						case 3 -> {
							System.out.println("ANTRIAN PESAWAT");
							Pesawat_antrian.cetak();
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
						 int jarak = itg.nextInt();
						 peta.tambahJalan(asal, jarak, tujuan);
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
					peta.cariKota(asalB);
					System.out.print("Masukkan kota tujuan > ");
					String tujuanB = str.nextLine();
					if (peta.cekJalanBetween(peta.returnKota(asalB), peta.returnKota(tujuanB))){
						switch (Menu.transportasi()) {
							case 1 -> {
								Bus_beli.beliTiket(Bus_antrian.getFirst(), Bus_antrian.getFirst().getNama(), asalB, tujuanB, "Bus");
								Bus_antrian.deQueue();
							}
							case 2 -> {
                                Kereta_beli.beliTiket(Kereta_antrian.getFirst(), Kereta_antrian.getFirst().getNama(), asalB, tujuanB, "Kereta");
                                Kereta_antrian.deQueue();
                            }
							case 3 -> {
                                Pesawat_beli.beliTiket(Pesawat_antrian.getFirst(), Pesawat_antrian.getFirst().getNama(), asalB, tujuanB, "Pesawat");
                                Pesawat_antrian.deQueue();
                            }
							default -> System.out.println("Pilihan invalid");
						}
					}
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