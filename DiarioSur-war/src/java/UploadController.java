/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

/**
 *
 * @author alberto
 */
@ManagedBean
@SessionScoped
public class UploadController{

    private Part image;
   
    public void doUpload(){
        try{
            InputStream in = image.getInputStream();
            
            File f = new File("/Users/alberto/Documents/upload/"+image.getSubmittedFileName());
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            
            byte[] buffer = new byte[1024];
            int length;
            
            while((length=in.read(buffer))>0){
                out.write(buffer, 0, length);
            }
            
            out.close();
            in.close();
            
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
}
