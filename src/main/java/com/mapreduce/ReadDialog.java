package com.mapreduce;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Box;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ReadDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void create() {
		try {
			ReadDialog dialog = new ReadDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReadDialog() {
		setBounds(100, 100, 317, 156);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		FlowLayout fl_contentPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_contentPanel.setAlignOnBaseline(true);
		contentPanel.setLayout(fl_contentPanel);
		{
			Box verticalBox = Box.createVerticalBox();
			contentPanel.add(verticalBox);
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					lblNewLabel_1 = new JLabel("Input");
					lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
					horizontalBox.add(lblNewLabel_1);
				}
				{
					textField_1 = new JTextField();
					textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
					horizontalBox.add(textField_1);
					textField_1.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				final JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							ReadWrite.readFileFromHDFS(textField_1.getText());							
							JOptionPane.showMessageDialog(okButton.getParent(), "Success", "Success", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException err) {
							err.printStackTrace();
							JOptionPane.showMessageDialog(okButton.getParent(), err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
