package org.strukdat;

import org.strukdat.graph.Graph;
import org.strukdat.queue.Queue;
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
		Graph peta = new Graph();
		Scanner str = new Scanner(System.in);
		Scanner itg = new Scanner(System.in);
		String nama, asal, tujuan;
		
		do {
			switch (Menu.utama()) {
				// Tambah Antrian
				case 1 -> {
					System.out.println("Masukkan nama anda > ");
					nama = str.nextLine();
					switch (Menu.transportasi()) {
						case 1 -> {
							Bus_antrian.enQueue(nama);
							
						}
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
						
						}
					}
				}
			}
		}while (Menu.confirm());
	}
}