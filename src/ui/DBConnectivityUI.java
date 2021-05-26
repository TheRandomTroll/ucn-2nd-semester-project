package ui;

import db.DBConnection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

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
		frmDatabaseStatus.setIconImage(Toolkit.getDefaultToolkit().getImage(DBConnectivityUI.class.getResource("/ui/img/icon.png")));
		frmDatabaseStatus.setTitle("Database status");
		frmDatabaseStatus.setBounds(100, 100, 383, 72);
		frmDatabaseStatus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDatabaseStatus.getContentPane().setLayout(null);
		
		JLabel lblStatus = new JLabel("Database status: ");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStatus.setBounds(10, 11, 347, 14);
		frmDatabaseStatus.getContentPane().add(lblStatus);

		class DBConnectivityWorker implements Runnable {
			@Override
			public void run() {
				while(true) {
					boolean success = false;
					try {
						success = DBConnection.getInstance().isDbConnected();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					lblStatus.setText("Database status: " + (success ? "Connected" : "Error connecting."));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		}

		Thread t1 = new Thread(new DBConnectivityWorker());
		t1.start();
	}

	public void showWindow() {
		EventQueue.invokeLater(() -> {
			try {
				frmDatabaseStatus.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
