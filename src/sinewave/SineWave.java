package sinewave;

import javax.swing.JFrame;

public class SineWave {
 
  public static void main(String[] args) {
      //ACS.main(args);
      
      
      JFrame mainFrame = new MainWindow();
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setResizable(false);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setVisible(true);
      mainFrame.setTitle("ECG Data");
  }
}
