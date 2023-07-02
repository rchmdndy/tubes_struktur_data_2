package org.strukdat;
import org.strukdat.graph.*;
public class TestClass {
	public static void main(String[] args) {
		Graph g = new Graph();
		g.initialize_data();
		g.tambahKota("Pyongyang");
		g.tambahJalan("Seoul", 54, "Pyongyang");
		g.cetakJalan();
		System.out.println();
	}
}
