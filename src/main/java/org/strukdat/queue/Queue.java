package org.strukdat.queue;
import java.util.Scanner;

import org.strukdat.Main;

public class Queue {
	Node first;
	int revenue;
	int[] seat_available;
	int highestSeatNumber;
	public Queue(){
        first = null;
    }
	Main m = new Main();
	
	public Queue(int seat_available){
        first = null;
		this.seat_available = new int[seat_available];
		for (int i = 0; i < seat_available; i++) {
			this.seat_available[i] = i + 1;
		}
		highestSeatNumber = seat_available;
    }
	
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public Node getFirst() {return first;}
	
	public int getRevenue() {
		return revenue;
	}
	
	public void enQueue(String name){
		Node manusia = new Node(name);
		if (first != null){
			Node pointer = first;
			int x = 1;
			while (pointer.next != null){
				x++;
				pointer = pointer.next;
			}
			x++;
			pointer.next = manusia;
			manusia.setNomerAntrian(x);
		}else{
			first = manusia;
			manusia.setNomerAntrian(1);
		}
	}
	
	public void deQueue(){
        if (first!= null){
			if (first.next == null) first = null;
            else first = first.next;
        }
    }
	
	public void cetak(){
		Node pointer = first;
			cetakGaris();
			System.out.printf("┃ %-10s\t ┃ %-15s\t ┃ %-13s\t ┃ %-10s\t ┃ %-10s\t ┃ %-20s\t ┃\n", "NO.ANTRIAN", "NAMA", "TIPE KENDARAAN", "KOTA ASAL", "KOTA TUJUAN", "HARGA TIKET");
			cetakGaris();
			while(pointer != null) {
				System.out.printf("┃ %-10d\t ┃ %-15s\t ┃ %-13s\t ┃ %-10s\t ┃ %-10s\t ┃ Rp.%10d,00-\t ┃\n", pointer.getNomerAntrian(), pointer.getNama(), pointer.getTipeKendaraan(), pointer.getKotaAsal(), pointer.getKotaTujuan(), pointer.getHargaTiket());
				pointer = pointer.next;
			}
			cetakGaris();
			System.out.printf("┃ TOTAL PENGHASILAN %70s Rp.%10d,00-\t ┃\n", "", getRevenue());
			cetakGaris();
	}
	
	public void cetakSeat(){
		for (int i = 0; i < seat_available.length; i++) {
			System.out.printf("┃ %-2s ┃	", seat_available[i]);
			if ((i + 1) < seat_available.length) {
				i++;
				System.out.printf("┃ %-2s ┃\n", seat_available[i]);
			}
		}
	}
	
	public void removeSeat(int seatNumber) {
		if ((seatNumber <= 0) || (seatNumber > highestSeatNumber)) System.out.println("Maaf, seat tidak tersedia");
		else {
			for (int i = 0; i < seat_available.length; i++) {
				if (seat_available[i] == seatNumber) {
					seat_available[i] = 0;
					System.out.printf("Seat %s berhasil dibeli\n", seatNumber);
					break;
				}
				else if (seat_available[seatNumber - 1] == 0) {
					System.out.println("Seat sudah dibeli!");
					break;
				}
			}
		}
	}
	void cetakGaris(){
		for (int i = 0; i < 115; i++) {
			System.out.print("━");
		}
		System.out.println();
	}
	public void enQueue_Beli(String name, String asal, String tujuan, String tipeKendaraan, int seat,int hargaTiket){
		Node manusia = new Node(name, asal, tujuan, tipeKendaraan, seat, hargaTiket);
		if (first != null){
			Node pointer = first;
			int x = 1;
			while (pointer.next != null){
				x++;
				pointer = pointer.next;
			}
			x++;
			pointer.next = manusia;
			manusia.setNomerAntrian(x);
		}else{
			first = manusia;
			manusia.setNomerAntrian(1);
		}
	}
	public void beliTiket(Node manusia, String nama, String asal, String tujuan, String tipeKendaraan){
		manusia.setKotaAsal(asal);
		manusia.setKotaTujuan(tujuan);
		manusia.setTipeKendaraan(tipeKendaraan);
		cetakSeat();
		System.out.print("Masukkan seat yang anda inginkan > ");
		Scanner s = new Scanner(System.in);
		int seat = s.nextInt();
		manusia.setSeat(seat);
		removeSeat(seat);
		int hargaTiket = 0;
		switch (tipeKendaraan){
			case "Bus" -> {
				hargaTiket = m.peta.getJarakBetween(m.peta.returnKota(asal), m.peta.returnKota(tujuan)) * 100;
				setRevenue(getRevenue() + hargaTiket);
			}
			case "Kereta" -> {
				hargaTiket = m.peta.getJarakBetween(m.peta.returnKota(asal), m.peta.returnKota(tujuan)) * 200;
				setRevenue(getRevenue() + hargaTiket);
			}
			case "Pesawat" -> {
				hargaTiket = m.peta.getJarakBetween(m.peta.returnKota(asal), m.peta.returnKota(tujuan)) * 500;
				setRevenue(getRevenue() + hargaTiket);
			}
		}
		enQueue_Beli(nama, asal, tujuan, tipeKendaraan, seat, hargaTiket);
	}
}
