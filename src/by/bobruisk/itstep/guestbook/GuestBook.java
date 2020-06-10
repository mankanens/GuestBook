package by.bobruisk.itstep.guestbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.bobruisk.itstep.guestbook.gui.GUI;
import by.bobruisk.itstep.guestbook.hotel.Hotel;
import by.bobruisk.itstep.guestbook.hotel.Room;
import by.bobruisk.itstep.guestbook.hotel.Room.RoomType;

public class GuestBook {

	private Hotel hotel = new Hotel();
	private List<Tenant> history = new ArrayList<>();
	private GUI gui = new GUI(this);

	public Hotel getHotel() {
		return hotel;
	}

	public List<Tenant> getHistory() {
		return history;
	}

	public void setHistory(List<Tenant> history) {
		this.history = history;
	}

	public Object[] markDeparture(int roomNumber) {
		Object[] answers = new Object[2];
		for (Room room : this.hotel.getRooms()) {
			if (room.getNumber() == roomNumber) {
				if (room.getTenant() == null) {
					answers[0] = false;
					answers[1] = "Указанная ваши комната уже пуста.";
					return answers;
				}
				room.getTenant().setDepartureDate(new Date());
				this.history.add(room.getTenant());
				this.hotel.getTenants().remove(room.getTenant());
				switch (room.getType()) {
				case SINGLE:
					this.hotel.addSingleEmptyRoom();
					break;
				case DOUBLE:
					this.hotel.addDoubleEmptyRoom();
					break;
				default:
					this.hotel.addLuxEmpty();
					break;
				}
				room.setTenant(null);
				answers[0] = true;
				answers[1] = "Постоялец успешно выписан.\nПожелайте ему приятного пути.";
				writeToFile();
				return answers;
			}
		}
		answers[0] = false;
		answers[1] = "Указанная вами комната не существует.\nПроверьте правильность номера.";
		return answers;
	}

	public Object[] addRoom(Room room) {
		Object[] answers = new Object[2];
		if (!this.hotel.getRooms().add(room)) {
			answers[0] = false;
			answers[1] = "Комната с данным номером уже существует.\nИзмените номер и попробуйте ещё раз.";
			return answers;
		} else {
			switch (room.getType()) {
			case LUX:
				this.hotel.addLuxEmpty();
				break;
			case SINGLE:
				this.hotel.addSingleEmptyRoom();
				break;
			case DOUBLE:
				this.hotel.addDoubleEmptyRoom();
			}
		}
		answers[0] = true;
		answers[1] = "Комната добавлена успешно.";
		writeToFile();
		return answers;
	}

	public Object[] addTenant(Tenant tenant, RoomType roomType) {
		Object[] answers = new Object[2];
		if (this.hotel.getRooms().size() == 0) {
			answers[0] = false;
			answers[1] = "Недостаточно комнат для заселения нового постояльца.";
			return answers;
		}
		if (!this.hotel.getTenants().add(tenant)) {
			answers[0] = false;
			answers[1] = "Не удаётся добавить постояльца.\nПроверьте правильность паспортных данных.";
			return answers;
		} else {
			boolean roomFinded = false;
			for (Room room : this.hotel.getRooms()) {
				if (room.getType() == roomType && room.getTenant() == null) {
					room.setTenant(tenant);
					tenant.setRoomNumber(room.getNumber());
					switch (roomType) {
					case SINGLE:
						this.hotel.reduceSingleEmptyRoom();
						break;
					case DOUBLE:
						this.hotel.reduceDoubleEmptyRoom();
						break;
					case LUX:
						this.hotel.reduceLuxEmpty();
					}
					roomFinded = true;
					break;
				}
			}
			if (!roomFinded) {
				this.hotel.getTenants().remove(tenant);
				answers[0] = false;
				answers[1] = "Недостаточно комнат для заселения нового постояльца.";
				return answers;
			}
		}
		answers[0] = true;
		answers[1] = "Новый постоялец оформлен успешно.\nВручите ему ключи от номера " + tenant.getRoomNumber();
		writeToFile();
		return answers;
	}

	public Tenant getTenantInfo(String name, String surname, String patronymic) {
		for (Tenant tenant : this.hotel.getTenants()) {
			if (tenant.getName().equals(name) && tenant.getSurname().equals(surname)
					&& tenant.getPatronymic().equals(patronymic)) {
				return tenant;
			}
		}
		return null;
	}

	public void start() {
		gui.showMain();
	}

	private void writeToFile() {
		File file = new File("History.txt");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.history);
			oos.close();
			fos.close();
		} catch (IOException e) {
			System.out.println("Что-то не записалось");
		}
		file = new File("Hotel.txt");
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.hotel);
			oos.close();
			fos.close();
		} catch (IOException e) {
			System.out.println("Что-то не записалось");
		}
	}

	public static GuestBook readFromFile() {
		File file = new File("History.txt");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		GuestBook guestBook = new GuestBook();
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			guestBook.history = (List<Tenant>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			return guestBook;
		} catch (ClassNotFoundException e) {

		}
		file = new File("Hotel.txt");
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			guestBook.hotel = (Hotel) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			return guestBook;
		} catch (ClassNotFoundException e) {

		}
		return guestBook;
	}

}
