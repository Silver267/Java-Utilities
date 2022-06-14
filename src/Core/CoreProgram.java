package Core;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatLightLaf;

public class CoreProgram {
	static final String compileDate = "2022/3/9";
	private static final String version = "v1.2.2";
	private static JPanel pan = new JPanel();
	private static JTabbedPane tabbedPane;
	private static JButton p0, p1, p2, p3, p4, p5;
	private static int currtab = 0;
	private static final String[] AppList = {"Program Launcher","Counter", "Encrypt/Decryptor", "Calculation Practice", "Random Generator", "Timer"};
	private static KeyEventDispatcher ked;

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		ked = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
            	if(e.getID()==KeyEvent.KEY_PRESSED) {
            		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex()-1<0 ? AppList.length-1 : tabbedPane.getSelectedIndex()-1);
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    	tabbedPane.setSelectedIndex((tabbedPane.getSelectedIndex()+1)%AppList.length);
                    }
            	}
            	return false;
            }
		};
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ked);
		JFrame frame = new GUI();
		frame.setVisible(true);
	}
	
	static void reqFocus() {
        currtab = tabbedPane.getSelectedIndex();
        switch(currtab) {
        case 0:
        	p0.requestFocus();
        	break;
        case 1:
        	p1.requestFocus();
        	break;
        case 2:
        	p2.requestFocus();
        	break;
        case 3:
        	p3.requestFocus();
        	break;
        case 4:
        	p4.requestFocus();
        	break;
        case 5:
        	p5.requestFocus();
        	break;
        }
	}
	
	private static class GUI extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public GUI() {
			try {
				UIManager.setLookAndFeel(new FlatLightLaf());
				SwingUtilities.updateComponentTreeUI(this);
				pack();
			} catch (Exception e) {
				e.printStackTrace();
			}
			setTitle("Program Launcher " + version);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			pan = new JPanel();
			pan.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(pan);
			pan.setLayout(new CardLayout(0, 0));

			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			pan.add(tabbedPane, "alpha");

			tabbedPane.addChangeListener(new ChangeListener() {
			    public void stateChanged(ChangeEvent e) {
			    	reqFocus();
			    }
			});

			p0 = new JButton("Patch notes");
			tabbedPane.addTab("Patch notes", null, p0, null);
			
			p1 = new JButton(AppList[1]);
			tabbedPane.addTab("App 1", null, p1, null);

			p2 = new JButton(AppList[2]);
			tabbedPane.addTab("App 2", null, p2, null);

			p3 = new JButton(AppList[3]);
			tabbedPane.addTab("App 3", null, p3, null);

			p4 = new JButton(AppList[4]);
			tabbedPane.addTab("App 4", null, p4, null);

			p5 = new JButton(AppList[5]);
			tabbedPane.addTab("App 5", null, p5, null);

			p0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int tmp = select();
					if (tmp!=-1) {
						KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
						Changelog.main(tmp, AppList[tmp]);
						dispose();						
					}
				}
			});
			
			p1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					dispose();
					Counter.main();
				}
			});
			p2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					dispose();
					EncryptUtil.main();
				}
			});
			p3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					dispose();
					MathPractice.main();
				}
			});
			p4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					dispose();
					RandomGenerator.main();
				}
			});
			p5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					dispose();
					Timer.main();
				}
			});
			
			setLocationRelativeTo(null);
		}
	}
	
	private static int select() {
		String s = (String)JOptionPane.showInputDialog(null, "Select application:", "Patch note selector", JOptionPane.PLAIN_MESSAGE, null, AppList, null);
		if(s==null) {
			return -1;
		}else {
			for(int i=0; i<AppList.length; i++) {
				if(s.equals(AppList[i])) 
					return i;
			}
		}
		return -1;
	}
}
