package by.bobruisk.itstep.guestbook.gui;

import java.io.Serializable;

import javax.swing.JFrame;

import by.bobruisk.itstep.guestbook.GuestBook;
import by.bobruisk.itstep.guestbook.creator.AddRoomFrameCreator;
import by.bobruisk.itstep.guestbook.creator.AddTenantFrameCreator;
import by.bobruisk.itstep.guestbook.creator.FrameCreator;
import by.bobruisk.itstep.guestbook.creator.MainFrameCreator;
import by.bobruisk.itstep.guestbook.creator.TenantInfoFrameCreator;
import by.bobruisk.itstep.guestbook.creator.DepartureFrameCreator;

public class GUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8882070475795206263L;
	private JFrame mainFrame = createMainFrame();
	private JFrame addRoomFrame = createAddRoomFrame();
	private JFrame tenantInfoFrame = createTenantInfoFrame();;
	private JFrame addTenantFrame = createAddTenantFrame();
	private JFrame departureFrame = createDepartureFrame();
	private FrameCreator frameCreator;
	public GuestBook guestBook;

	public GUI(GuestBook guestBook) {
		this.guestBook = guestBook;
	}

	private JFrame createMainFrame() {
		frameCreator = new MainFrameCreator();
		return frameCreator.create("Гостевая книга.", this);
	}

	private JFrame createAddRoomFrame() {
		frameCreator = new AddRoomFrameCreator();
		return frameCreator.create("Добавить номер.", this);
	}

	private JFrame createTenantInfoFrame() {
		frameCreator = new TenantInfoFrameCreator();
		return frameCreator.create("Информация о жильце.", this);
	}

	private JFrame createAddTenantFrame() {
		frameCreator = new AddTenantFrameCreator();
		return frameCreator.create("Новый постоялец.", this);
	}

	private JFrame createDepartureFrame() {
		frameCreator = new DepartureFrameCreator();
		return frameCreator.create("Отметить отъезд.", this);
	}

	public void showMain() {
		mainFrame.setVisible(true);
	}

	public void showAddRoom() {
		addRoomFrame.setVisible(true);
	}

	public void showTenantInfoFrame() {
		tenantInfoFrame.setVisible(true);
	}

	public void showAddTenant() {
		addTenantFrame.setVisible(true);
	}

	public void showAddTime() {
		departureFrame.setVisible(true);
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JFrame getAddRoomFrame() {
		return addRoomFrame;
	}

	public void setAddRoomFrame(JFrame addRoomFrame) {
		this.addRoomFrame = addRoomFrame;
	}

	public JFrame getTenantInfoFrame() {
		return tenantInfoFrame;
	}

	public void setTenantInfoFrame(JFrame tenantInfoFrame) {
		this.tenantInfoFrame = tenantInfoFrame;
	}

	public JFrame getAddTenantFrame() {
		return addTenantFrame;
	}

	public void setAddTenantFrame(JFrame addTenantFrame) {
		this.addTenantFrame = addTenantFrame;
	}

	public JFrame getDepartureFrame() {
		return departureFrame;
	}

	public void setDepartureFrame(JFrame departureFrame) {
		this.departureFrame = departureFrame;
	}

}
