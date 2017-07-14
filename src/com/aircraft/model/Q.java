package com.aircraft.model;

public class Q {
	
	
	private Node<AirCraft> first;
	private Node<AirCraft> last;
	private int size = 0;
	
	public Q() {
		
	}
	
	
	
	
	
	private static class Node<AirCraft> {
		AirCraft item;
        Node<AirCraft> next;
        Node<AirCraft> prev;

        Node(Node<AirCraft> prev, AirCraft element, Node<AirCraft> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
