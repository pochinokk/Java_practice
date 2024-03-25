package practice6;

public class Book {
    private final String name;
    private final String author;
    private final int year;
    private final int number_of_pages;

    public Book(String name, String author, int year, int number_of_pages) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.number_of_pages = number_of_pages;
    }
    public static BookBuilder builder(){
        return new BookBuilder();
    }

    public static class BookBuilder{
        private  String name;
        private  String author;
        private  int year;
        private  int number_of_pages;

        public BookBuilder name(String name){
            this.name = name;
            return this;
        }
        public BookBuilder author(String author){
            this.author = author;
            return this;
        }
        public BookBuilder year(int year){
            this.year = year;
            return this;
        }
        public BookBuilder number_of_pages(int number_of_pages){
            this.number_of_pages = number_of_pages;
            return this;
        }

        public Book build(){
            return new Book(name, author, year, number_of_pages);
        }
    }

    @Override
    public String toString()
    {
        return "Книга" +
                "\nname: " + this.name +
                "\nauthor: " + this.author +
                "\nyear: " + this.year +
                "\nnumber_of_pages: " + this.number_of_pages;
    }

}
