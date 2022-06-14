package Core;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class EncryptUtil extends JFrame {
	public static final String compileDate = "2022/3/9";
	private static final String version = "v1.5";
	private static String password = "", title = "Encrypt/Decryptor " + version;
	private static final long serialVersionUID = 1L;
	private static JScrollPane consoleScroll, outputScroll, inputScroll;
	private static JTextArea output, console, input;
	private static boolean IfEncrypt = true, isError = false;
	private static JButton SetMode, submit, clearConsole, SetPasswd;
	private static JLabel inputLable, outputLable;
	private static JPanel panel;
	private static EncryptUtil frame;

	private static byte[] Encrypt(String W, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = W.getBytes("GBK");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] Decrypt(byte[] W, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(password.getBytes());
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] result = cipher.doFinal(W);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1)
				hex = '0' + hex;
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	private static byte[] StringToBytes(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[(hexStr.length() / 2)];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	private EncryptUtil() {
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
		setBounds(100, 100, 1000, 610);
		setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		console = new JTextArea();
		output = new JTextArea();
		outputScroll = new JScrollPane();
		inputScroll = new JScrollPane();
		submit = new JButton("Submit");
		consoleScroll = new JScrollPane();
		SetMode = new JButton("Decode");
		input = new JTextArea();
		clearConsole = new JButton("Clear ↑");
		inputLable = new JLabel("Input");
		outputLable = new JLabel("Output");
		SetPasswd = new JButton("Set key");

		submit.setBounds(442, 134, 97, 23);
		inputScroll.setBounds(10, 165, 476, 398);
		SetMode.setBounds(10, 10, 150, 110);
		outputScroll.setBounds(496, 165, 480, 398);
		consoleScroll.setBounds(170, 10, 806, 110);
		clearConsole.setBounds(879, 132, 97, 23);
		inputLable.setBounds(186, 140, 58, 15);
		outputLable.setBounds(737, 140, 58, 15);
		SetPasswd.setBounds(10, 132, 97, 23);

		panel.add(inputScroll);
		panel.add(outputScroll);
		panel.add(consoleScroll);
		panel.add(SetMode);
		panel.add(clearConsole);
		panel.add(submit);
		panel.add(inputLable);
		panel.add(outputLable);
		panel.add(SetPasswd);
		input.setText(null);

		outputScroll.setViewportView(output);
		consoleScroll.setViewportView(console);
		inputScroll.setViewportView(input);

		inputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		outputScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		consoleScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		inputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		outputScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		consoleScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		output.setEditable(false);
		console.setEditable(false);
		
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		input.getInputMap().put(enter, "none");

		input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((char) e.getKeyChar() == KeyEvent.VK_ENTER) {
					submit.doClick();
				}
			}
		});

		setLocationRelativeTo(null);
	}

	public static void AfterMath() {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable trans = new StringSelection(output.getText());
		clipboard.setContents(trans, null);
		console.append("Copied to clipboard.\n");
	}

	public static void UnifyAfter() {
		input.setText(null);
		input.requestFocus();
		console.selectAll();
		System.gc();
	}

	public static void PasswdInput() {
		String alpha = (String) JOptionPane.showInputDialog(frame, "New key:", "Change key", JOptionPane.PLAIN_MESSAGE, null,
				null, "Password");
		password = alpha;
		if (alpha == null)
			PasswdInput();
		return;
	}

	public static void main() {
		frame = new EncryptUtil();
		frame.setTitle(title);
		PasswdInput();
		frame.setVisible(true);
		IfEncrypt = true;
		SetMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (IfEncrypt) {
					SetMode.setText("Encrypt");
					IfEncrypt = false;
					console.append("Using encrypt mode.\n");
					console.selectAll();
					input.requestFocus();
				} else {
					SetMode.setText("Decrypt");
					IfEncrypt = true;
					console.append("Using decrypt mode.\n");
					console.selectAll();
					input.requestFocus();
				}
			}
		});
		if (IfEncrypt)
			console.append("Using encrypt mode.\n");
		else 
			console.append("Using decrypt mode.\n");
		console.append("Input content.\n");
		input.requestFocus();
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				output.setText(null);
				if (input.getText().equals("")) {
					console.append("Error: no input.\n");
					console.selectAll();
					input.setText(null);
					input.requestFocus();
					System.gc();
				} else {
					int inputLines = input.getLineCount();
					String[] tempinputs = input.getText().split("\n");
					String[] inputs;
					boolean ifHasSlashN;
					if (!input.getText().substring(input.getText().length() - 1).equals("\n")) {
						ifHasSlashN = false;
						inputs = new String[inputLines + 1];
					} else {
						ifHasSlashN = true;
						inputs = new String[inputLines];
						inputLines--;
					}
					for (int i = 0; i < inputLines; i++) {
						inputs[i] = tempinputs[i];
					}
					if (!ifHasSlashN) {
						inputs[inputLines] += "\n";
					}
					for (int i = 0; i < inputLines; i++) {
						if (IfEncrypt) {
							byte[] encryptbits = Encrypt(inputs[i], password);
							String tmp = parseByte2HexStr(encryptbits);
							output.append(tmp + "\n");
						} else {
							byte[] tmp = StringToBytes(inputs[i]);
							try {
								byte[] enctmp = Decrypt(tmp, password);
								String processed = new String(enctmp);
								output.append(processed + "\n");
							} catch (Exception ec) {
								isError = true;
								console.append("Password incorrect.\n");
								input.setText(null);
								console.selectAll();
								System.gc();
								break;
							}
						}
					}
					if(!isError)
						AfterMath();
					UnifyAfter();
				}

			}
		});
		clearConsole.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				console.setText("Please enter content.\n");
			}
		});
		SetPasswd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswdInput();
				console.append("New key recorded.\n");
			}
		});
	}

}