import java.awt.*;
import javax.swing.*;

public class Spot {
	
	// value of spot, use 0 for unset, 1 for unmarked, and 2 for marked in
	// Hitori and Kakurasu.
	private int value;
	
	//possible increments of spot, use 3 for Hitori and Kakurasu
	private int limit;

	public Spot(int limit, int value) {
		this.limit = limit;
		this.value = value;

	}

	public Spot(int limit) {
		this.value = 0;
		this.limit = limit;
	}

	// Increments a spot, setting it to zero if it's at its limit.
	public void increment() {
		if (this.value == limit) {
			this.value = 0;
		} else {
			this.value = this.value + 1;
		}
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
}