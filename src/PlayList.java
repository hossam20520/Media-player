
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hossam
 */
public class PlayList {
    
     JFileChooser b = new JFileChooser();
      ArrayList ls = new ArrayList();
      public void add(JFrame frame){
       b.setMultiSelectionEnabled(true);
      
      int fileV = b.showOpenDialog(frame);
      if(fileV == javax.swing.JFileChooser.CANCEL_OPTION){
          return;
      }else if (fileV == javax.swing.JFileChooser.APPROVE_OPTION) {
          File[] file = b.getSelectedFiles();
          ls.addAll(Arrays.asList(file));
          
      }
      }
      
    ArrayList getListSong(){
      return ls;
  }
     FileOutputStream fos;
  ObjectOutputStream oos;
  
    public void saveAsPlayList(JFrame frame) throws IOException{
       b.setMultiSelectionEnabled(false);
      int V = b.showSaveDialog(frame);
     
        if(V == javax.swing.JFileChooser.CANCEL_OPTION){
          return;
      }else if (V == javax.swing.JFileChooser.APPROVE_OPTION) {
          File pls = b.getSelectedFile();
           try {
               fos = new FileOutputStream(pls + ".tgr");
               oos = new ObjectOutputStream(fos);
               for(int i=0; i<ls.size(); i++){
                   File tmp=  (File) ls.get(i);
                   oos.writeObject(tmp);
                   
               }
               oos.close();
           } catch (Exception ex) {
               Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
           }
          
          
          
      }
     
  }
    
    
      FileInputStream fis;
     ObjectInputStream ois;
        
     public void openPls(JFrame frame) throws IOException, ClassNotFoundException{
           b.setMultiSelectionEnabled(false);
      int V = b.showOpenDialog(frame);
      if(V== javax.swing.JFileChooser.CANCEL_OPTION){
          return;
      }
      if(V == javax.swing.JFileChooser.APPROVE_OPTION){
          File pls = b.getSelectedFile();
               try {
                   fis = new FileInputStream(pls);
                    
                   ois = new ObjectInputStream(fis);
                   File tmp;
                   while((tmp = (File) ois.readObject())  != null){
                   ls.add(tmp);
               }
                   if((tmp = (File) ois.readObject()) == null){
                   ls.isEmpty();
               }
                   ois.close();
               } catch (Exception ex) {
                   Logger.getLogger(PlayList.class.getName()).log(Level.SEVERE, null, ex);
               }
          
      }
     }
    
    
}
