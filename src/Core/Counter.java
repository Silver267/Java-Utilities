package Core;

import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Counter {
	public static final String compileDate = "2022/3/9";
	private static final String version = "v1.0";
	private static JPanel frame;
	private static JLabel displayed;
	private static JButton increase, decrease, return0;
	private static long count;
	private static GUI content;
	private static JMenuBar menuBar;
	private static JMenu operations;
	private static JCheckBoxMenuItem onTop;
	private static KeyEventDispatcher ked;

	public static void main() {
		ked = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
            	if(e.getID()==KeyEvent.KEY_PRESSED) {
            		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            			increase.doClick();
                    }
            	}
            	return false;
            }
        };
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ked);
		count = 0;
		content = new GUI();
		content.setVisible(true);
		buttonclicked();
	}

	private static void buttonclicked() {
		increase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				update();
			}
		});
		decrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count--;
				update();
			}
		});
		return0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = 0;
				update();
			}
		});
	}

	private static void update() {
		displayed.setText(count + "");
	}

	private static class GUI extends JFrame {// GUI
		private static final long serialVersionUID = 1L;

		public GUI() {
			CoreProgram.reqFocus();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					e.getWindow().dispose();
					try {
						CoreProgram.main(new String[1]);
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			});
			setTitle("Counter "+version);
			setResizable(false);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 400, 210);

			menuBar = new JMenuBar();
			setJMenuBar(menuBar);

			operations = new JMenu("Operations");
			menuBar.add(operations);

			onTop = new JCheckBoxMenuItem("Stay on top");
			operations.add(onTop);

			frame = new JPanel();
			frame.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(frame);
			frame.setLayout(null);

			displayed = new JLabel("0");
			displayed.setHorizontalAlignment(SwingConstants.CENTER);
			displayed.setFont(new Font("Poly", Font.PLAIN, 22));
			displayed.setBounds(10, 10, 374, 100);
			frame.add(displayed);

			increase = new JButton("Increase");
			increase.setBounds(10, 120, 93, 23);
			frame.add(increase);

			decrease = new JButton("Decrease");
			decrease.setBounds(280, 120, 93, 23);
			frame.add(decrease);

			return0 = new JButton("Zero");
			return0.setBounds(150, 120, 93, 23);
			frame.add(return0);
			onTop.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (onTop.getState()) {
						setAlwaysOnTop(true);
					} else {
						setAlwaysOnTop(false);
					}
				}

			});
			addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
						increase.doClick();
					}
				}
			});

			setLocationRelativeTo(null);
		}
	}
}
