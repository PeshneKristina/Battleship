package ru.spbu.apmath.prog.hw2.task3;

import java.util.ArrayList;

public class Library {
    // Сюда надо добавить недостающие методы

    private String address;
    private ArrayList<Book> library = new ArrayList<>();

    public Library(String address) {
        this.address = address;
    }

    public void addBook(Book book) {
        if (library.indexOf(book) == -1) {
            library.add(book);
            System.out.println("Вы добавили книгу " + book.getTitle() + " в библиотеку");
        } else {
            System.out.println("Эта книга уже есть в библиотеке.");
        }
    }

    public void printAddress() {
        System.out.println(address);
    }

    public void borrowBook(String title) {
        int i = library.indexOf(new Book(title));
        if (i != -1) {
            if (!library.get(i).isBorrowed()) {
                library.get(i).borrowed();
                System.out.println("Вы взяли книгу: " + title);

            } else {
                System.out.println("Эту книгу уже взяли до Вас");
            }
        } else {
            System.out.println("Этой книги нет в этой библиотеке.");
        }
    }

    private void printAvailableBooks() {
        if (library.isEmpty()) {
            System.out.println("В библиотеке пусто.");
        }
        for (Book book : library) {
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle());
            }
        }
    }

    private void returnBook(String title) {
        for (Book book : library) {
            if (book.getTitle() == title) {
                if (book.isBorrowed()) {
                    book.returned();
                    System.out.println("Вы вернули книгу: " + title);
                } else {
                    System.out.println("Эту книгу не брали.");
                }
            }
        }
    }


    public static void main(String[] args) {
        // Создаем две библиотеки
        Library firstLibrary = new Library("Университетский пр., 120");
        Library secondLibrary = new Library("Московский пр., 86");

        // Добавляем четыре книги в первую библиотеку
        firstLibrary.addBook(new Book("Код да Винчи")); // При добавлении на экран должно выводиться сообщение об успешном добавлении соответствующей книги
        firstLibrary.addBook(new Book("50 оттенков серого"));
        firstLibrary.addBook(new Book("Учебник мемологии"));
        firstLibrary.addBook(new Book("Властелин Колец"));

        // Выводим на экран часы работы и адреса
        System.out.println("Часы работы библиотек:");
        printOpeningHours(); // Что-то типа "Все библиотеки работают с понедельника по субботу с 9 до 18", текст на ваше усмотрение
        System.out.println();

        System.out.println("Адреса библиотек:");
        firstLibrary.printAddress(); // Выводит адрес
        secondLibrary.printAddress();
        System.out.println();

        // Пытаемся взять Властелина Колец из обеих библиотек
        System.out.println("Берем лучшую книгу на земле:");
        firstLibrary.borrowBook("Властелин Колец");   // Должно пройти успешно, мы должны получить соответствующее сообщение об успехе
        firstLibrary.borrowBook("Властелин Колец");   // Книга уже взята, об этом нам должны написать
        secondLibrary.borrowBook("Властелин Колец");  // Такой книги нет в каталоге, это тоже отдельное сообщение для нас
        System.out.println();

        // Выводим названия всех книг в обеих библиотеках
        System.out.println("Доступные книги в первой библиотеке:");
        firstLibrary.printAvailableBooks();  // Только список книг, которые можно взять
        System.out.println();
        System.out.println("Доступные книги во второй библиотеке:");
        secondLibrary.printAvailableBooks(); // Так как во вторую книг не добавляли, то тут надо написать что-то типа "В каталоге пусто"
        System.out.println();

        // Возвращаем Властелина Колец в первую библиотеку
        System.out.println("Прочитали Властелина Колец, возвращаем:");
        firstLibrary.returnBook("Властелин Колец"); // Сообщение об успешном возвращении
        System.out.println();

        // Снова выводим список доступных книг в первой библиотеке
        System.out.println("Доступные книги в первой библиотеке:");
        firstLibrary.printAvailableBooks();
    }

    public static void printOpeningHours() {
        System.out.println("Все библиотеки работают с понедельника по субботу с 9 до 18");
    }


}
