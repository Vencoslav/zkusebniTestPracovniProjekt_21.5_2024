import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainForm extends JFrame{
    private JPanel panelMain;
    private JButton dalsiBt;
    private JButton predchoziBt;
    private JButton ulozBt;
    private JTextField tfNazev;
    private JTextField tfPocetResitelu;
    private JCheckBox checkBox1;
    private JTextField tfDatum;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JTable table1;
    private List<Projekt> seznam = new ArrayList<>();
    private File selectedFile;
    private int index = 0;



    public MainForm(){
        setContentPane(panelMain);
        setTitle("Projekty");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();

        Projekt projektJedna = new Projekt("Nový vesmýr", 2,BigDecimal.valueOf(100.00),2, LocalDate.of(2015,2,14),true);
        Projekt projektDva = new Projekt("Berserk", 3,BigDecimal.valueOf(300.00),3, LocalDate.of(2006,6,3),false);
        Projekt projektTri = new Projekt("JJK", 1, BigDecimal.valueOf(200.00),1, LocalDate.of(2024,9,3),true);
        seznam.add(projektJedna);
        seznam.add(projektDva);
        seznam.add(projektTri);

        dalsiBt.addActionListener(e->{nactiDalsi();});
        predchoziBt.addActionListener(e->{nactiPredchozi();});
        ulozBt.addActionListener(e->{nactiSoubor();});

        display();




    }

    public void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu projekt = new JMenu("Projekt");
        menuBar.add(projekt);


        JMenuItem pridej = new JMenuItem("Přidej další");
        projekt.add(pridej);
        pridej.addActionListener(e->{
            pridejNovyProjekt();});

        JMenuItem statistika = new JMenuItem("Statistika");//Pozor!!! aby to tlačítko bylo schopné fungovat po stisknutí tak to musí být JMenuItem
        menuBar.add(statistika);
        statistika.addActionListener(e->{vytvorStatistiku();});

    }

    public void display(){
        Projekt aktualniProjekt = seznam.get(index);
        tfNazev.setText(aktualniProjekt.getNazev());
        tfPocetResitelu.setText(String.valueOf(aktualniProjekt.getPocetResitelu()));
        tfDatum.setText(String.valueOf(aktualniProjekt.getDatum()));
        a1RadioButton.setSelected(aktualniProjekt.getHodnoceni() == 1);
        a2RadioButton.setSelected(aktualniProjekt.getHodnoceni() == 2);
        a3RadioButton.setSelected(aktualniProjekt.getHodnoceni() == 3);
        checkBox1.setSelected(aktualniProjekt.getDokonceno());

        TableModel tableModel = new TableModel(seznam);
        table1.setModel(tableModel);
        table1.getColumnModel().getColumn(1).setMinWidth(100);

    }

    public void nactiDalsi(){
        if(index < seznam.size()-1){
            index++;
            display();
        }
    }

    public void nactiPredchozi(){
        if(index > 0){
            index--;
            display();
        }
    }

    public void nactiSoubor(){
        JFileChooser fc = new JFileChooser(".");
        int reslult = fc.showOpenDialog(this);
        if(reslult == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            ulozDoSouboru(selectedFile);
        }
    }

    public void ulozDoSouboru(File selectedFile){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile))){
           for(Projekt projekt : seznam){

               bw.write(projekt.getNazev() + "#" + projekt.getPocetResitelu() + "#" + projekt.getNaklady() + "#" + projekt.getHodnoceni()+ "#" + projekt.getDatum() + "#" + projekt.getDatum() + "#" + projekt.getDokonceno() + "\n");
           }





        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void pridejNovyProjekt(){
        String nazev = tfNazev.getText();
        Integer resitele = Integer.parseInt(tfPocetResitelu.getText());
        LocalDate datum = LocalDate.parse(tfDatum.getText());
        BigDecimal naklady = BigDecimal.valueOf(0.0);

        Boolean dokonceno = null;
        if(checkBox1.isSelected()){
            dokonceno = true;
        } else {
            dokonceno = false;
        }

        Integer hodnoceni = 0;
        if (a1RadioButton.isSelected()){
            hodnoceni = 1;
        } else if (a2RadioButton.isSelected()){
            hodnoceni = 2;
        } else if (a3RadioButton.isSelected()){
            hodnoceni =3;
        } else {
            hodnoceni =0;
        }

        Projekt novyProjekt = new Projekt(nazev,resitele,naklady,hodnoceni,datum,dokonceno);
        seznam.add(novyProjekt);

    }


    public void vytvorStatistiku(){
        BigDecimal celkoveNaklady = BigDecimal.valueOf(0);
        for(Projekt projekt : seznam){
            celkoveNaklady = celkoveNaklady.add(projekt.getNaklady());
        }

        JOptionPane.showMessageDialog(this,"Souček veškerých nákladů: " + celkoveNaklady);
    }

}
