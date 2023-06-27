package org.strukdat.queue;
import java.util.Scanner;
import org.strukdat.graph.*;

public class Queue {
	Node first;
	int revenue;
	int[] seat_available;
	int highestSeatNumber;
	public Queue(){
        first = null;
    }
	
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
	
	public void enQueue(String name, String tipeKendaraan){
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
		System.out.println("Berhasil menambah antrian pada tipe kendaraan " + tipeKendaraan);
	}
	
	public void deQueue(){
        if (first!= null){
			if (first.next == null) first = null;
            else first = first.next;
        }
    }
	
	public void cetak() {
		if (first != null) {
			Node pointer = first;
			cetakGaris();
			System.out.printf("┃ %-10s\t ┃ %-15s\t ┃ %-13s\t ┃ %-4s\t ┃ %-10s\t ┃ %-10s\t ┃ %-20s\t ┃\n", "NO.ANTRIAN", "NAMA", "TIPE KENDARAAN", "SEAT","KOTA ASAL", "KOTA TUJUAN", "HARGA TIKET");
			cetakGaris();
			while (pointer != null) {
				System.out.printf("┃ %-10d\t ┃ %-15s\t ┃ %-13s\t ┃ %-4d\t ┃ %-10s\t ┃ %-10s\t ┃ Rp.%10d,00-\t ┃\n", pointer.getNomerAntrian(), pointer.getNama(), pointer.getTipeKendaraan(), pointer.getSeat(), pointer.getKotaAsal(), pointer.getKotaTujuan(), pointer.getHargaTiket());
				pointer = pointer.next;
			}
			cetakGaris();
			System.out.printf("┃ TOTAL PENGHASILAN %78s Rp.%10d,00-\t ┃\n", "", getRevenue());
			cetakGaris();
		}else System.out.println("BELUM ADA DATA!");
	}
	public void cetakAntrian(){
		if(first != null){
			Node pointer = first;
            cetakGaris();
            System.out.printf("┃ %-10s\t ┃ %-15s\t ┃\n", "NO.ANTRIAN", "NAMA");
            cetakGaris();
			while(pointer != null) {
				System.out.printf("┃ %-10d\t ┃ %-15s\t ┃\n", pointer.getNomerAntrian(), pointer.getNama());
				pointer = pointer.next;
			}
			cetakGaris();
		}
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
		for (int i = 0; i < seat_available.length; i++) {
			if (seat_available[i] == seatNumber) {
				seat_available[i] = 0;
				System.out.printf("Seat %s berhasil dibeli\n", seatNumber);
			}
		}
	}
	
	public boolean cekSeat(int seatNumber){
		if ((seatNumber <= 0) || (seatNumber > highestSeatNumber)) {
			System.out.println("Seat tidak tersedia!");
            return false;
        }else if (seat_available[seatNumber - 1] == 0) {
			System.out.println("Seat sudah dibeli!");
			return false;
		}else {
			for (int i : seat_available) {
				if (i == seatNumber) return true;
			}
		}
		return false;
	}
	void cetakGaris(){
		String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		if (callingMethodName.equals("cetak")) {
			for (int i = 0; i < 122; i++) {
				System.out.print("━");
			}
			System.out.println();
		} else if (callingMethodName.equals("cetakAntrian")) {
			for (int i = 0; i < 38; i++) {
				System.out.print("━");
			}
			System.out.println();
		}
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
	public boolean beliTiket(Node manusia, String nama, String asal, String tujuan, String tipeKendaraan, Graph peta) {
		manusia.setKotaAsal(asal);
		manusia.setKotaTujuan(tujuan);
		manusia.setTipeKendaraan(tipeKendaraan);
		cetakSeat();
		System.out.print("Masukkan seat yang anda inginkan > ");
		Scanner s = new Scanner(System.in);
		int seat = s.nextInt();
		if (cekSeat(seat)) {
			manusia.setSeat(seat);
			removeSeat(seat);
			int hargaTiket = 0;
			switch (tipeKendaraan) {
				case "Bus" -> {
					hargaTiket = peta.getJarakBetween(peta.returnKota(asal), peta.returnKota(tujuan)) * 100;
					setRevenue(getRevenue() + hargaTiket);
				}
				case "Kereta" -> {
					hargaTiket = peta.getJarakBetween(peta.returnKota(asal), peta.returnKota(tujuan)) * 200;
					setRevenue(getRevenue() + hargaTiket);
				}
				case "Pesawat" -> {
					hargaTiket = peta.getJarakBetween(peta.returnKota(asal), peta.returnKota(tujuan)) * 500;
					setRevenue(getRevenue() + hargaTiket);
				}
			}
			enQueue_Beli(nama, asal, tujuan, tipeKendaraan, seat, hargaTiket);
			System.out.printf("Silahkan membayar seharga Rp%d,00-\n", hargaTiket);
			s.next();
			System.out.printf("Berhasil membeli tiket pada %s dari kota %s menuju ke kota %s dengan jarak %d KM\n", tipeKendaraan, asal, tujuan, peta.getJarakBetween(peta.returnKota(asal), peta.returnKota(tujuan)));
			return true;
		}
		return false;
	}
}
