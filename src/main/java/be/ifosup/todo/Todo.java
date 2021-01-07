package be.ifosup.todo;

public class Todo {
    // ATTRIBUTES
    private Long id;
    private String description;
    private Long FK_category;
    private String category;

    // CONSTRUCTOR
    public Todo(Long id, String description, Long FK_category, String category){
        this.id = id;
        this.description = description;
        this.FK_category = FK_category;
        this.category = category;
    }

    // GETTER AND SETTER
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFK_category() {
        return FK_category;
    }

    public void setFK_category(Long FK_category) {
        this.FK_category = FK_category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
