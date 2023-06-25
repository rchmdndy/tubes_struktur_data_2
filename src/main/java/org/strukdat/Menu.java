package org.strukdat;

import java.util.Scanner;

public class Menu {
	static int utama(){
		Scanner s = new Scanner(System.in);
		System.out.println("MENU");
		System.out.println("1. Tambah Antrian");
		System.out.println("2. Lihat Antrian");
		System.out.println("3. Kelola Kota");
		System.out.println("4. Cari Kota");
		System.out.println("5. Kelola Jalan");
		System.out.println("6. Cari Jalan");
		System.out.println("7. Beli Tiket");
		System.out.println("8. Lihat tiket terjual");
		System.out.print("Silihkan pilih pilihan anda > ");
		return s.nextInt();
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
		System.out.print("Silihkan pilih pilihan anda > ");
		return s.nextInt();
	}
	static int kota(){
		Scanner s = new Scanner(System.in);
		System.out.println("Pilihan Kota");
		System.out.println("1. Seoul");
		System.out.println("2. Incheon");
		System.out.println("3. Goyang");
		System.out.println("4. Namyangju");
		System.out.println("5. Seongnamj");
		System.out.print("Silahkan pilih pilihan anda > ");
		return s.nextInt();
	}
	
	static boolean confirm(){
		Scanner s = new Scanner(System.in);
		System.out.print("Input lagi ? [y/n] > ");
		String pilihan = s.nextLine();
		if(pilihan.equalsIgnoreCase("y")){
			return true;
		}
		return false;
	}
}
