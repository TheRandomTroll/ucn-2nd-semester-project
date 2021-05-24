package ui;

import db.DBConnection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class DBConnectivityUI {
	private JFrame frmDatabaseStatus;

	/**
	 * Create the application.
	 */
	public DBConnectivityUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDatabaseStatus = new JFrame();
		frmDatabaseStatus.setTitle("Database status");
		frmDatabaseStatus.setBounds(100, 100, 383, 72);
		frmDatabaseStatus.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmDatabaseStatus.getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("Database status: ");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(10, 11, 347, 14);
		frmDatabaseStatus.getContentPane().add(lblStatus);

		class DBConnectivityWorker implements Runnable {
			@Override
			public void run() {
				while(true) {
					boolean success = DBConnection.getInstance().isDbConnected();
					lblStatus.setText("Database status: " + (success ? "Connected" : "Error connecting."));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		Thread t1 = new Thread(new DBConnectivityWorker());
		t1.start();
	}

	public void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDatabaseStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
