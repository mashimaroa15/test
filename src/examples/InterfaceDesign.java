/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Mashi
 */
public class InterfaceDesign {

    /**
     * Fonction principal
     * @param args
     */
    public static void main(String args[]) {
        InterfaceDesign id = new InterfaceDesign();
        id.getPnlProjectDetail("My Project");
    }

    /**
     *
     * @param strProjectName
     */
    public void getPnlProjectDetail(String strProjectName) {
        JPanel pnlProjectDetail = new JPanel();
        JScrollPane scrProjectDetail;
        pnlProjectDetail.setLayout(null);
//        pnlProjectDetail.setBounds(0, 0, 400, 400);
        pnlProjectDetail.setPreferredSize(new Dimension(500, 500));
        JFrame frmtest = new JFrame(strProjectName);
        frmtest.setBounds(1, 1, 300, 200);

        JLabel lblFirstName = new JLabel("First Name");
        JLabel lblLastName = new JLabel("Last Name");
        JLabel lblAddress = new JLabel("Address");
        JLabel lblCity = new JLabel("City");
        JLabel lblZipCode = new JLabel("Zip Code");
        JLabel lblPhone = new JLabel("Phone");
        JLabel lblEmailID = new JLabel("Emain Id");

        JTextField tfFirstName = new JTextField();
        JTextField tfLastName = new JTextField();
        JTextField tfAddress = new JTextField();
        JTextField tfCity = new JTextField();
        JTextField tfZipCode = new JTextField();
        JTextField tfPhone = new JTextField();
        JTextField tfEmailID = new JTextField();

        lblFirstName.setBounds(25, 55, 85, 20);
        tfFirstName.setBounds(25, 85, 85, 20);
        pnlProjectDetail.add(lblFirstName);
        pnlProjectDetail.add(tfFirstName);

        lblLastName.setBounds(25, 115, 85, 20);
        tfLastName.setBounds(25, 145, 85, 20);
        pnlProjectDetail.add(lblLastName);
        pnlProjectDetail.add(tfLastName);

        lblAddress.setBounds(25, 175, 85, 20);
        tfAddress.setBounds(25, 205, 85, 20);
        pnlProjectDetail.add(lblAddress);
        pnlProjectDetail.add(tfAddress);

        lblCity.setBounds(25, 235, 85, 20);
        tfCity.setBounds(25, 265, 85, 20);
        pnlProjectDetail.add(lblCity);
        pnlProjectDetail.add(tfCity);

        lblZipCode.setBounds(25, 295, 85, 20);
        tfZipCode.setBounds(25, 325, 85, 20);
        pnlProjectDetail.add(lblZipCode);
        pnlProjectDetail.add(tfZipCode);

        lblPhone.setBounds(25, 355, 85, 20);
        tfPhone.setBounds(25, 385, 85, 20);
        pnlProjectDetail.add(lblPhone);
        pnlProjectDetail.add(tfPhone);

        lblEmailID.setBounds(25, 415, 85, 20);
        tfEmailID.setBounds(25, 445, 85, 20);
        pnlProjectDetail.add(lblEmailID);
        pnlProjectDetail.add(tfEmailID);

        scrProjectDetail = new JScrollPane(pnlProjectDetail);
        frmtest.setContentPane(scrProjectDetail);
        //frmtest.add(scrProjectDetail);
        frmtest.setVisible(true);
        frmtest.setResizable(false);
        frmtest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //return pnlProjectDetail;      
    }   
}
