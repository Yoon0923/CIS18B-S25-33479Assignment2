import java.util.ArrayList;

abstract class Item {
    protected String title;
    protected int publicationYear;

    public Item(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public String getTitle() { return title; }
    public int getPublicationYear() { return publicationYear; }
    
    public abstract void displayInfo();
}

class Book extends Item implements IBorrowable {
    private String author;
    private String ISBN;
    private String borrower;

    public Book(String title, int publicationYear, String author, String ISBN) {
        super(title, publicationYear);
        this.author = author;
        this.ISBN = ISBN;
        this.borrower = null;
    }

    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    
    @Override
    public void borrowItem(String borrower) {
        if (this.borrower == null) {
            this.borrower = borrower;
            System.out.println(title + "- borrowed by " + borrower);
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    @Override
    public void returnItem() {
        if (this.borrower != null) {
            System.out.println(title + " - returned by " + borrower);
            this.borrower = null;
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }

    @Override
    public boolean isBorrowed() {
        return borrower != null;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book: " + title + ", Author: " + author + ", Year: " + publicationYear + ", ISBN: " + ISBN);
    }
}

class Magazine extends Item {
    private int issueNumber;

    public Magazine(String title, int publicationYear, int issueNumber) {
        super(title, publicationYear);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }
    
    @Override
    public void displayInfo() {
        System.out.println("Magazine: " + title + ", Issue: " + issueNumber + ", Year: " + publicationYear);
    }
}

interface IBorrowable {
    void borrowItem(String borrower);
    void returnItem();
    boolean isBorrowed();
}

class Library {
    private static Library instance;
    private ArrayList<Item> items;

    private Library() {
        items = new ArrayList<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void listAvailableItems() {
        for (Item item : items) {
            if (item instanceof Book) {
                if (!((Book) item).isBorrowed()) {
                    item.displayInfo();
                }
            } else {
                item.displayInfo();
            }
        }
    }

    public Item findItemByTitle(String title) {
        for (Item item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null; // No item found
    }
}

class LibraryItemFactory {
    public static Item createItem(String type, String title, int publicationYear, String extraData) {
        try {
            if (type.equalsIgnoreCase("book")) {
                return new Book(title, publicationYear, extraData, "ISBN-" + title.hashCode());
            } else if (type.equalsIgnoreCase("magazine")) {
                return new Magazine(title, publicationYear, Integer.parseInt(extraData));
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid issue number for magazine.");
        }
        return null; // No item found
    }
}
