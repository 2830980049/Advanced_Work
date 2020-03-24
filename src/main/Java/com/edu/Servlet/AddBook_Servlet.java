package com.edu.Servlet;

import com.edu.domain.Book;
import com.edu.domain.Book_type;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/add.do")
public class AddBook_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = (List<Book>)req.getServletContext().getAttribute("bookList");
        Book book = new Book();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        Map<String,String> map = new HashMap<String,String>();
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        try {
            List<FileItem> list = fileUpload.parseRequest(req);
            for (FileItem fileItem:list){
                if(fileItem.isFormField()){
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    map.put(name,value);
                }
                else{
                    String fileName = fileItem.getName();
                    String uuidFileName = getFileName(fileName);
                    InputStream is = fileItem.getInputStream();
                    String path = req.getSession().getServletContext().getRealPath("/")+"img\\";
                    //"F:\\IDEA Files\\Advanced_Work\\src\\main\\webapp\\img\\"
                    //String filePaths = realPath + "upload/"+ file.getOriginalFilename();
                    System.out.println(path);
                    String url = path + uuidFileName;
                    System.out.println(uuidFileName);
                    map.put("path",uuidFileName.toString());
                    OutputStream os = new FileOutputStream(url);
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1)
                        os.write(b,0,len);
                    is.close();
                    os.close();
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String id = map.get("bookId");
        book.setId(id);
        book.setBook_name(map.get("bookName"));
        List<Book_type> bookTypes = (List<Book_type>)req.getServletContext().getAttribute("bookTypes");
        for (int i = 0; i < bookTypes.size(); i++){
            if(bookTypes.get(i).getId().equals(map.get("categoryId"))){
                book.setType(bookTypes.get(i).getName());
                break;
            }
        }
        System.out.println(map.get("categoryId"));
        book.setPrice(Double.parseDouble(map.get("bookPrice")));
        book.setImg(map.get("path"));
        int len = bookList.size();
        boolean flag = true;
        for (int i = 0; i < len; i++){
            if(bookList.get(i).getId().equals(id)){
                bookList.set(i,book);
                flag = false;
                break;
            }
        }
        if(flag)
            bookList.add(book);
        req.getServletContext().setAttribute("bookList", bookList);
        System.out.println(bookList);
        req.getRequestDispatcher("/bookList.jsp").forward(req,resp);
    }

    public static String getFileName(String fileName){
        int index = fileName.lastIndexOf(".");
        String exName = fileName.substring(index);
        String uuidFileName = UUID.randomUUID().toString().replace("-","")+exName;
        return uuidFileName;
    }
}
