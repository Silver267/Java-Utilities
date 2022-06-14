package Core;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UnsupportedLookAndFeelException;

public class Changelog {
	
	private static JTextArea output;
	
	public static void main(int ProgramLog, String str) {
		JFrame frame = new GUI();
		frame.setVisible(true);
		frame.setTitle(str + "Changelog");
		output.append("Creator: Xuanxi Jiang\n");
		switch(ProgramLog){
			case 0:
				UtilitiesLog();
				break;
			case 1:
				CounterLog();
				break;
			case 2:
				EncryptUtilLog();
				break;
			case 3:
				MathPracticeLog();
				break;
			case 4:
				RandomGeneratorLog();
				break;
			case 5:
				TimerLog();
				break;
		}
	}
	
	private static void UtilitiesLog() {
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + CoreProgram.compileDate + "\n"
				+ "------\n"
				+ "v1.0:\n"
				+ "The initial release of the application.\n"
				+ "The application is developed to combine all previous utilities which I composed.\n"
				+ "------\n"
				+ "v1.1:\n"
				+ "Updated various sub-applications\n"
				+ "Focus on the buttons when opening / closing sub-applications.\n"
				+ "Deleted needless codes and utilize the main application's theme.\n"
				+ "Unified code format.\n"
				+ "Adjusted the order of the tabs.\n"
				+ "Solved an issue where some values are not reset after closing the sub-application.\n"
				+ "------\n"
				+ "v1.2:\n"
				+ "Updated various sub-applications.\n"
				+ "Deleted needless imports\n"
				+ "Fixed some wordings of the patch notes.\n"
				+ "Optimized the format of the code to output patch notes.\n"
				+ "v1.2.1:\n"
				+ "Automatically close the main window when opening a sub-application, and re-open when the application is closed.\n"
				+ "Used \"Final\" for some variables.\n"
				+ "Automatically center the program when opened\n"
				+ "v1.2.2:\n"
				+ "Updated various sub-applications.\n"
				+ "Users are now able to use left and right arrow keys to navigate through the sub-applications.\n"
				+ "Changed the declaration of some variables from public to private.\n"
				+ "Changed the \"ActionListener\" of all sub-applications to \"KeyEventDispatcher\".");
	}
	
	private static void CounterLog() {
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + Counter.compileDate + "\n"
				+ "------\n"
				+ "v1.0:\n"
				+ "The initial release of the application.\n"
				+ "The application is developed merely as a counter.\n");
	}
	
	private static void EncryptUtilLog() {
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + EncryptUtil.compileDate + "\n"
				+ "------\n"
				+ "v1.0:\n"
				+ "The initial release of the application.\n"
				+ "The application is developed to encrypt and decrypt text.\n"
				+ "------\n"
				+ "Resolved numerous bugs.\n"
				+ "The user needs to input the password to start the application.\n"
				+ "Code optimizations.\n"
				+ "------\n"
				+ "v1.2:\n"
				+ "Automatically close the application if the password is incorrect.\n"
				+ "Code optimizations.\n"
				+ "------\n"
				+ "v1.3.1:"
				+ "Added \"Clear console\" function.\n"
				+ "Console readability optimizations.\n"
				+ "Code optimizations.\n"
				+ "------\n"
				+ "v1.4 r1:\n"
				+ "Deleted the integrated patch notes.\n"
				+ "Disabled the need of inputting the correct password to get in the application.\n"
				+ "Code readability optimizations.\n"
				+ "Robustness optimizations.\n"
				+ "------\n"
				+ "v1.5:\n"
				+ "Added the ability to manually input the key for encrypting and decrypting.\n"
				+ "Added the ability to automatically check if the decryption key is correct.\n"
				+ "Added the ability to automatically check if the input is empty.\n"
				+ "Changed the encoding from GBK to UTF-8.\n"
				+ "Fixed the layout of the console.\n"
				+ "Set output and console as not editable\n"
				+ "Resolved numerous bugs.\n"
				+ "Code optimizations.\n");
	}
	
	private static void MathPracticeLog() {
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + CoreProgram.compileDate + "\n"
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
				+ "Fixed the action when the language selection box was closed without selection.\n");
	}
	
	private static void RandomGeneratorLog() {
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + CoreProgram.compileDate + "\n"
				+ "------\n"
				+ "v1.0:\n"
				+ "The initial release of the application.\n"
				+ "The application is developed to generate random numbers to decide stuff.\n"
				+ "------\n"
				+ "v1.1:\n"
				+ "Fixed a bug which will cause the program to freeze during execution.\n"
				+ "Used SecureRandom() instead of Random().\n"
				+ "Used flatlaf as theme.\n"
				+ "Optimized the output to make it display the round count and \"true\" count.\n"
				+ "------\n"
				+ "v1.2:\n"
				+ "Optimized code structure.\n"
				+ "Added the ability to manually choose the number of booleans generated.\n"
				+ "The console will now automatically slide to the newest output.\n"
				+ "Added the ability to make the application stay on top.\n"
				+ "v1.2.1:\n"
				+ "Fixed a bug when the program will not accept new input after a number that is not positive odd number was inputted.\n"
				+ "Added the ability to ensure the number of booleans generated is nonnegative.\n"
				+ "v1.2.2:\n"
				+ "The menu bar will now be locked when generating booleans.\n"
				+ "Added the option to restore round count.\n"
				+ "v1.2.3:\n"
				+ "If the inputted number of booleans generated is empty, fallback to previously entered value.\n"
				+ "Reduced the interval of generating booleans (from 1s to 0.5s)\n"
				+ "Fixed a bug when the round count is resetted but the true count did not.\n"
				+ "v1.2.4:\n"
				+ "Regenerate the random number generator's seed after restore round count.\n"
				+ "v1.2.5:\n"
				+ "The program can run when enter key is pressed even if the run button is not selected.\n");
	}
	
	private static void TimerLog() {
		output.append("===Patch Notes===\n"
				+ "Compile Date:" + CoreProgram.compileDate + "\n"
				+ "------\n"
				+ "v1.0:\n"
				+ "The initial release of the application.\n"
				+ "The application is developed as a timer.\n"
				+ "Provides Start, Pause, and Stop function.\n"
				+ "------\n"
				+ "v1.1:\n"
				+ "Substantially increased the update interval of the GUI.\n"
				+ "Added the ability to make the application stay on top.\n"
				+ "Added a menu bar.\n"
				+ "------\n"
				+ "v1.2:\n"
				+ "Changed the theme to flatlaf。\n"
				+ "------\n"
				+ "v1.3:\n"
				+ "Added a countdown option.\n"
				+ "v1.3.1:\n"
				+ "Optimized the input dialogue of countdown.\n"
				+ "v1.3.2:\n"
				+ "Fixed a bug which may cause the hours and minutes remain not displaying correctly.\n"
				+ "Deleted some needless codes.\n"
				+ "v1.3.3:\n"
				+ "If there are no input when inputting countdown, put 0 as default.\n");
	}
	
	private static class GUI extends JFrame {
		private static final long serialVersionUID = 1L;

		public GUI() {
			setResizable(false);
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
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 750, 550);
			
			JScrollPane scrollPane = new JScrollPane();
			add(scrollPane, BorderLayout.CENTER);
			
			output = new JTextArea();
			output.setEditable(false);
			output.setLineWrap(true);
			scrollPane.setViewportView(output);
			
			setLocationRelativeTo(null);
		}
	}
}
