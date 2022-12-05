import java.sql.SQLException;

public class Main {
    public static void main(String[] argv) throws SQLException{
        //dbcon = new DBContext();
        dao = new BookStoreDAO();
        dao.createTable();
        dao.setConn(dbcon.getConnection());
        
        Book book = new Book();
        book.setISBN("978-0544003415");
        book.setTitle("The Lord of the Rings");
        book.setPages(1216);
        dao.insertBook(book);
        
        List<Book> books = dao.getAllBooks();

    }
}
