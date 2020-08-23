import java.util.Arrays;

public class LibraryBooks {
    private static int flag = 0;
    private static int[] readDataConsole = {0};
    private static int[] baseOne = {10};
    private static int[] sortingIndexOne = {0};

    public static void main(String[] args) {
        do{
            //вывести основное меню
            showMenu ();
            //запросить входные данные
            readConsole ();
            //проверить входные данные на наличие инородных примесей.
            checkCharOrNumber (); //если вернет flag 0 то число, если вернет flag 1 то символы
            //проверить входные данные на наличие командного числа
            if ((flag & (1 << 1)) == 0){
                //проверить количество цифр в масиве если больше одной то это неверная команда
                if (readDataConsole.length == 2){
                    //обработка команд
                    switch (readDataConsole[0]){
                        case  49:  //1
                            flag &= ~ (1 << 2); //сбросить флаг
                            do {
                                System.out.println("Enter name book");
                                System.out.println("or number (0) to enter the menu");
                                processingMethodOneCommand();
                            }while((flag & (1 << 2)) == 0); //
                            break;

                        case  50: //2
                            flag &= ~ (1 << 3); //сбросить флаг
                            do {
                                System.out.println("Delete book name");
                                processingMethodTwoCommand ();
                            }while((flag & (1 << 3)) == 0); //
                            break;

                        case  51:  //3
                            // processingMethodThreeCommand ();
                            break;

                        case  52:  //4
                            flag &= ~ (1 << 5); //сбросить флаг
                            do {
                                System.out.println("display the title of books");
                                processingMethodFourCommand ();
                            }while ((flag & (1 << 5)) == 0);
                            break;

                        case  53:  //5
                            //  processingMethodFiveCommand ();
                            break;

                        case  54:  //6
                            //  processingMethodSixCommand ();
                            break;

                        case  55:  //7
                            flag |= (1 << 0);  //установить флаг выхода
                            System.out.println("Exit program");
                            break;

                        default: //error comand
                            System.out.println("Error command");
                            break;
                    }

                } else{
                    System.out.println("Error command");
                }
            }else {
                System.out.println("Error command");
            }

        }while ((flag & (1 << 0))==0);
    }

    public  static void processingMethodOneCommand(){
        //запросить входные данные
        readConsole();
        //проверить входные данные на наличие инородных примесей.
        checkCharOrNumber(); //если вернет flag 0 то число, если вернет flag 1 то символы
        //проверить входные данные на наличие командного числа
        if ((flag & (1 << 1)) == 0) {
            //проверить количество цифр в масиве если больше одной то это неверная команда
            if (readDataConsole.length == 2) {
                if (readDataConsole[0] == 48){
                    flag |= (1 << 2);  //установить флаг выхода из обработчика первой команды
                }
            }
        }

        if ((flag & (1 << 2)) == 0){
            //проверить введеное значение в консоле на правельность символов
            //начало имени должно быть заглавными буквами и не должны быть цифрами и спец символы.
            cheсkSimbol();
            if ((flag & (1 << 8)) == 0) {
                System.out.println("ok");
                // проверить на присутствие в базе
                checkBaseBooks ();
                if ((flag & (1 << 9)) == 0){
                    // добавление записи книги
                    writeDatabase ();
                    System.out.println("book added successfully");
                }else {
                    System.out.println("such a book already exists");
                }



            }else {
                System.out.println("be careful");
            }
        }
    }
    private  static void writeDatabase (){
        //создаем временное хранилище
        int[] baseTemp = new int[baseOne.length + readDataConsole.length];
        //копируем в него полученные с консоли данные
        System.arraycopy(readDataConsole, 0, baseTemp, baseOne.length, readDataConsole.length);
        //копируем в него старые данные из основного хранилища
        System.arraycopy(baseOne, 0, baseTemp, 0, baseOne.length);
        //пересоздаем основное хранилище равным размеру временного хранилища
        baseOne = new int[baseTemp.length];
        //копируем в новопересозданое хранилище данные из временного
        System.arraycopy(baseTemp, 0, baseOne, 0, baseTemp.length);

        //создаем запись в сортировочном хранилище
        int[] sortingIndexOneTemp = new int[sortingIndexOne.length + 1];
        System.arraycopy(sortingIndexOne, 0, sortingIndexOneTemp, 0, sortingIndexOne.length);
       // sortingIndexOneTemp[sortingIndexOne.length] = 0;
        sortingIndexOneTemp[sortingIndexOne.length] = baseTemp.length;
        //увеличиваем количество книг
        sortingIndexOneTemp[0] = sortingIndexOneTemp[0] + 1;
        sortingIndexOne = new int[sortingIndexOneTemp.length];
        System.arraycopy(sortingIndexOneTemp, 0, sortingIndexOne, 0, sortingIndexOneTemp.length);

        System.out.println(Arrays.toString(sortingIndexOne));
        System.out.println(Arrays.toString(baseOne));

    }

    private  static void processingMethodTwoCommand (){
        if ((flag & (1 << 3)) == 0) {
            if (sortingIndexOne[0] == 0){
                System.out.println("there are no books in the database");
                System.out.println("or number (0) to enter the menu");
                //запросить входные данные
                readConsole();
                //проверить входные данные на наличие инородных примесей.
                checkCharOrNumber(); //если вернет flag 0 то число, если вернет flag 1 то символы
                //проверить входные данные на наличие командного числа
                if ((flag & (1 << 1)) == 0) {
                    //проверить количество цифр в масиве если больше одной то это неверная команда
                    if (readDataConsole.length == 2) {
                        if (readDataConsole[0] == 48){
                            flag |= (1 << 3);  //установить флаг выхода из обработчика первой команды
                        }
                    }
                }
            }else{
                for (int sIO = 1; sIO <= sortingIndexOne[0];sIO++) {
                    if (sIO == 1) {
                        System.out.write(sIO + 48);
                        System.out.write(46);
                        System.out.write(32);
                        for (int readName = 1; readName < sortingIndexOne[sIO]; readName++) {
                            System.out.write(baseOne[readName]);
                        }

                    } else {
                        System.out.write(sIO + 48);
                        System.out.write(46);
                        System.out.write(32);
                        for (int readName = sortingIndexOne[sIO - 1]; readName < sortingIndexOne[sIO]; readName++) {
                            System.out.write(baseOne[readName]);
                        }
                    }
                }
                System.out.println("in the database of ("+ sortingIndexOne[0] + ") books");
                System.out.println("or number (0) to enter the menu");
                //запросить входные данные
                readConsole();
                //проверить входные данные на наличие инородных примесей.
                checkCharOrNumber(); //если вернет flag 0 то число, если вернет flag 1 то символы
                //проверить входные данные на наличие командного числа
                if ((flag & (1 << 1)) == 0) {
                    if ((2 <= readDataConsole.length) & (readDataConsole.length <= 11)) {
                        if ((readDataConsole[0] == 48) & (readDataConsole.length == 2)){
                            flag |= (1 << 3);  //установить флаг выхода из обработчика первой команды
                        }else {
                            int inputDataIndex = 0;
                            int mulIndex = 0;
                            for (int index = readDataConsole.length-2; index>=0; index--){
                                if ((mulIndex = mulIndex * 10) == 0){
                                    mulIndex = 1;
                                }
                                inputDataIndex = inputDataIndex + ((readDataConsole[index]-48)*mulIndex);
                            }
                            //проверяем есть ли такой номер книги
                            if (sortingIndexOne[0] >= inputDataIndex){
                               // создаем резервный массив
                                int [] baseTemp = new int[baseOne.length];
                                //затем копируем в этот массив все данные до
                                // полученного числа -1
                                System.arraycopy(baseOne, 0, baseTemp, 0, sortingIndexOne[inputDataIndex-1]);


                                if (inputDataIndex == sortingIndexOne[0]){
                                    baseOne = new int[baseTemp.length-(sortingIndexOne[inputDataIndex]-sortingIndexOne[inputDataIndex-1])];
                                    System.arraycopy(baseTemp, 0, baseOne, 0, baseOne.length);
                                    sortingIndexOne[0] = sortingIndexOne[0]-1;
                                    int[] sortingIndexOneTemp = new int[sortingIndexOne.length-1];
                                    System.arraycopy(sortingIndexOne, 0, sortingIndexOneTemp, 0, sortingIndexOneTemp.length);
                                    sortingIndexOne = new int[sortingIndexOneTemp.length];
                                    System.arraycopy(sortingIndexOneTemp, 0, sortingIndexOne, 0, sortingIndexOne.length);
                                }else {
                                    int dfg = (sortingIndexOne[sortingIndexOne.length-1]-sortingIndexOne[inputDataIndex]);
                                    if (inputDataIndex-1 == 0){
                                        System.arraycopy(baseOne, sortingIndexOne[inputDataIndex], baseTemp, 1, dfg);
                                        baseOne = new int[baseTemp.length-(sortingIndexOne[inputDataIndex]-1)];

                                    }else {

                                        System.arraycopy(baseOne, sortingIndexOne[inputDataIndex], baseTemp, sortingIndexOne[inputDataIndex-1], dfg);
                                        baseOne = new int[baseTemp.length-(sortingIndexOne[inputDataIndex]-sortingIndexOne[inputDataIndex-1])];
                                    }

                                    System.arraycopy(baseTemp, 0, baseOne, 0, baseOne.length);
                                    sortingIndexOne[0] = sortingIndexOne[0]-1;
                                    int[] sortingIndexOneTemp = new int[sortingIndexOne.length+1];
                                    System.arraycopy(sortingIndexOne, 0, sortingIndexOneTemp, 0, sortingIndexOne.length);
                                    System.out.println(Arrays.toString(sortingIndexOneTemp));
                                    if (inputDataIndex-1 == 0) {
                                        dfg = sortingIndexOneTemp[inputDataIndex] - 1;
                                    }else{
                                        dfg = sortingIndexOneTemp[inputDataIndex] - sortingIndexOneTemp[inputDataIndex - 1];
                                    }
                                    System.out.println(dfg);
                                    for (int index = inputDataIndex;index<sortingIndexOneTemp.length-2;index++ ){

                                        sortingIndexOneTemp[index] = sortingIndexOneTemp[index+1] - dfg;
                                    }
                                    sortingIndexOne = new int[sortingIndexOneTemp.length-2];
                                    System.arraycopy(sortingIndexOneTemp, 0, sortingIndexOne, 0, sortingIndexOne.length);
                                }

                                System.out.println(Arrays.toString(sortingIndexOne));

                                System.out.println(Arrays.toString(baseOne));

                            }else{
                                System.out.println("вы ошиблись такого номера книги нет");
                            }
                        }

                    }

                }
            }
        }

    }
    private  static void processingMethodFourCommand (){

        if ((flag & (1 << 5)) == 0){
            if (sortingIndexOne[0] == 0){
                System.out.println("there are no books in the database");
            }else{
                System.out.println("in the database of ("+ sortingIndexOne[0] + ") books");
                for (int sIO = 1; sIO <= sortingIndexOne[0];sIO++){
                    if (sIO == 1) {
                        System.out.write(sIO+48);
                        System.out.write(46);
                        System.out.write(32);
                        for (int readName = 1; readName < sortingIndexOne[sIO];readName++){
                            System.out.write(baseOne[readName]);
                        }

                    }else{
                        System.out.write(sIO+48);
                        System.out.write(46);
                        System.out.write(32);
                        for (int readName = sortingIndexOne[sIO - 1]; readName < sortingIndexOne[sIO];readName++) {
                            System.out.write(baseOne[readName]);
                        }
                    }
                }
            }
        }
        System.out.println("or number (0) to enter the menu");
        //запросить входные данные
        readConsole();
        //проверить входные данные на наличие инородных примесей.
        checkCharOrNumber(); //если вернет flag 0 то число, если вернет flag 1 то символы
        //проверить входные данные на наличие командного числа
        if ((flag & (1 << 1)) == 0) {
            //проверить количество цифр в масиве если больше одной то это неверная команда
            if (readDataConsole.length == 2) {
                if (readDataConsole[0] == 48){
                    flag |= (1 << 5);  //установить флаг выхода из обработчика первой команды
                }
            }
        }

    }



    private  static void checkBaseBooks (){

    }
    private  static void checkCharOrNumber (){
        boolean triger = true;
        for (int count = 0; count<readDataConsole.length-1; count++){
            if (triger) {
                if (readDataConsole[count] == 48) {
                    if (readDataConsole[count+1] == 10){
                        flag &= ~ (1 << 1); //сбросить флаг
                        break;
                    }else {
                        flag |= (1 << 1);  //установить флаг
                        break;
                    }
                }
            }
            if ((48 <= readDataConsole[0]) & (readDataConsole[0] <= 57)){
                triger = false;
                flag &= ~ (1 << 1); //сбросить флаг
            }
            else {
                flag |= (1 << 1);  //установить флаг
                break;
            }

        }

    }

    private  static void readConsole (){
        byte [] letterWrite = {60, 58};
        int [] inputData = new int[100];
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
                    inputData[a++] = 10;
                    inputData[a] = 10;
                    exit_name_books = false;
                }
                else {
                    if (a < 98) {
                        inputData[a] = letter;
                        a++;
                    }
                    else {
                        inputData[0] = 0;
                    }
                }

            } catch (java.io.IOException e) {
                a = 0;
                inputData[a] = 0;
                exit_name_books = false;
            }
        }
        readDataConsole = new int[a];
        System.arraycopy (inputData, 0, readDataConsole, 0, a);
    }


    private static void showMenu (){
        //       byte [] colorRed = {27, 91, 51, 49, 109};
        //       byte [] colorGreen = {27, 91, 51, 50, 109};
        //       byte [] colorBlack = {27, 91, 48, 109, 32};

        byte matrixArrayMenu[][] = {
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
            //          if (matrixArrayMenu[x][1] == 1){
            //              for (c = 0; c<5; c++ ) {
            //                  System.out.write(colorRed[c]);
            //             }
            //         }
            //          if (matrixArrayMenu[x][1] == 2){
            //              for (c = 0; c<5; c++ ) {
            //                  System.out.write(colorGreen[c]);
            //              }
            //         }
            if (matrixArrayMenu[x][1] == 3){

                //              for (c = 0; c<5; c++ ) {
                //                  System.out.write(colorRed[c]);
                //              }
                System.out.print((x-1) + ".");

                //              for (c = 0; c<5; c++ ) {
                //                  System.out.write(colorBlack[c]);
                //              }
            }
            for(int y = 2; y<matrixArrayMenu[x][0]; y++){
                System.out.write(matrixArrayMenu[x][y]);
            }
        }

    }
    private static void cheсkSimbol(){

        flag &= ~ (1 << 8); //сбросить флаг
        if (readDataConsole.length > 1) {     //проверяем длину считанного слова отлавливаем пустую строку
            int simbol1 = readDataConsole[0];
            int simbol2 = readDataConsole[1];

            if ((97 <= simbol1) & (simbol1 <= 122)) {
                System.out.println("The first letter must be capital");
                flag |= (1 << 8);  //установить флаг некоректности символа;
            }

            if ((160 <= simbol1) & (simbol1 <= 175)) {
                System.out.println("Rus The first letter must be capital");
                flag |= (1 << 8);  //установить флаг некоректности символа;
            }
            if ((224 <= simbol1) & (simbol1 <= 241)) {
                System.out.println("Rus The first letter must be capital");
                flag |= (1 << 8);  //установить флаг некоректности символа;
            }
            if (simbol1 == 208) {
                if ((176 <= simbol2) & (simbol2 <= 191)) {
                    System.out.println("Rus The first letter must be capital");
                    flag |= (1 << 8);  //установить флаг некоректности символа;
                }
            }
            if (simbol1 == 209) {
                if ((128 <= simbol2) & (simbol2 <= 145)) {
                    System.out.println("Rus The first letter must be capital");
                    flag |= (1 << 8);  //установить флаг некоректности символа;
                }
            }
            if ((48 <= simbol1) & (simbol1 <= 57)) {
                System.out.println("numbers at the beginning of the name cannot be used");
                flag |= (1 << 8);  //установить флаг некоректности символа;
            }
            if ((91 <= simbol1) & (simbol1 <= 96)) {
                System.out.println("special characters cannot be used");
                flag |= (1 << 8);  //установить флаг некоректности символа; спец символы
            }
            if ((58 <= simbol1) & (simbol1 <= 64)) {
                System.out.println("special characters cannot be used");
                flag |= (1 << 8);  //установить флаг некоректности символа; спец символы
            }
            if ((123 <= simbol1) & (simbol1 <= 126)) {
                System.out.println("special characters cannot be used");
                flag |= (1 << 8);  //установить флаг некоректности символа; спец символы
            }
            if ((32 <= simbol1) & (simbol1 <= 47)) {
                System.out.println("special characters cannot be used");
                flag |= (1 << 8);  //установить флаг некоректности символа; спец символы
            }

        }else {
            flag |= (1 << 8);  //установить флаг некоректности символа;
        }
    }
}
