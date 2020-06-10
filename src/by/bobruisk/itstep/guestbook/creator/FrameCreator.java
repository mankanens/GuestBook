package by.bobruisk.itstep.guestbook.creator;

import javax.swing.JFrame;

import by.bobruisk.itstep.guestbook.gui.GUI;

public interface FrameCreator {

	JFrame create(String name, GUI gui);

}
