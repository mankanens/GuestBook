package by.bobruisk.itstep.guestbook.hotel;

import java.io.Serializable;

import by.bobruisk.itstep.guestbook.Tenant;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3586749280837151325L;
	private RoomType type;
	private int number;
	private Tenant tenant;

	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public enum RoomType {
		SINGLE(5), DOUBLE(10), LUX(15);

		private int roomCost;

		RoomType(int cost) {
			this.roomCost = cost;
		}

		public int getRoomCost() {
			return roomCost;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (number != other.number)
			return false;
		return true;
	}

}
