
import java.util.Arrays;
public class LibraryBooks {

    //   public static void main(String[] args) throws IOException {
    public static void main(String[] args) {
        int[] b_1 = new int[0]; //books listing name
        int[] sortingBase = new int[0];
        boolean ex_prog = true;
        byte [] exit_program = {69, 120, 105, 116, 32, 112, 114, 111, 103, 114, 97, 109, 10, 10};
        byte [] add_title = {65, 100, 100, 32, 116, 105, 116, 108, 101, 10};
        byte [] error_comand = {69, 114, 114, 111, 114, 32, 99, 111, 109, 109, 97, 110, 100, 10};
        byte [] book_name_written = {98, 111, 111, 107, 32, 110, 97, 109, 101, 32, 119, 114, 105, 116, 116, 101, 110, 10, 10};
        byte [] book_name_not_entered = {98, 111, 111, 107, 32, 110, 97, 109, 101, 32, 110, 111, 116, 32, 101, 110, 116, 101, 114, 101, 100, 10, 10};
        byte [] enter_the_symbol_1_to_exit= {101, 110, 116, 101, 114, 32, 116, 104, 101, 32, 115, 121, 109, 98, 111, 108, 32, 40, 49, 41, 32, 116, 111, 32, 101, 120, 105, 116, 10, 10};
        while (ex_prog){
            showMenu ();

            int comand = read_comand();
            int flag = 0;
            switch (comand){

                case 49:   //1
                    int out_case49 = 0;
                    int out_case49_no_write = 0;
                    int[] name_books;
                    //console "Add title"
                    do{
                        System.out.write(add_title, 0, add_title.length);
                        System.out.write(enter_the_symbol_1_to_exit, 0, enter_the_symbol_1_to_exit.length);

                        //re-creating the array, storing the new book name into an array
                        name_books = read_name_books();

                        if (name_books.length == 1) {
                            System.out.write(book_name_not_entered, 0, book_name_not_entered.length);
                            out_case49 = 1;
                        } else {
                            out_case49 = 0;
                            if (name_books[0] == 49 & name_books[1] == 10){
                                out_case49_no_write = 1;
                            }else{
                                if ((48 <= name_books[0]) & (name_books[0] <= 57)){
                                    System.out.println("Book cannot start with a number");
                                    out_case49 = 1;
                                }

                            }
                            out_case49 = chekSimbol(name_books[0] , name_books [1]);
                        }
                    }while (out_case49 != 0 );
                    if (out_case49_no_write == 0) {
                        int[] b_2 = new int[b_1.length + name_books.length];
                        System.arraycopy(name_books, 0, b_2, b_1.length, name_books.length);
                        System.arraycopy(b_1, 0, b_2, 0, b_1.length);
                        b_1 = new int[b_2.length];
                        System.arraycopy(b_2, 0, b_1, 0, b_2.length);
                        System.out.write(book_name_written, 0, book_name_written.length);
                    }
                    break;
                case 50:   //2
                    flag = 1;
                    sortingBase = new int[b_1.length];
                case 51:   //3
                    if (flag == 0){
                        flag = 2;
                    }

                case 52:   //4
                    int fileNumberLow = 0;
                    int fileNumberHigh = 0;
                    int countBooks = 0;
                    for (int ee = 0; ee < b_1.length; ee++) {
                        if (b_1[ee] == 10){
                            if (countBooks != 0){
                                fileNumberLow = fileNumberHigh;
                            }
                            countBooks++;
                            fileNumberHigh = ee+1;
                            System.out.print(countBooks + ". ");
                            if (flag == 1){
                                sortingBase[countBooks] = fileNumberHigh;
                            }



                            for (int read_file_name = fileNumberLow; read_file_name<fileNumberHigh; read_file_name++ ){
                                System.out.write(b_1[read_file_name]);
                            }

                        }

                    }
                    System.out.println("found (" + countBooks + ") book entries");
                    if (flag == 1){
                        System.out.println("enter the number to delete");
                        int comand_delite_data = read_comand();
                        if ((48 <= comand_delite_data) & (comand_delite_data <= 57)){
                            for (int dat = sortingBase[comand_delite_data-49]; dat < sortingBase[comand_delite_data-48]; dat++){
                                b_1[dat]=0;
                            }
                        }

                        System.out.println(Arrays.toString(sortingBase));


                    }
                    if (flag == 2){
                        System.out.println("enter the number to edit");
                        // sortingBase = new int[b_1.length];


                    }
                    break;
                case 53:   //5
                    break;
                case 54:   //6
                    break;
                case 55:   //7
                    System.out.write(exit_program,0,exit_program.length);
                    ex_prog = false;
                    break;
                default:
                    System.out.write(error_comand,0,error_comand.length);
                    break;
            }
        }
    }

    private static int[] read_name_books() {
        byte [] letterWrite = {60, 58};
        int [] x_data = new int[100];
        int a = 0;
        boolean exit_name_books = true;
        System.out.write(letterWrite, 0, letterWrite.length);
        while (exit_name_books) {
            try {
                int letter = System.in.read();

                if (letter == 13){
                    letter = System.in.read();
                }
                if (letter == 10) {
                    x_data[a++] = 10;
                    x_data[a] = 10;
                    exit_name_books = false;
                }
                else {
                    if (a < 98) {
                        x_data[a] = letter;
                        a++;
                    }
                    else {
                        x_data[0] = 0;
                    }
                }



            } catch (java.io.IOException e) {
                a = 0;
                x_data[a] = 0;
                exit_name_books = false;
            }

        }
        int[] name_books = new int[a];
        System.arraycopy (x_data, 0, name_books, 0, a);
        return name_books;

    }

    private static void showMenu ()
    {
        //       byte [] color_red = {27, 91, 51, 49, 109};
        //       byte [] color_gren = {27, 91, 51, 50, 109};
        //       byte [] color_black = {27, 91, 48, 109, 32};

        byte matrix_menu[][] = {
                //************Menu************
                { 31, 2, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 77, 101, 110, 117, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 10 },
                //Enter the number (1-7)
                { 25, 1, 69, 110, 116, 101, 114, 32, 116, 104, 101, 32, 110, 117, 109, 98, 101, 114, 32, 40, 49, 45, 55, 41, 10 },
                //Add the title of the book.
                { 29, 3, 65, 100, 100, 32, 116, 104, 101, 32, 116, 105, 116, 108, 101, 32, 111, 102, 32, 116, 104, 101, 32, 98, 111, 111, 107, 46, 10 },
                //Delete the book by title.
                { 28, 3, 68, 101, 108, 101, 116, 101, 32, 116, 104, 101, 32, 98, 111, 111, 107, 32, 98, 121, 32, 116, 105, 116, 108, 101, 46, 10 },
                //Edit the title of the book.
                { 30, 3, 69, 100, 105, 116, 32, 116, 104, 101, 32, 116, 105, 116, 108, 101, 32, 111, 102, 32, 116, 104, 101, 32, 98, 111, 111, 107, 46, 10 },
                //Show all books.
                { 18, 3, 83, 104, 111, 119, 32, 97, 108, 108, 32, 98, 111, 111, 107, 115, 46, 10 },
                //Check the book by title.
                { 27, 3, 67, 104, 101, 99, 107, 32, 116, 104, 101, 32, 98, 111, 111, 107, 32, 98, 121, 32, 116, 105, 116, 108, 101, 46, 10 },
                //Sorting books.
                { 17, 3, 83, 111, 114, 116, 105, 110, 103, 32, 98, 111, 111, 107, 115, 46, 10 },
                //Exit the program.
                { 20, 3, 69, 120, 105, 116, 32, 116, 104, 101, 32, 112, 114, 111, 103, 114, 97, 109, 46, 10 },
                //*****************************
                { 32, 2, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 10 }
        };
        for (int x = 0; x<10; x++ ){
            int c = 0;
            //          if (matrix_menu[x][1] == 1){
            //              for (c = 0; c<5; c++ ) {
            //                  System.out.write(color_red[c]);
            //             }
            //         }
            //          if (matrix_menu[x][1] == 2){
            //              for (c = 0; c<5; c++ ) {
            //                  System.out.write(color_gren[c]);
            //              }
            //         }
            if (matrix_menu[x][1] == 3){

                //              for (c = 0; c<5; c++ ) {
                //                  System.out.write(color_red[c]);
                //              }
                System.out.print((x-1) + ".");

                //              for (c = 0; c<5; c++ ) {
                //                  System.out.write(color_black[c]);
                //              }
            }
            for(int y = 2; y<matrix_menu[x][0]; y++){
                System.out.write(matrix_menu[x][y]);
            }
        }
    }
    private static int read_comand(){
        byte [] letterWrite = {60, 58};
        int a = 0;
        int b = -1;
        boolean exit_comand = true;
        System.out.write(letterWrite, 0, letterWrite.length);
        while (exit_comand) {
            try {
                int letter = System.in.read();

                if (letter == 13){
                    //  System.out.print(letter +", ");
                    letter = System.in.read();
                }
                // System.out.print(letter+", ");
                if (letter == 10){
                    if (a < 2) {
                        exit_comand = false;
                    }
                    else {
                        b = -1;
                        exit_comand = false;
                    }
                } else {
                    a++;
                    b = letter;
                    exit_comand = true;
                }
            } catch (java.io.IOException e) {
                b = -1; //error
            }
        }
        return b;
    }
    private static int chekSimbol(int simbol1, int simbol2){
        if ((97 <= simbol1) & (simbol1 <= 122)) {
            System.out.println("The first letter must be capital");
            return 1;
        }

        if ((160 <= simbol1) & (simbol1 <= 175)) {
            System.out.println("Rus The first letter must be capital");
            return 1;
        }
        if ((224 <= simbol1) & (simbol1 <= 241)) {
            System.out.println("Rus The first letter must be capital");
            return 1;
        }
        if (simbol1 == 208) {
            if ((176 <= simbol2) & (simbol2 <= 191)) {
                System.out.println("Rus The first letter must be capital");
                return 1;
            }
        }
        if (simbol1 == 209) {
            if ((128 <= simbol2) & (simbol2 <= 145)) {
                System.out.println("Rus The first letter must be capital");
                return  1;
            }
        }
        return 0;
    }


}