/*Zasada działania programu: użytkownik wpisuję wartość wynagrodzenia brutto,
zaś program oblicza i wyświetla poszczególne składki wraz z kwotą netto. 
Następnie użytkownik ma możliwość zapisania wyników do pliku *.txt lub *.doc.
Po zapisaniu, moze on otworzyć otrzymany plik.*/
package SalaryCalculator;

import java.awt.Desktop;
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.logging.*;
import javax.swing.*;

// Zastosowane class-y
/* import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException; */
/**
 *
 * @author Sławomir Kowalczyk
 */
public class SalaryCalculator extends JFrame implements ActionListener {

    JButton bCalculate;
    JLabel lTitle, lGrossSalary, lTaxYear, lWorkplace,
            lE, lR, lC, lZ, lZalPom, lNettSalary,
            lShowGrossSalary, lShowE, lShowR, lShowC, lShowZ, lShowZalPom, lShowNettSalary,
            linformation1, linformation2;
    JMenuBar menuBar;
    JMenu menuFile, menuView, menuHelp;
    JMenuItem mOpen, mSave, mExit,
            mMetal, mNimbus, mWindows,
            mAbout;
    JTextField tBrutto;
    JCheckBox ChYesNo;
    BigDecimal brutto, netto, E, R, C, PNFZ, Z, ZP, Po, Zal, ZalPom, z1, KW, KUP;
    double z2, z3, z4, z5;

    public SalaryCalculator() {
        setSize(500, 550);
        setTitle("Kalkulator Wynagrodzenia");
        setLayout(null);

        menuBar = new JMenuBar();
        menuFile = new JMenu("Plik");
        menuView = new JMenu("Widok");
        menuHelp = new JMenu("Pomoc");
        setJMenuBar(menuBar);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

        mOpen = new JMenuItem("Otwórz");
        mOpen.addActionListener(this);
        mSave = new JMenuItem("Zapisz");
        mSave.addActionListener(this);

        mExit = new JMenuItem("Zamknij");
        mExit.addActionListener(this);

        menuFile.add(mOpen);
        menuFile.add(mSave);
        menuFile.addSeparator();
        menuFile.add(mExit);

        mMetal = new JMenuItem("Metal");
        mMetal.addActionListener(this);
        mNimbus = new JMenuItem("Nimbus");
        mNimbus.addActionListener(this);
        mWindows = new JMenuItem("Windows");
        mWindows.addActionListener(this);

        menuView.add(mMetal);
        menuView.add(mNimbus);
        menuView.add(mWindows);

        mAbout = new JMenuItem("O programie");
        mAbout.addActionListener(this);

        menuHelp.add(mAbout);

        lTitle = new JLabel("Rodzaj umowy: umowa o pracę");
        lTitle.setBounds(10, 20, 200, 20);
        add(lTitle);

        lTaxYear = new JLabel("Rok podatkowy: 2017");
        lTaxYear.setBounds(350, 20, 200, 20);
        add(lTaxYear);

        lGrossSalary = new JLabel("Wynagrodzenie brutto [PLN]:");
        lGrossSalary.setBounds(10, 70, 180, 20);
        add(lGrossSalary);

        tBrutto = new JTextField();
        tBrutto.setBounds(210, 70, 100, 25);
        add(tBrutto);

        lWorkplace = new JLabel("Praca w miejscu zamieszkania?");
        lWorkplace.setBounds(10, 100, 200, 20);
        add(lWorkplace);

        ChYesNo = new JCheckBox();
        ChYesNo.setBounds(210, 100, 20, 20);
        ChYesNo.addActionListener(this);
        add(ChYesNo);

        bCalculate = new JButton("Oblicz");
        bCalculate.setBounds(250, 150, 100, 20);
        bCalculate.addActionListener(this);
        add(bCalculate);

        lGrossSalary = new JLabel("Kwota brutto:");
        lGrossSalary.setBounds(10, 210, 200, 20);
        add(lGrossSalary);

        lShowGrossSalary = new JLabel();
        lShowGrossSalary.setBounds(220, 210, 200, 20);
        add(lShowGrossSalary);

        lE = new JLabel("Ubezpieczenie emerytalne:");
        lE.setBounds(10, 240, 200, 20);
        add(lE);

        lShowE = new JLabel();
        lShowE.setBounds(220, 240, 200, 20);
        add(lShowE);

        lR = new JLabel("Ubezpieczenie rentowe:");
        lR.setBounds(10, 270, 200, 20);
        add(lR);

        lShowR = new JLabel();
        lShowR.setBounds(220, 270, 200, 20);
        add(lShowR);

        lC = new JLabel("Ubezpieczenie chorobowe:");
        lC.setBounds(10, 300, 200, 20);
        add(lC);

        lShowC = new JLabel();
        lShowC.setBounds(220, 300, 200, 20);
        add(lShowC);

        lZ = new JLabel("Ubezpieczenie zdrowotne:");
        lZ.setBounds(10, 330, 200, 20);
        add(lZ);

        lShowZ = new JLabel();
        lShowZ.setBounds(220, 330, 200, 20);
        add(lShowZ);

        lZalPom = new JLabel("Zaliczka na podatek dochodowy:");
        lZalPom.setBounds(10, 360, 200, 20);
        add(lZalPom);

        lShowZalPom = new JLabel();
        lShowZalPom.setBounds(220, 360, 200, 20);
        add(lShowZalPom);

        lNettSalary = new JLabel("Wynagrodzenie netto:");
        lNettSalary.setBounds(10, 390, 200, 20);
        add(lNettSalary);

        lShowNettSalary = new JLabel();
        lShowNettSalary.setBounds(220, 390, 200, 20);
        add(lShowNettSalary);

        linformation1 = new JLabel("Wskazówka: Możesz zapisać otrzymane wyniki obliczeń, np. do pliku *.txt.");
        linformation1.setBounds(10, 420, 450, 20);
        add(linformation1);

        linformation2 = new JLabel("Następnie sprawdzić zapisany plik otwierając go.");
        linformation2.setBounds(10, 440, 450, 20);
        add(linformation2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if (z == mOpen) {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    Desktop.getDesktop().edit(fc.getSelectedFile());
                } catch (IOException ex) {
                    Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (z == mSave) {
            JFileChooser okno = new JFileChooser();
            if (okno.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File plik = okno.getSelectedFile();
                //JOptionPane.showMessageDialog(null, "Wybraliśmy plik to: " + plik);
                try {
                    PrintWriter save = new PrintWriter(plik, "UTF-8");
                    save.println("Kwota brutto = " + brutto + " PLN");
                    save.println("Ubezpieczenie emerytalne = " + E + " PLN");
                    save.println("Ubezpieczenie rentowe = " + R + " PLN");
                    save.println("Ubezpieczenie chorobowe = " + C + " PLN");
                    save.println("Ubezpieczenie zdrowotne = " + Z + " PLN");
                    save.println("Zaliczka na podatek dochodowy = " + ZalPom + " PLN");
                    save.println("Wynagrodzenie netto = " + netto + " PLN");
                    save.println();
                    save.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (z == mExit) {
            dispose();
        }
        if (z == mMetal) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.updateComponentTreeUI(this);
        } else if (z == mNimbus) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.updateComponentTreeUI(this);
        } else if (z == mWindows) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(SalaryCalculator.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
        if (z == mAbout) {
            JOptionPane.showMessageDialog(null, "Kalkulator wynagrodzenia pracownika"
                    + "\nstyczeń 2017"
                    + "\nwersia 1.0\n\n"
                    + "autor: Sławomir Kowalczyk",
                    "O Programie",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (ChYesNo.isSelected()) {
            KUP = new BigDecimal("111.25");
        } else if (!ChYesNo.isSelected()) {
            KUP = new BigDecimal("135.63");
        }
        if (z == bCalculate) {

            try {
                brutto = new BigDecimal(tBrutto.getText());
                brutto = brutto.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                E = brutto.multiply(new BigDecimal("0.0976"));
                E = E.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                R = brutto.multiply(new BigDecimal("0.015"));
                R = R.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                C = brutto.multiply(new BigDecimal("0.0245"));
                C = C.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                PNFZ = ((brutto.subtract(E)).subtract(R)).subtract(C);

                Z = PNFZ.multiply(new BigDecimal("0.09"));
                Z = Z.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                ZP = PNFZ.multiply(new BigDecimal("0.0775"));
                ZP = ZP.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                Po = PNFZ.subtract(KUP);
                Po = Po.setScale(0, BigDecimal.ROUND_HALF_EVEN);

                Zal = Po.multiply(new BigDecimal("0.18"));
                Zal = Zal.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                KW = new BigDecimal("46.335");
                ZalPom = (Zal.subtract(ZP)).subtract(KW);
                ZalPom = ZalPom.setScale(0, BigDecimal.ROUND_HALF_EVEN);
                
                /**
                 * Obliczenia dotyczące składki zdrowotnej i zaliczki na podatek
                 * dochodowy zgodne z art 83 ust. 1 i 2 ustawy z dn.27.08.2004
                 * o świadczeniach opieki zdrowotnej finansowanych ze środków
                 * publicznych, z późn. zm.
                 *
                 * @param z1..z5 - zmienne pomocnicze
                 */
                
                z1 = Zal.subtract(KW);
                z1 = z1.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                z2 = Double.parseDouble(Z.toString());
                z3 = Double.parseDouble(z1.toString());
                if (z2 > z3) {
                    Z = z1;
                }
                z4 = Double.parseDouble(Z.toString());
                if (z4 < 0) {
                    Z = new BigDecimal("0");
                }
                z5 = Double.parseDouble(ZalPom.toString());
                if (z5 < 0) {
                    ZalPom = new BigDecimal("0");
                }

                netto = (PNFZ.subtract(Z)).subtract(ZalPom);
                netto = netto.setScale(2, BigDecimal.ROUND_HALF_EVEN);

                lShowGrossSalary.setText(brutto.toString() + " PLN");
                lShowE.setText(E.toString() + " PLN");
                lShowR.setText(R.toString() + " PLN");
                lShowC.setText(C.toString() + " PLN");
                lShowZ.setText(Z.toString() + " PLN");
                lShowZalPom.setText(ZalPom.toString() + " PLN");
                lShowNettSalary.setText(netto.toString() + " PLN");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Wpisz prawidłowe dane", "Błąd", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SalaryCalculator app = new SalaryCalculator();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
