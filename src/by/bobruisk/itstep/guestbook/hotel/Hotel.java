package by.bobruisk.itstep.guestbook.hotel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import by.bobruisk.itstep.guestbook.Tenant;

public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474941730853474352L;
	private Set<Room> rooms = new HashSet<>();
	private Set<Tenant> tenants = new HashSet<>();
	private int singleEmptyRoom = 0;
	private int doubleEmptyRoom = 0;
	private int luxEmpty = 0;

	public int getSingleEmptyRoom() {
		return singleEmptyRoom;
	}

	public int getDoubleEmptyRoom() {
		return doubleEmptyRoom;
	}

	public int getLuxEmpty() {
		return luxEmpty;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<Tenant> getTenants() {
		return tenants;
	}

	public void setTenants(Set<Tenant> tenants) {
		this.tenants = tenants;
	}

	public void reduceSingleEmptyRoom() {
		this.singleEmptyRoom--;
	}

	public void addSingleEmptyRoom() {
		this.singleEmptyRoom++;
	}

	public void reduceDoubleEmptyRoom() {
		this.doubleEmptyRoom--;
	}

	public void addDoubleEmptyRoom() {
		this.doubleEmptyRoom++;
	}

	public void reduceLuxEmpty() {
		this.luxEmpty--;
	}

	public void addLuxEmpty() {
		this.luxEmpty++;
	}

}
