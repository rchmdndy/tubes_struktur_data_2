package org.strukdat.queue;

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
	
	public String getManusia(){
		return first.getNama();
	}
	public int getNoAntrian(){
		return first.getNomerAntrian();
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	
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
	
	public void setSeatNumber(String name, int seatNumber){
		Node pointer = first;
		while (pointer != null){
			if (pointer.getNama().equals(name)){
				pointer.setSeat(seatNumber);
                break;
			}
			pointer = pointer.next;
		}
		System.out.println("Data orang tidak ditemukan");
	}
	void cetakGaris(){
		for (int i = 0; i < 115; i++) {
			System.out.print("━");
		}
		System.out.println();
	}
}
