
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class Editor extends JFrame {
 private JTextArea textArea;
 public Editor() {
 setTitle("Editor");
 setSize(800, 600);
 setDefaultCloseOperation(EXIT_ON_CLOSE);
 textArea = new JTextArea();
 JScrollPane scrollPane = new JScrollPane(textArea);
 add(scrollPane, BorderLayout.CENTER);
 JMenuBar menuBar = new JMenuBar();
 JMenu fileMenu = new JMenu("File");
 JMenuItem openMenuItem = new JMenuItem("Open");
 JMenuItem saveMenuItem = new JMenuItem("Save");
 JMenuItem exitMenuItem = new JMenuItem("Exit");
 openMenuItem.addActionListener(new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 JFileChooser fileChooser = new JFileChooser();
 FileNameExtensionFilter filter = new FileNameExtensionFilter(
 "Text files", "txt");
 fileChooser.setFileFilter(filter);
 int returnVal = fileChooser.showOpenDialog(Editor.this);
 if (returnVal == JFileChooser.APPROVE_OPTION) {
 File file = fileChooser.getSelectedFile();
 try {
 BufferedReader br = new BufferedReader(new FileReader(file));
 textArea.read(br, null);
 br.close();
 } catch (IOException ex) {
 ex.printStackTrace();
 }
 }
 }
 });
 saveMenuItem.addActionListener(new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 JFileChooser fileChooser = new JFileChooser();
 int returnVal = fileChooser.showSaveDialog(Editor.this);
 if (returnVal == JFileChooser.APPROVE_OPTION) {
 File file = fileChooser.getSelectedFile();
 try {
 BufferedWriter bw = new BufferedWriter(new FileWriter(file));
 textArea.write(bw);
 bw.close();
 } catch (IOException ex) {
 ex.printStackTrace();
 }
 }
 }
 });
 exitMenuItem.addActionListener(new ActionListener() {
 public void actionPerformed(ActionEvent e) {
 System.exit(0);
 }
 });
 fileMenu.add(openMenuItem);
 fileMenu.add(saveMenuItem);
 fileMenu.addSeparator();
 fileMenu.add(exitMenuItem);
 menuBar.add(fileMenu);
 setJMenuBar(menuBar);
 }
 public static void main(String[] args) {
 SwingUtilities.invokeLater(new Runnable() {
 public void run() {
 new Editor().setVisible(true);
 }
 });
 }
}
