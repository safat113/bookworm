package net.therap.service.impl;

import net.therap.dao.BookDao;
import net.therap.dao.UserPreferenceDao;
import net.therap.domain.Book;
import net.therap.domain.Category;
import net.therap.domain.WishedBook;
import net.therap.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rifatul.islam
 * @author shakhawat.hossain
 * @since 8/7/14.
 */

@Service
@Transactional("transactionManager")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private UserPreferenceDao userPreferenceDao;

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public Book getBookById(int bookId) {
        return bookDao.getBookById(bookId);
    }

    @Override
    public List<Category> getAllCategory() {
        return bookDao.getAllCategory();
    }

    @Override
    public void removePostedBookById(int postedBookId) {
        bookDao.removePostedBookById(postedBookId);
    }

    @Override
    public void removeWishedBookById(int wishedBookId) {
        bookDao.removeWishedBookById(wishedBookId);
    }

    @Override
    public List<Book> getRecentlyPostedBooks() {
        return bookDao.getRecentlyPostedBooks();
    }

    @Override
    public List<Book> getBooksBySearchKey(String searchKey) {
        return bookDao.getBooksBySearchKey(searchKey);
    }


    @Override
    public List<Book> getBooksByAuthorOrTitle(String title, String author) {
    return bookDao.getBooksByAuthorOrTitle(title, author);
    }

    @Override
    public int addWishedBookAndGetId(WishedBook wishedBook) {
        return bookDao.addWishedBookAndGetId(wishedBook);
    }

    @Override
    public List<Book> getUserPreferredBookList() {
        return userPreferenceDao.getUserPreferredBookList();
    }
}
