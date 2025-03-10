import java.util.Scanner;

public class LibraryTest {
    public static void main(String[] args) {
        Library library = Library.getInstance();
        
        Item book1 = LibraryItemFactory.createItem("book", "Harry Potter 1", 1997, "J.K. Rowling");
        Item book2 = LibraryItemFactory.createItem("book", "The Lord of the Rings", 1954, "J.R.R. Tolkien");
        Item magazine1 = LibraryItemFactory.createItem("magazine", "Vogue", 2025, "3");

        library.addItem(book1);
        library.addItem(book2);
        library.addItem(magazine1);

        System.out.println("Available items in the library:");
        library.listAvailableItems();

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the title of the book to borrow: ");
        String titleToBorrow = scanner.nextLine();
        Item itemToBorrow = library.findItemByTitle(titleToBorrow);
        if (itemToBorrow instanceof Book) {
            System.out.print("Enter your name: ");
            String borrowerName = scanner.nextLine();
            ((Book) itemToBorrow).borrowItem(borrowerName);
        } else {
            System.out.println("Item not found.");
        }

        System.out.print("Enter the title of the book to return: ");
        String titleToReturn = scanner.nextLine();
        Item itemToReturn = library.findItemByTitle(titleToReturn);
        if (itemToReturn instanceof Book) {
            ((Book) itemToReturn).returnItem();
        } else {
            System.out.println("Item not found.");
        }
        
        System.out.println("Updated list of available items:");
        library.listAvailableItems();

        scanner.close();
    }
}
