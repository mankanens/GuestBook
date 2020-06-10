package by.bobruisk.itstep.guestbook;

public class Main {

	public static void main(String[] args) {
		GuestBook guestBook = GuestBook.readFromFile();
		guestBook.start();
	}

}
