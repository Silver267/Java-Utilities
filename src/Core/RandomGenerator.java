package Core;

import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class RandomGenerator {
	public static final String compileDate = "2022/3/9";
	private static final String Version = "v1.2.5";
	private static SecureRandom rd = new SecureRandom();
	private static int valTrue, count = 0, countPos;
	private static int totalRound = 11;
	private static boolean ifstop;
	private static JPanel content;
	private static JTextArea JTA;
	private static JButton conti;
	private static JFrame jf;
	private static JMenu operations;
	private static KeyEventDispatcher ked;
	
	private static class cli extends Thread {
		public void run() {
			count++;
			valTrue = 0;
			rd.setSeed(SecureRandom.getSeed(256));
			JTA.setText(null);
			jf.requestFocus();
			conti.setEnabled(false);
			operations.setEnabled(false);
			for (int i = 0; i < totalRound - 1; i++) {
				if(ifstop) {
					interrupt();
				}
				boolean nb = rd.nextBoolean();
				JTA.append(nb + "\n\r");
				if (nb)
					valTrue++;
				try {
					Thread.sleep(Math.abs(rd.nextInt() % 500));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				JTA.selectAll();
			}
			boolean nb = rd.nextBoolean();
			JTA.append(nb + "\n\r");
			if (nb)
				valTrue++;
			conti.setEnabled(true);
			operations.setEnabled(true);
			if (valTrue >= Math.ceil(totalRound / 2) + 1) {
				countPos++;
				JTA.append("Result: true\n");
				JTA.append("Currently at " + count + " round, " + countPos + " rounds have true as result.");
			} else {
				JTA.append("Result: false\n");
				JTA.append("Currently at " + count + " round, " + countPos + " rounds have true as result.");
			}
			JTA.selectAll();
			conti.requestFocus();
			return;
		}
	}

	public static void main() {
		ked = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
            	if(e.getID()==KeyEvent.KEY_PRESSED) {
            		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            			conti.doClick();
                    }
            	}
            	return false;
            }
        };
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ked);
		jf = new GUI();
		ifstop = false;
		totalRound = 11; count = 0; countPos = 0; 
		jf.setVisible(true);
		JTA.setText(null);
		conti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.gc();
				Thread t = new cli();
				t.start();
			}
		});
	}
	
	private static void boolInput() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
		int tmp = 11; //fallback protection.
		try {
			tmp = Integer.parseInt((String)JOptionPane.showInputDialog(jf, "Please input a positive odd number", "Number of boolean generated:", JOptionPane.PLAIN_MESSAGE, null, null, null));
			if(tmp%2==0 || tmp<0) boolInput();
			else totalRound = tmp;
		}catch(Exception e1) {
			if(tmp==11) {
				return;
			}else boolInput();
		}
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ked);
		return;
	}
	
	private static class GUI extends JFrame {
		private static final long serialVersionUID = 1L;

		public GUI() {
			CoreProgram.reqFocus();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
					ifstop = true;
					e.getWindow().dispose();
					try {
						CoreProgram.main(new String[1]);
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			setResizable(false);
			setTitle("Random Generator " + Version);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			content = new JPanel();
			content.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(content);
			content.setLayout(new BorderLayout(0, 0));
			
			JMenuBar bar = new JMenuBar();
			operations = new JMenu("Operations");
			JMenuItem rounds = new JMenuItem("Number of boolean generation");
			JMenuItem reset = new JMenuItem("Reset round count");
			JCheckBoxMenuItem onTop = new JCheckBoxMenuItem("Stay on top");
			
			setJMenuBar(bar);
			bar.add(operations);
			operations.add(onTop);
			operations.add(rounds);
			operations.add(reset);
			
			JScrollPane scrollPane = new JScrollPane();
			content.add(scrollPane, BorderLayout.CENTER);

			JTA = new JTextArea();
			JTA.setEditable(false);
			scrollPane.setViewportView(JTA);

			conti = new JButton("Run");
			scrollPane.setRowHeaderView(conti);
			
			rounds.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolInput();
				}
			});
			
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					count = 0;
					countPos = 0;
					JTA.setText(null);
					rd.setSeed(SecureRandom.getSeed((int)(Math.random()*256 + 1)));
					JTA.append("Round count resetted.");
				}
			});
			
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

			setLocationRelativeTo(null);
		}
	}

}
