package Core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Timer {
	public static final String compileDate = "2022/3/9";
	private static final String version = "v1.3.3";
	private static JCheckBoxMenuItem onTop, ifcd;
	private static long curtime, starttime, sec, min, h, bfTime = 0, tttime = 0;
	private static JButton start, pause, stop;
	private static boolean ifPause, exit, countdown = false;
	private static JLabel diTime;
	private static JFrame frame;
	private static Thread t;

	public static void main() {
		init();
		diTime.setText("00:00:00");
		pause.setEnabled(false);
		start.setEnabled(true);
		start.setEnabled(true);
		stop.setEnabled(false);
		ifPause = false;
		exit = true;
		bfTime = 0;
		System.gc();
	}

	private static void init() {
		frame = new GUI();
		frame.setVisible(true);
		buttonsClicked();
	}

	private static void buttonsClicked() {
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t = new tmme();
				start.setEnabled(false);
				pause.setEnabled(true);
				stop.setEnabled(true);
				exit = false;
				t.start();
				System.gc();
			}
		});
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ifPause) {// resume
					t = new tmme();
					exit = false;
					ifPause = false;
					if(!countdown) {
						curtime = bfTime;
					}
					pause.setText("Pause");
					t.start();
				} else {// pause
					pause.setText("Continue");
					ifPause = true;
					if(!countdown) {
						bfTime = curtime;
					} else {
						bfTime = tttime - curtime;
					}
					exit = true;
				}
				System.gc();
			}
		});
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diTime.setText("00:00:00");
				pause.setEnabled(false);
				start.setEnabled(true);
				start.setEnabled(true);
				stop.setEnabled(false);
				ifPause = false;
				exit = true;
				bfTime = 0;
				System.gc();
			}
		});
	}

	private static class cal extends Thread {
		public void run() {
			if(!countdown) {
				curtime = ((new Date().getTime() - starttime) / 1000) + bfTime;
				if (curtime < 60) {
					sec = curtime;
				} else if (curtime >= 60 && curtime < 3600) {
					sec = curtime % 60;
					min = (int) Math.floor(curtime / 60);
				} else {
					h = (int) Math.floor(curtime / 3600);
					min = (int) Math.floor((curtime / 60) - (h * 60));
					sec = (int) Math.floor(curtime - (h * 3600) - (min * 60));
				}				
			}else {
				curtime = tttime - (((new Date().getTime() - starttime) / 1000) + bfTime);
				if (curtime == 0) {
					JOptionPane.showMessageDialog(frame, "Time is up");
					diTime.setText("00:00:00");
					ifcd.setState(false);
					pause.setEnabled(false);
					start.setEnabled(true);
					start.setEnabled(true);
					stop.setEnabled(false);
					ifPause = false;
					exit = true;
					bfTime = 0;
					System.gc();
				} else if (curtime < 60) {
					sec = curtime;
					min = 0;
					h = 0;
				} else if (curtime >= 60 && curtime < 3600) {
					sec = curtime % 60;
					min = (int) Math.floor(curtime / 60);
					h = 0;
				} else {
					h = (int) Math.floor(curtime / 3600);
					min = (int) Math.floor((curtime / 60) - (h * 60));
					sec = (int) Math.floor(curtime - (h * 3600) - (min * 60));
				}
			}
		}
	}

	private static class tmme extends Thread {
		public void run() {
			starttime = new Date().getTime();
			Thread cal = new cal();
			while (!exit) {
				cal.run();
				if (sec < 10 && min < 10 && h < 10) {
					diTime.setText("0" + h + ":0" + min + ":0" + sec);
				} else if (sec < 10 && min < 10) {
					diTime.setText(h + ":0" + min + ":0" + sec);
				} else if (sec < 10 && h < 10) {
					diTime.setText("0" + h + ":" + min + ":0" + sec);
				} else if (h < 10 && min < 10) {
					diTime.setText("0" + h + ":0" + min + ":" + sec);
				} else if (sec < 10) {
					diTime.setText(h + ":" + min + ":0" + sec);
				} else if (min < 10) {
					diTime.setText(h + ":0" + min + ":" + sec);
				} else if (h < 10) {
					diTime.setText("0" + h + ":" + min + ":" + sec);
				} else {
					diTime.setText(h + ":" + min + ":" + sec);
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String diag(int state, int values[]) {
		String result = null;
		switch (state){
		case 0:
			result = (String)JOptionPane.showInputDialog(frame, "Hours:", "Countdown", JOptionPane.PLAIN_MESSAGE, null, null, "Input Hours");
			break;
		case 1:
			result = (String)JOptionPane.showInputDialog(frame, "Minutes:", "Countdown", JOptionPane.PLAIN_MESSAGE, null, null, "Input Minutes");
			break;
		case 2:
			result = (String)JOptionPane.showInputDialog(frame, "Seconds:", "Countdown", JOptionPane.PLAIN_MESSAGE, null, null, "Input Seconds");
		}
		try {
			values[state] = Integer.parseInt(result);
		}catch(java.lang.NumberFormatException ec){
			switch (state) {
			case 0:
				if(result.equals("Input Hours")) {
					values[state] = 0;
					return "0";
				}
				break;
			case 1:
				if(result.equals("Input Minutes")) {
					values[state] = 0;
					return "0";
				}
				break;
			case 2:
				if(result.equals("Input Seconds")) {
					values[state] = 0;
					return "0";
				}
			}
			JOptionPane.showMessageDialog(null, "Format error");
			input();
		}
		return result;
	}
	
	private static void input() {
		int tmp[] = new int[3];
		for(int i=0; i<3; i++) {
			diag(i, tmp);
		}
		tttime = (tmp[0]*3600) + (tmp[1]*60) + tmp[2];
		curtime = tttime;
		if (tttime < 60) {
			sec = tttime;
		} else if (tttime >= 60 && tttime < 3600) {
			sec = tttime % 60;
			min = (int) Math.floor(tttime / 60);
		} else {
			h = (int) Math.floor(tttime / 3600);
			min = (int) Math.floor((tttime / 60) - (h * 60));
			sec = (int) Math.floor(tttime - (h * 3600) - (min * 60));
		}
		if (sec < 10 && min < 10 && h < 10) {
			diTime.setText("0" + h + ":0" + min + ":0" + sec);
		} else if (sec < 10 && min < 10) {
			diTime.setText(h + ":0" + min + ":0" + sec);
		} else if (sec < 10 && h < 10) {
			diTime.setText("0" + h + ":" + min + ":0" + sec);
		} else if (h < 10 && min < 10) {
			diTime.setText("0" + h + ":0" + min + ":" + sec);
		} else if (sec < 10) {
			diTime.setText(h + ":" + min + ":0" + sec);
		} else if (min < 10) {
			diTime.setText(h + ":0" + min + ":" + sec);
		} else if (h < 10) {
			diTime.setText("0" + h + ":" + min + ":" + sec);
		} else {
			diTime.setText(h + ":" + min + ":" + sec);
		}
	}
	
	private static class GUI extends JFrame {
		private static final long serialVersionUID = 1L;
		private static JPanel contentPane;

		public GUI() {
			CoreProgram.reqFocus();
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					e.getWindow().dispose();
					try {
						CoreProgram.main(new String[1]);
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
				}
			});
			setResizable(false);
			setTitle("Timer " + version);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 700, 380);

			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			onTop = new JCheckBoxMenuItem("Stay on top");
			ifcd = new JCheckBoxMenuItem("Countdown?");
			JMenu operations = new JMenu("Operations");
			diTime = new JLabel("00:00:00");
			JMenuBar bar = new JMenuBar();
			start = new JButton("Start");
			pause = new JButton("Pause");
			stop = new JButton("End");

			pause.setBounds(265, 240, 160, 70);
			diTime.setBounds(10, 10, 664, 200);
			start.setBounds(10, 240, 160, 70);
			stop.setBounds(515, 240, 160, 70);

			setJMenuBar(bar);
			bar.add(operations);
			operations.add(onTop);
			operations.add(ifcd);

			contentPane.add(diTime);
			contentPane.add(start);
			contentPane.add(pause);
			contentPane.add(stop);

			pause.setEnabled(false);
			stop.setEnabled(false);

			diTime.setHorizontalAlignment(SwingConstants.CENTER);
			
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
			
			ifcd.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(ifcd.getState()) {
						countdown = true;
						input();
					}else {
						countdown = false;
						curtime = 0;
						tttime = 0;
					}
				}
			});

			setLocationRelativeTo(null);
		}
	}
}
