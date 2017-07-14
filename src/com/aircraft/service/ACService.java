package com.aircraft.service;

import java.util.ArrayList;
import java.util.List;

import com.aircraft.model.AirCraft;
import com.aircraft.model.OrderedJSON;
import com.aircraft.utils.SIZE;
import com.aircraft.utils.TYPE;

public class ACService {
	private List<AirCraft> queue = new ArrayList<AirCraft>();
	private static int dq_counter = 0;
//	private PriorityQueue<AirCraft> q = new PriorityQueue<AirCraft>(new Comparator<AirCraft>() {
//		public int compare(AirCraft a, AirCraft b) {
//			if(currentHasPriority(a, b)) {
//				return 1;
//			}else {
//				return 0;
//			}
//		}
//	});

	public ACService() {

	}

	public void reboot() {
		queue.clear();
	}
	public int enqueue(AirCraft ac) {
		queue.add(ac);
		int index = queue.size();
		heapify(index - 1);
		// checkLeftSibling(index-1);
		return index;
	}

	// public AirCraft dequeue() {
	// if(queue == null || queue.size() <1) {
	// return null;
	// }
	// return queue.remove(0);
	// }
	public AirCraft dequeue() {
		if (queue == null || queue.size() < 1) {
			return null;
		}

		AirCraft ret = queue.get(0);
		AirCraft fst = queue.get(queue.size() - 1);
		queue.remove(0);
		queue.add(0, fst);
		queue.remove(queue.size() - 1);
		downHeapify(0);
		return ret;

	}

	private void downHeapify(int i) {
		while (left(i) < queue.size()) {
			int sib = childrenPriority(i);
			if (currentHasPriority(queue.get(sib), queue.get(i))) {
				exchangeAC(i, sib);
				i = sib;
			} else {
				break;
			}
		}
	}

	private int childrenPriority(int i) {
		int lef = left(i);
		int rig = right(i);
		if (rig >= queue.size()) {
			return lef;
		}

		if (currentHasPriority(queue.get(rig), queue.get(lef))) {
			return rig;
		}
		return lef;
	}

	private void heapify(int i) {
		if (i == dq_counter) {
			return;
		}
		while (i > dq_counter) {
			int parent_index = parent(i);
			AirCraft current = queue.get(i);
			AirCraft parent = queue.get(parent_index);
			if (currentHasPriority(current, parent)) {
				exchangeAC(i, parent_index);
				i = parent_index;
			} else {
				break;
			}
		}
	}

	private void exchangeAC(int i, int j) {
		AirCraft a = queue.get(i);
		AirCraft b = queue.get(j);
		queue.set(i, b);
		queue.set(j, a);
	}

//	private void checkLeftSibling(int right) {
//		if (right == 0) {
//			return;
//		}
//		int parent = parent(right);
//		int left = left(parent);
//		if (currentHasPriority(queue.get(right), queue.get(left))) {
//			exchangeAC(right, left);
//		}
//	}

	private boolean currentHasPriority(AirCraft current, AirCraft parent) {
		switch (current.getType()) {
		case PASSENGER:
			switch (parent.getType()) {
			case PASSENGER:
				switch (current.getSize()) {
				case LARGE:
					switch (parent.getSize()) {
					case SMALL:
						return true;
					case LARGE:
						if (parent.getId() < current.getId()) {
							return false;
						} else {
							return true;
						}
					}
				case SMALL:
					switch (parent.getSize()) {
					case SMALL:
						if (parent.getId() < current.getId()) {
							return false;
						} else {
							return true;
						}
					case LARGE:
						return false;
					}

				}
			case CARGO:
				return true;
			}
		case CARGO:
			switch (parent.getType()) {
			case PASSENGER:
				return false;
			case CARGO:
				switch (current.getSize()) {
				case LARGE:
					switch (parent.getSize()) {
					case SMALL:
						return true;
					case LARGE:
						if (parent.getId() < current.getId()) {
							return false;
						} else {
							return true;
						}
					}
				case SMALL:
					switch (parent.getSize()) {
					case SMALL:
						if (parent.getId() < current.getId()) {
							return false;
						} else {
							return true;
						}
					case LARGE:
						return false;
					}

				}
			}
		}
		return false;
	}

	private int parent(int i) {
		return Math.floorDiv((i - 1), 2);
	}

	private int left(int i) {
		return ((2 * i) + 1);
	}

	private int right(int i) {
		return ((2 * i) + 2);
	}
	
	public String print(String act, Object res) {
		OrderedJSON json = new OrderedJSON();
		json.put("Action", act);
		if(res!=null) {
			json.put("Who Updated", (res==null)?null:res.toString());
		}
		json.put("Queue", formatQ());
		
		return json.toString(1);
	}
	
	private String formatQ() {
		StringBuilder sb = new StringBuilder();
		sb.append("["); int last = 1;
		for(AirCraft ac: queue) {
			sb.append(ac.toString());
			if(last == queue.size()) {
				
			}else {
				sb.append(", ");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	public List<AirCraft> getQ(){
		return queue;
	}
	
	public static void main(String[] args) {
		ACService a = new ACService();
		int id = 0;
		a.enqueue(new AirCraft(TYPE.CARGO, SIZE.LARGE, "5", ++id));
		a.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "6", ++id));
		a.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "7", ++id));
		a.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "8", ++id));
		a.enqueue(new AirCraft(TYPE.CARGO, SIZE.SMALL, "1", ++id));
		a.enqueue(new AirCraft(TYPE.CARGO, SIZE.LARGE, "2", ++id));
		a.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.LARGE, "3", ++id));
		a.enqueue(new AirCraft(TYPE.PASSENGER, SIZE.SMALL, "4", ++id));
		for (AirCraft ac : a.queue) {
			System.out.println("=> " + ac);
		}
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
	}
}
