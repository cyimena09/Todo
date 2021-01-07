package be.ifosup.category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    List<Category> getCategories() throws SQLException;

}
