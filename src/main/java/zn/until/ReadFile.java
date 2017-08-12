/**
 * 
 */
package zn.until;

import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.File;  
  
public class ReadFile {  
  
	   /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    public  static void doDeleteEmptyDir(String dir) {
       (new File(dir)).delete();
     
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    public static  boolean deleteFile(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteFile(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

  
 /** 
  * 输出某个文件夹下的所有文件夹和文件路径 
  * 
  * @param delpath 
  *            String 
  * @throws FileNotFoundException 
  * @throws IOException 
  * @return boolean 
  */  
 public static boolean readfile(String filepath)  
   throws FileNotFoundException, IOException {  
  try {  
  
   File file = new File(filepath);  
   System.out.println("遍历的路径为：" + file.getAbsolutePath());  
   // 当且仅当此抽象路径名表示的文件存在且 是一个目录时（即文件夹下有子文件时），返回 true  
   if (!file.isDirectory()) {  
    System.out.println("该文件的绝对路径：" + file.getAbsolutePath());  
    System.out.println("名称：" + file.getName());  
   } else if (file.isDirectory()) {  
    // 得到目录中的文件和目录  
    String[] filelist = file.list();  
    if (filelist.length == 0) {  
     System.out.println(file.getAbsolutePath()  
       + "文件夹下，没有子文件夹或文件");  
    } else {  
     System.out  
       .println(file.getAbsolutePath() + "文件夹下，有子文件夹或文件");  
    }  
    for (int i = 0; i < filelist.length; i++) {  
     File readfile = new File(filepath + "\\" + filelist[i]);  
     System.out.println("遍历的路径为：" + readfile.getAbsolutePath());  
     if (!readfile.isDirectory()) {  
      System.out.println("该文件的路径："  
        + readfile.getAbsolutePath());  
      System.out.println("名称：" + readfile.getName());  
     } else if (readfile.isDirectory()) {  
      System.out.println("-----------递归循环-----------");  
      readfile(filepath + "\\" + filelist[i]);  
     }  
    }  
  
   }  
  
  } catch (FileNotFoundException e) {  
 
  }  
  return true;  
 }  
  
 
 
 
}  