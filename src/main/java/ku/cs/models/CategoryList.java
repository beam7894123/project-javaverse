package ku.cs.models;

import java.util.ArrayList;

public class CategoryList {
    //Category add
    private static ArrayList<Category> categories;

    public CategoryList(){
        categories = new ArrayList<>();
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public static ArrayList<Category> getCategories(){
        return categories;
    }
}
