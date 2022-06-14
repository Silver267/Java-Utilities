package Core;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class MathPractice {
	public static final String compileDate = "2022/3/9";// Compile date
	private static int count, initcount, value, curtime, sec, min, h, errorC = 0, decide, cot = 0, difficulty, mode,
			errcount = 0, language = 0;
	private static long ans = 0, reim = 0, starttime;
	private static boolean go = false, ifdevide = false, auto = false, init1 = false, init3 = false, init5 = false, ifstop;
	private static JLabel remain, lblNewLabel_2, time, informtime, lblNewLabel, difc, model;
	private static final String authorE = "Xuanxi Jiang";
	private static final String author = "江炫熹";
	private static JTextArea output, input, remainder;
	private static final String versionSuffix = "";
	private static final String version = "v1.9.3";
	private static JScrollPane scrollPane_1;
	private static KeyEventDispatcher ked;
	private static JProgressBar progress;
	private static Thread tme;
	private static JButton submit;
	private static String tmp;
	private static GUI frame;

	public static void main() {// Main fuction
		laninit();
		buttonclicked();
	}

	private static void laninit() {
		frame = new GUI();
		frame.setVisible(true);
		ifstop = false;
		ans = 0; reim = 0;
		go = false; ifdevide = false; auto = false; init1 = false; init3 = false; init5 = false;
		errorC = 0; cot = 0; errcount = 0; count = 0; initcount = 0;
		output.setText(null);
		input.setText(null);
		input.setBounds(10, 584, 624, 72);
		input.requestFocus();
		lblNewLabel_2.setVisible(false);
		progress.setVisible(false);
		time.setVisible(false);
		informtime.setVisible(false);
		difc.setVisible(false);
		model.setVisible(false);
		ked = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
            	if(e.getID()==KeyEvent.KEY_PRESSED) {
            		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            			submit.doClick();
            			if(ifdevide && input.getText().trim().length()!=0) {
            				remainder.requestFocus();
            			}else {
            				input.requestFocus();
            			}
                    }
            	}
            	return false;
            }
        };
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(ked);
		init();
	}

	private static void init() {// Initialization
		switch (language) {
		case 0:
			output.append("请输入题目总数：");
			remain.setText("等待输入题目总数……");
			break;
		case 1:
			output.append("Please enter the total number of equations");
			remain.setText("Waiting for input");
		}
	}

	static void init2() {// Init after inputting the total equation number
		switch (language) {
		case 0:
			if (!SorI(input.getText())) {
				input.setText(null);
				lblNewLabel.setText("数据错误，重新输入题目总数：");
				return;
			}
			break;
		case 1:
			if (!SorI(input.getText())) {
				input.setText(null);
				lblNewLabel.setText("Data error");
				return;
			}
			break;
		}
		count = Integer.parseInt(input.getText());
		initcount = count;
		input.setText(null);
		switch (language) {
		case 0:
			output.setText("题目总数：");
			output.append(count + "题\n");
			output.append("请选择难度：1: Easy, 2: Normal, 3: Hard, 4: Lunatic\n");
			lblNewLabel.setText("难度：");
			remain.setText("等待输入难度");
			break;
		case 1:
			output.setText("Total：");
			output.append(count + " Equations\n");
			output.append("Select difficulty：1: Easy, 2: Normal, 3: Hard, 4: Lunatic\n");
			lblNewLabel.setText("Difficulty：");
			remain.setText("Waiting for Difficulty");
		}
		init1 = true;
		System.gc();
	}

	private static void init4() {// Init after inputting the difficulty
		switch (language) {
		case 0:
			if (!SorI(input.getText())) {
				input.setText(null);
				lblNewLabel.setText("数据错误，重新输入难度：");
				return;
			} else if (Integer.parseInt(input.getText()) > 4 || Integer.parseInt(input.getText()) < 1) {
				input.setText(null);
				lblNewLabel.setText("数据错误，重新输入难度：");
				return;
			} else {
				difficulty = Integer.parseInt(input.getText());
			}
			switch (difficulty) {
			case 1:
				difc.setText("难度：Easy");
				output.setText("题目总数：");
				output.append(count + "题\n");
				output.append("难度：Easy\n");
				break;
			case 2:
				difc.setText("难度：Normal");
				output.setText("题目总数：");
				output.append(count + "题\n");
				output.append("难度：Normal\n");
				break;
			case 3:
				difc.setText("难度：Hard");
				output.setText("题目总数：");
				output.append(count + "题\n");
				output.append("难度：Hard\n");
				break;
			case 4:
				difc.setText("难度：Lunatic");
				output.setText("题目总数：");
				output.append(count + "题\n");
				output.append("难度：Lunatic\n");
			}
			output.append("请输入运行模式：1：随机， 2：随机，降低除法可能性， 3：顺序");
			lblNewLabel.setText("运行模式：");
			remain.setText("等待输入运行模式");
			break;
		case 1:
			if (!SorI(input.getText())) {
				input.setText(null);
				lblNewLabel.setText("Data error, re-enter：");
				return;
			} else if (Integer.parseInt(input.getText()) > 4 || Integer.parseInt(input.getText()) < 1) {
				input.setText(null);
				lblNewLabel.setText("Data error, re-enter：");
				return;
			} else {
				difficulty = Integer.parseInt(input.getText());
			}
			switch (difficulty) {
			case 1:
				difc.setText("Difficulty：Easy");
				output.setText("Total：");
				output.append(count + " Equations\n");
				output.append("Difficulty：Easy\n");
				break;
			case 2:
				difc.setText("Difficulty：Normal");
				output.setText("Total：");
				output.append(count + " Equations\n");
				output.append("Difficulty：Normal\n");
				break;
			case 3:
				difc.setText("Difficulty：Hard");
				output.setText("Total：");
				output.append(count + " Equations\n");
				output.append("Difficulty：Hard\n");
				break;
			case 4:
				difc.setText("Difficulty：Lunatic");
				output.setText("Total：");
				output.append(count + " Equations\n");
				output.append("Difficulty：Lunatic\n");
			}
			output.append("Please enter mode：1：random， 2：random, decreased division possibility， 3：ordered");
			lblNewLabel.setText("Mode：");
			remain.setText("Waiting for mode");
			break;
		}
		input.setText(null);
		init3 = true;
		System.gc();
	}

	private static void init6() {// Init after inputting the mode of generating equations
		switch (language) {
		case 0:
			if (!SorI(input.getText())) {
				input.setText(null);
				lblNewLabel.setText("数据错误，重新输入运行模式：");
				return;
			} else if (Integer.parseInt(input.getText()) > 3 || Integer.parseInt(input.getText()) < 1) {
				input.setText(null);
				lblNewLabel.setText("数据错误，重新输入运行模式：");
				return;
			} else {
				mode = Integer.parseInt(input.getText());
			}
			switch (mode) {
			case 1:
				model.setText("运行模式：随机");
				break;
			case 2:
				model.setText("运行模式：随机，降低除法可能性");
				break;
			case 3:
				model.setText("运行模式：顺序");
				break;
			}
			remain.setText("剩余：" + count + "题，进度：");
			lblNewLabel.setText("答案");
			break;
		case 1:
			if (!SorI(input.getText())) {
				input.setText(null);
				lblNewLabel.setText("Data error, re-enter：");
				return;
			} else if (Integer.parseInt(input.getText()) > 3 || Integer.parseInt(input.getText()) < 1) {
				input.setText(null);
				lblNewLabel.setText("Data error, re-enter：");
				return;
			} else {
				mode = Integer.parseInt(input.getText());
			}
			switch (mode) {
			case 1:
				model.setText("mode: random");
				break;
			case 2:
				model.setText("mode: random, decreased division possibility");
				break;
			case 3:
				model.setText("mode: ordered");
				break;
			}
			remain.setText(count + " remain, Progress:");
			lblNewLabel.setText("Answer");
			break;
		}
		output.setText(null);
		newnum();
		output.append(tmp);
		progress.setMaximum(initcount);
		model.setVisible(true);
		informtime.setVisible(true);
		progress.setVisible(true);
		difc.setVisible(true);
		time.setVisible(true);
		input.setText(null);
		output.selectAll();
		init5 = true;
		System.gc();
	}

	private static void buttonclicked() {// Listen to button action
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (init1 == false) {
					init2();
				} else if (init3 == false) {
					init4();
				} else if (init5 == false) {
					init6();
				} else if (count == initcount) {
					tme = new tmme();
					tme.start();
					newset();
					output.selectAll();
				} else {
					newset();
					output.selectAll();
				}
				if (go == true) {
					submit.doClick();
				}
				System.gc();
			}
		});
	}

	private static class putans extends Thread {// Thread to automatically put answer
		public void run() {
			if(ifstop) {
				interrupt();
			}
			auto = true;
			boolean ifstop = false;
			while (!ifstop) {
				if (ifdevide) {
					String[] tmp = { String.valueOf(ans), String.valueOf(reim) };
					input.setText(tmp[0]);
					remainder.setText(tmp[1]);
				} else {
					input.setText(String.valueOf(ans));
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				submit.doClick();
				if (count == 1) {
					if (ifdevide) {
						String[] tmp = { String.valueOf(ans), String.valueOf(reim) };
						input.setText(tmp[0]);
						remainder.setText(tmp[1]);
					} else {
						input.setText(String.valueOf(ans));
					}
					switch (language) {
					case 0:
						submit.setText("DEBUG结束，按下按钮以结束程序。");
						break;
					case 1:
						submit.setText("DEBUG completed.");
					}
					break;
				}
			}
		}
	}

	private static class cal extends Thread {// Calculate current time
		public void run() {
			if(ifstop) {
				interrupt();
			}
			curtime = (int) (new Date().getTime() - starttime) / 1000;
			if (curtime < 60) {// 60s > curtime
				sec = curtime;
			} else if (curtime >= 60 && curtime < 3600) {// 60min > curtime > 60s
				sec = curtime % 60;
				min = (int) Math.floor(curtime / 60);
			} else {// curtime > 60min
				h = (int) Math.floor(curtime / 3600);
				min = (int) Math.floor((curtime / 60) - (h * 60));
				sec = (int) Math.floor(curtime - (h * 3600) - (min * 60));
			}
		}
	}

	private static class tmme extends Thread {// Thread to output time
		public void run() {
			starttime = new Date().getTime();
			Thread cal = new cal();
			if(ifstop) {
				interrupt();
				time.setText("00:00:00");
			}
			while (!ifstop) {
				cal.run();
				if (sec < 10 && min < 10 && h < 10) {
					time.setText("0" + h + ":0" + min + ":0" + sec);
				} else if (sec < 10 && min < 10) {
					time.setText(h + ":0" + min + ":0" + sec);
				} else if (sec < 10 && h < 10) {
					time.setText("0" + h + ":" + min + ":0" + sec);
				} else if (h < 10 && min < 10) {
					time.setText("0" + h + ":0" + min + ":" + sec);
				} else if (sec < 10) {
					time.setText(h + ":" + min + ":0" + sec);
				} else if (min < 10) {
					time.setText(h + ":0" + min + ":" + sec);
				} else if (h < 10) {
					time.setText("0" + h + ":" + min + ":" + sec);
				} else {
					time.setText(h + ":" + min + ":" + sec);
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void changelog() {// Output patch notes
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + MathPractice.compileDate + "\n"
				+ "------\n"
				+ "v1.0:\n"
				+ "The initial release of the application.\n"
				+ "The application is developed to practice the user's calculation speed.\n"
				+ "------\n"
				+ "v1.1:\n"
				+ "The remainder box will automatically hide if necessary\n"
				+ "GUI Beautify\n"
				+ "Code readability fixes\n"
				+ "Fixed potential occurrences java.lang.ArithmeticException.\n"
				+ "Fixed potential occurrence of diviser larger than the divided number.\n"
				+ "------\n"
				+ "v1.2:\n"
				+ "Added a scroll bar to main output panel.\n"
				+ "Added a progress bar.\n"
				+ "Added a timer implemented by creating a new thread.\n"
				+ "------\n"
				+ "v1.3:\n"
				+ "The timer could now distinguish seconds, minutes, and hours.\n"
				+ "Note: the current implementation may not allow the program to continuously run for over one day.\n"
				+ "Code readability fixes\n"
				+ "Added the function to distinguish errors.\n"
				+ "GUI readability fixes\n"
				+ "------\n"
				+ "v1.4:\n"
				+ "Code readability fixes.\n"
				+ "Code generalization fixes.\n"
				+ "Automatically scrow to the bottom if there are new output lines.\n"
				+ "Fixed a bug with the timer display which occurs when only the hours count is less than 10.\n"
				+ "Optimized the error distinguish ability.\n"
				+ "The program will not output changelog anymore unless the user typed \"changelog\"\n"
				+ "Optimized the performance of the timer.\n"
				+ "------\n"
				+ "v1.5:\n"
				+ "Optimized the function to check if input is a number.\n"
				+ "GUI theme could now adapt to the OS!\n"
				+ "GUI logic optimizations.\n"
				+ "Massively improved the algorithm used to display the progress bar.\n"
				+ "\"Enter\" key could now be used as a replacement of the submit button.\n"
				+ "The submit button now only requires one click to proceed to next question.\n"
				+ "Automatically focus to the input box when launching application.\n"
				+ "Automatically focus to the remainder box when only the inputted remainder is wrong.\n"
				+ "Optimized some algorithms.\n"
				+ "Fixed various bugs.\n"
				+ "------\n"
				+ "v1.5.1\n"
				+ "Fixed a bug where the minute and second count will not update when the timer exceeds 1h.\n"
				+ "------\n"
				+ "v1.6.0\n"
				+ "Added the ability to record the number of errors. The count will be outputted when completed all the questions.\n"
				+ "Fixed a bug where the timer incorrectly considers minutes as hours if the timer exceeds 10 min.\n"
				+ "Fixed a bug where the incorrect number remains in the input box even after submission.\n"
				+ "Fixed a bug where the timer is still running after completing all questions.\n"
				+ "Fixed a bug where the progress bar will not reach 100% even if all questions are completed.\n"
				+ "Fixed a bug where the seconds are not displaying correctly if timer exceeds 1h.\n"
				+ "GUI optimizations.\n"
				+ "The program will now output author's name if user inputted \"auth\".\n"
				+ "The user will now need to use \"cl\" to output changelog.\n"
				+ "The remainder box will not have any text by default instead of displaying 0 as default.\n"
				+ "------\n"
				+ "v1.7.0\n"
				+ "Optimized the refresh interval of the timer from 20ms to 50ms.\n"
				+ "Added a feature to automatically submit answers if entered \"auto\".\n"
				+ "Optimized the submit button.\n"
				+ "Separated the GUI and the main function.\n"
				+ "Optimized code structures (for example, changing various for loops to switch).\n"
				+ "Optimized various functions.\n"
				+ "Automatically focus to the remainder box if nothing is in it.\n"
				+ "Addressed various bugs.\n"
				+ "Added some comments in code.\n"
				+ "Updated java 8 to java 11.\n"
				+ "------\n"
				+ "v1.8.0\n"
				+ "Added difficulty selection.\n"
				+ "changelog grammar fixes.\n"
				+ "Added System.gc() after some operations to reduce memory usage.\n"
				+ "The program will now output the number of questions and the difficulties at the end.\n"
				+ "User could now customize the number of questions.\n"
				+ "Optmized GUI.\n"
				+ "User could now select the question generation modes.\n"
				+ "Fixed a bug where wrong answers in division questions are not counted as wrong.\n"
				+ "Change some int to long to prevent overflow.\n"
				+ "changelog could now record the compile time.\n"
				+ "v1.8.1\n"
				+ "Fixed the treatment when the generated divisor is larger than the divided number.\n"
				+ "v1.8.2 lts\n"
				+ "Decreased the speed of auto answer mode.\n"
				+ "v1.8.3\n"
				+ "Simplified the code to make it more efficient.\n"
				+ "v1.8.4\n"
				+ "Added auto fill function: when the remainder box is empty and the answer for remainder is 0, automatically proceed.\n"
				+ "v1.8.5\n"
				+ "Added fault tolerance where the first wrong submission will not be recorded.\n"
				+ "v1.8.6\n"
				+ "Addressed some issues.\n"
				+ "v1.8.7\n"
				+ "Clear the output box when the init values are set.\n"
				+ "------\n"
				+ "v1.9.0\n"
				+ "Added language selection module to enable users to select between Chinese and English.\n"
				+ "Fixed some grammar issues.\n"
				+ "Improved some codes.\n"
				+ "Code generalization fixes.\n"
				+ "Timer update interval set to 1ms.\n"
				+ "GUI layout edits.\n"
				+ "Improved performance of antidiv function.\n"
				+ "Optimized the UI of JProgressBar.\n"
				+ "Optimized colors.\n"
				+ "Used flatlaf as UI theme.\n"
				+ "Fixed various bugs.\n"
				+ "v1.9.1\n"
				+ "Changed \"auto\" for auto mode to \"debug\".\n"
				+ "Fixed grammar issues.\n"
				+ "Fixed some layout issues.\n"
				+ "v1.9.2\n"
				+ "Fixed the action when the language selection box was closed without selection.\n"
				+ "===Continue===\n");
	}

	private static void antidiv() {// Lower the possibility of division
		if (decide == 4) {
			int dec = (int) Math.ceil(Math.random() * 4);
			if (dec >= 2) {
				decide = (int) Math.ceil(Math.random() * 3);
			}
		}
	}

	private static void rand() {// Random arithmetic symbol

		decide = (int) Math.ceil(Math.random() * 4);// Determine the symbol
		if (count == initcount && decide == 4) {
			decide = (int) Math.ceil(Math.random() * 3);
		}
	}

	private static void AntiDiv() {// Random arithmetic symbol, but lower the possibility of division.
		decide = (int) Math.ceil(Math.random() * 4);// Determine the arithmetic symbol
		if (count == initcount && decide == 4) {
			decide = (int) Math.ceil(Math.random() * 3);
		}
		antidiv();
	}

	private static void loop() {// Arithmetic symbol appears in order
		cot++;
		if (cot % 2 == 1) {
			if (cot % 4 == 1) {
				decide = 1;
			} else {
				decide = 3;
			}
		} else {
			if (cot % 4 == 2) {
				decide = 2;
			} else {
				decide = 4;
			}
		}
	}

	private static void newnum() {// Calculate new equation
		long left, right;
		switch (mode) {
		case 1:
			rand();
			break;
		case 2:
			AntiDiv();
			break;
		case 3:
			loop();
			break;
		}
		switch (difficulty) {
		case 1:
			switch (decide) {
			case 1:// Add
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 10);
				right = (int) Math.floor(Math.random() * 10);
				ans = left + right;
				tmp = left + "+" + right + "=";
				break;
			case 2:// Subtract
				ifdevide = false;
				left = (int) Math.floor(Math.random() * 10);
				right = (int) Math.floor(Math.random() * 10);
				ans = left - right;
				tmp = left + "-" + right + "=";
				break;
			case 3:// Multiply
				ifdevide = false;
				left = (int) Math.floor(Math.random() * 10);
				right = (int) Math.floor(Math.random() * 10);
				ans = left * right;
				tmp = left + "×" + right + "=";
				break;
			case 4:// Divide
				decide = (int) Math.ceil(Math.random() * 4);
				ifdevide = true;
				left = (int) Math.floor(Math.random() * 9) + 1;
				right = (int) Math.floor(Math.random() * 9) + 1;
				if (left > right) {
					long tmpppp = left;
					left = right;
					right = tmpppp;
					ans = (int) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				} else {
					ans = (int) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				}
				break;
			}
			break;
		case 2:
			switch (decide) {
			case 1:// Add
				ifdevide = false;
				left = (int) Math.floor(Math.random() * 1000);
				right = (int) Math.floor(Math.random() * 1000);
				ans = left + right;
				tmp = left + "+" + right + "=";
				break;
			case 2:// Subtract
				ifdevide = false;
				left = (int) Math.floor(Math.random() * 1000);
				right = (int) Math.floor(Math.random() * 1000);
				ans = left - right;
				tmp = left + "-" + right + "=";
				break;
			case 3:// Multiply
				ifdevide = false;
				left = (int) Math.floor(Math.random() * 100);
				right = (int) Math.floor(Math.random() * 100);
				ans = left * right;
				tmp = left + "×" + right + "=";
				break;
			case 4:// Divide
				decide = (int) Math.ceil(Math.random() * 4);
				ifdevide = true;
				left = (int) Math.floor(Math.random() * 99) + 1;
				right = (int) Math.floor(Math.random() * 9999) + 1;
				if (left > right) {
					long tmpppp = left;
					left = right;
					right = tmpppp;
					ans = (int) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				} else {
					ans = (int) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				}
				break;
			}
			break;
		case 3:
			switch (decide) {
			case 1:// Add
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 10000);
				right = (long) Math.floor(Math.random() * 10000);
				ans = left + right;
				tmp = left + "+" + right + "=";
				break;
			case 2:// Subtract
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 10000);
				right = (long) Math.floor(Math.random() * 10000);
				ans = left - right;
				tmp = left + "-" + right + "=";
				break;
			case 3:// Multiply
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 10000);
				right = (long) Math.floor(Math.random() * 10000);
				ans = left * right;
				tmp = left + "×" + right + "=";
				break;
			case 4:// Divide
				decide = (int) Math.ceil(Math.random() * 4);
				ifdevide = true;
				left = (long) Math.floor(Math.random() * 9999) + 1;
				right = (long) Math.floor(Math.random() * 999999) + 1;
				if (left > right) {
					long tmpppp = left;
					left = right;
					right = tmpppp;
					ans = (long) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				} else {
					ans = (long) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				}
				break;
			}
			break;
		case 4:
			switch (decide) {
			case 1:// Add
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 10000000);
				right = (long) Math.floor(Math.random() * 10000000);
				ans = left + right;
				tmp = left + "+" + right + "=";
				break;
			case 2:// Subtract
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 10000000);
				right = (long) Math.floor(Math.random() * 10000000);
				ans = left - right;
				tmp = left + "-" + right + "=";
				break;
			case 3:// Multiply
				ifdevide = false;
				left = (long) Math.floor(Math.random() * 1000000);
				right = (long) Math.floor(Math.random() * 1000000);
				ans = left * right;
				tmp = left + "×" + right + "=";
				break;
			case 4:// Divide
				decide = (int) Math.ceil(Math.random() * 4);
				ifdevide = true;
				left = (long) Math.floor(Math.random() * 9999999) + 1;
				right = (long) Math.floor(Math.random() * 999999999) + 1;
				if (left > right) {
					long tmpppp = left;
					left = right;
					right = tmpppp;
					ans = (long) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				} else {
					ans = (long) Math.floor(right / left);
					reim = right % left;
					tmp = right + "÷" + left + "=";
				}
				break;
			}
			break;
		}

	}

	private static boolean SorI(String str) {// Check if "str" is a number
		if (str.equals("-")) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '-') {
				return false;
			}
		}
		return true;
	}

	private static void newset() {// Main calculation which determines if the input is correct
		if (count < 1) {
			tme.interrupt();
			progress.setValue(100);
			String tail;
			KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(ked);
			switch (language) {
			case 0:
				if (auto) {
					JOptionPane.showMessageDialog(null, "恭喜你的电脑挑战成功。");
				} else {
					String mod = new String();
					switch (mode) {
					case 1:
						mod = "\n运行模式：随机";
						break;
					case 2:
						mod = "\n运行模式：随机，降低除法可能性";
						break;
					case 3:
						mod = "\n运行模式：顺序";
						break;
					}
					tail = "\n题目总数：" + initcount + "\n错误数量：" + errorC;
					switch (difficulty) {
					case 1:
						JOptionPane.showMessageDialog(null, "恭喜，挑战成功！" + mod + "\n难度：Easy" + tail);
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "恭喜，挑战成功！" + mod + "\n难度：Normal" + tail);
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "恭喜，挑战成功！" + mod + "\n难度：Hard" + tail);
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "恭喜完成终极挑战：Lunatic难度！！！" + mod + tail);
						break;
					}
				}
				break;
			case 1:
				if (auto) {
					JOptionPane.showMessageDialog(null, "Congratulations for your computer");
				} else {
					String mod = new String();
					switch (mode) {
					case 1:
						mod = "\nMode: random";
						break;
					case 2:
						mod = "\nMode: random, decreased division possibility";
						break;
					case 3:
						mod = "\nMode: ordered";
						break;
					}
					tail = "\nTotal Equations：" + initcount + "\nError count：" + errorC;
					switch (difficulty) {
					case 1:
						JOptionPane.showMessageDialog(null, "Congratulations！" + mod + "\nDifficulty：Easy" + tail);
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Congratulations！" + mod + "\nDifficulty：Normal" + tail);
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Congratulations！" + mod + "\nDifficulty：Hard" + tail);
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "Congratulations for accomplishing Lunatic!" + mod + tail);
						break;
					}
				}
				break;
			}
			frame.dispose();
			try {
				CoreProgram.main(new String[1]);
			} catch (UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}
		if (input.getText().equals("cl")) {
			changelog();
			output.append(tmp);
			input.setText(null);
		} else if (input.getText().equals("auth")) {
			output.append("\n===============\n");
			output.append("作者中文名：\n");
			output.append(author + "\n");
			output.append("作者英文名：\n");
			output.append(authorE + "\n");
			output.append("====继续计算====\n");
			output.append(tmp);
			input.setText(null);
		} else if (input.getText().equals("debug")) {
			input.setText(null);
			output.append("\nDEBUG MODE ENABLED\n");
			output.append(tmp);
			Thread putans = new putans();
			putans.start();
		} else if (go == true) {
			go = false;
			value = initcount - count;
			progress.setValue(value);
			if (ifdevide == false) {
				input.setBounds(10, 584, 624, 72);
				lblNewLabel_2.setVisible(false);
				remainder.setText(null);
			} else {
				input.setBounds(10, 584, 310, 72);
				lblNewLabel_2.setVisible(true);
				remainder.setText(null);
			}
			output.append("\n");
			output.append(tmp);
		} else {
			String[] errs = new String[5];
			switch (language) {
			case 0:
				errs[0] = "错误：没有答案\n";
				errs[1] = "错误：不是数字\n";
				errs[2] = "错误：答案错误\n";
				errs[3] = "错误：余数错误\n";
				errs[4] = "错误：余数和答案错误\n";
				break;
			case 1:
				errs[0] = "Error: No answer\n";
				errs[1] = "Error: Not a number\n";
				errs[2] = "Error: Answer incorrect\n";
				errs[3] = "Error: Remainder incorrect\n";
				errs[4] = "Error: Remainder and Answer incorrect\n";
			}
			if (ifdevide == false) {

				if (!input.getText().equals(String.valueOf(ans))) {
					if (errcount == 0) {
						input.requestFocus();
						remainder.setText(null);
						input.setText(null);
						errcount++;
					} else if (input.getText().equals("")) {
						output.append("\n");
						output.append(errs[0]);
						output.append(tmp);
					} else if (SorI(input.getText()) == false) {
						output.append("\n");
						output.append(errs[1]);
						output.append(tmp);
						input.setText(null);
					} else {
						errorC++;
						output.append("\n");
						output.append(errs[2]);
						output.append(tmp);
						input.setText(null);
					}
				} else {
					errcount = 0;
					go = true;
					output.append(String.valueOf(ans));
					input.setText(null);
					count = count - 1;
					switch (language) {
					case 0:
						remain.setText("剩余：" + count + "题，进度：");
						break;
					case 1:
						remain.setText(count + " remain, Progress:");
					}
					newnum();
				}
			} else {
				lblNewLabel_2.setVisible(true);
				if (!input.getText().equals(String.valueOf(ans)) || !remainder.getText().equals(String.valueOf(reim))) {
					if (remainder.getText().equals("")) {
						if (reim == 0) {
							remainder.setText("0");
							submit.doClick();
						} else {
							remainder.requestFocus();
						}
					} else if (errcount == 0) {
						input.requestFocus();
						remainder.setText(null);
						input.setText(null);
						errcount++;
					} else if (input.getText().equals("")) {
						output.append("\n");
						output.append(errs[0]);
						output.append(tmp);
						remainder.setText(null);
					} else if (!SorI(input.getText())) {
						output.append("\n");
						output.append(errs[1]);
						output.append(tmp);
						input.setText(null);
					} else if (!SorI(remainder.getText())) {
						output.append("\n");
						output.append(errs[1]);
						output.append(tmp);
						remainder.setText(null);
					} else if (!remainder.getText().equals(String.valueOf(reim))) {
						if (!input.getText().equals(String.valueOf(ans))) {
							errorC++;
							output.append("\n");
							output.append(errs[4]);
							output.append(tmp);
							input.setText(null);
							remainder.setText(null);
							input.requestFocus();
						} else {
							output.append("\n");
							output.append(errs[3]);
							output.append(tmp);
							remainder.requestFocus();
							remainder.setText(null);
						}
					} else {
						errorC++;
						output.append("\n");
						output.append(errs[2]);
						output.append(tmp);
						input.setText(null);
						remainder.setText(null);
						input.requestFocus();
					}
				} else {
					errcount = 0;
					input.requestFocus();
					go = true;
					remainder.setText(null);
					input.setText(null);
					count = count - 1;
					switch (language) {
					case 0:
						output.append(String.valueOf(ans) + "余" + String.valueOf(reim));
						remain.setText("剩余：" + count + "题，进度：");
						break;
					case 1:
						output.append(String.valueOf(ans) + "R" + String.valueOf(reim));
						remain.setText(count + " remain, Progress:");
						break;
					}
					newnum();
				}
			}
		}
	}
	
	private static void langSelection() {
		String[] options = { "中文", "English" };
		language = JOptionPane.showOptionDialog(null, "Please select your language", "Lanuage Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(language == -1) 
			langSelection();
		return;
	}
	
	private static class GUI extends JFrame {// GUI
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
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			langSelection();
			switch (language) {
			case 0:
				setTitle("运算练习器" + version + " " + versionSuffix);
				break;
			case 1:
				setTitle("Calculaton Practice " + version + " " + versionSuffix);
				break;
			}

			setBounds(100, 100, 930, 700);
			getContentPane().setLayout(null);

			switch (language) {
			case 0:
				remain = new JLabel("剩余：" + count + "题，进度：");
				lblNewLabel = new JLabel("题目总数：");
				informtime = new JLabel("已用时间：");
				lblNewLabel_2 = new JLabel("余数");
				submit = new JButton("提交");
				difc = new JLabel("难度：");
				model = new JLabel("模式：");
				break;
			case 1:
				remain = new JLabel("Remaining：" + count + "E, Progress:");
				lblNewLabel = new JLabel("Total E：");
				informtime = new JLabel("Time：");
				lblNewLabel_2 = new JLabel("Remainder");
				submit = new JButton("Submit");
				difc = new JLabel("Difficulty：");
				model = new JLabel("Mode：");
				break;
			}
			scrollPane_1 = new JScrollPane();
			progress = new JProgressBar(0, initcount);
			remainder = new JTextArea();
			output = new JTextArea();
			input = new JTextArea();
			time = new JLabel("00:00:00");

			switch (language) {
			case 0:
				remain.setBounds(74, 10, 522, 15);
				break;
			case 1:
				remain.setBounds(64, 10, 522, 15);
				break;
			}
			lblNewLabel_2.setBounds(459, 565, 70, 20);
			scrollPane_1.setBounds(10, 35, 893, 531);
			lblNewLabel.setBounds(133, 565, 200, 20);
			remainder.setBounds(330, 584, 304, 72);
			informtime.setBounds(786, 10, 60, 15);
			progress.setBounds(190, 10, 146, 14);
			submit.setBounds(644, 584, 259, 72);
			getContentPane().add(scrollPane_1);
			input.setBounds(10, 584, 310, 72);
			model.setBounds(480, 10, 300, 15);
			time.setBounds(850, 10, 54, 15);
			difc.setBounds(350, 10, 150, 15);

			progress.setStringPainted(true);
			scrollPane_1.setViewportView(output);
			getContentPane().add(input);
			output.setEditable(false);
			remainder.setText("0");
			input.setColumns(10);
			input.requestFocus();

			KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
			remainder.getInputMap().put(enter, "none");
			input.getInputMap().put(enter, "none");
			getContentPane().add(lblNewLabel_2);
			getContentPane().add(lblNewLabel);
			getContentPane().add(informtime);
			getContentPane().add(remainder);
			getContentPane().add(progress);
			getContentPane().add(remain);
			getContentPane().add(submit);
			getContentPane().add(time);
			getContentPane().add(difc);
			getContentPane().add(model);

			input.setBackground(new Color(255, 255, 255));
			remainder.setBackground(new Color(255, 255, 255));
			submit.setBackground(new Color(250, 250, 250));
			progress.setUI(new MyProgressBarUI());
			progress.setForeground(new Color(39, 175, 153));
			progress.setBackground(new Color(220, 220, 220));
			progress.repaint();

			setLocationRelativeTo(null);
		}

		public class MyProgressBarUI extends BasicProgressBarUI {

			// Text color before covered by progress bar
			@Override
			protected Color getSelectionBackground() {
				return new Color(57, 62, 70);
			}

			// Text color after covered by progress bar
			@Override
			protected Color getSelectionForeground() {
				return new Color(34, 40, 49);
			}

		}
	}
}
