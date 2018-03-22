/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaulterconsumer;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author imsil
 */
public class TestConsumer {

    static Consumer[] consumer = new Consumer[10];
    static Consumer[] cpyConsumer;

    public static void createStub() {
        consumer[0] = new Consumer(16543038144L, "MURJIBHAI", "PATEL", "BRAMPTON", 3856.87, 2017, 07, 29);
        consumer[1] = new Consumer(16543038152L, "SUBHASCHANDRA", "SHANKARLAL", "SCARBOROUGH", 2807.6, 2016, 06, 17);
        consumer[2] = new Consumer(16543038160L, "ANKIT", "SHAH", "BRAMPTON", 2290.65, 2015, 05, 18);
        consumer[3] = new Consumer(16543038179L, "CHANDRAKANT", "PATI", "MISSISUAGA", 948.56, 2014, 04, 19);
        consumer[4] = new Consumer(16543038187L, "MAHENDRABHAI", "SHAH", "SCARBOROUGH", 70.01, 2013, 02, 20);
        consumer[5] = new Consumer(16543038195L, "JYOTIBEN", "MAHETA", "BRAMPTON", 4265.8, 2013, 02, 21);
        consumer[6] = new Consumer(16543038209L, "DINESHBHAI", "PATEL", "BRAMPTON", 5671.94, 2014, 03, 22);
        consumer[7] = new Consumer(16543038217L, "ASHOKBHAI", "PATEL", "SCARBOROUGH", 5033.44, 2015, 04, 23);
        consumer[8] = new Consumer(16543038225L, "BELABEN", "TRIVEDI", "BRAMPTON", 5849.78, 2016, 05, 24);
        consumer[9] = new Consumer(16543038233L, "MANISHBHAI", "TRIVEDI", "MISSISUAGA", 1313.61, 2017, 07, 25);
    }

    public static void main(String[] args) {
        System.out.println("Hydro Consumer Database Management System");

        createStub();
        cpyConsumer = new Consumer[10];

        cpyConsumer = Arrays.copyOf(consumer, 10);
        int intChoice;
        do {
            intChoice = operationChoice();

            switch (intChoice) {

                case 1:
                    consumerAdd();//Add new record to database
                    break;

                case 2:
                    consumerEdit();
                    break;

                case 3:
                    consumerListDisplay(consumerDateFilter(3));
                    break;

                case 4:
                    consumerListDisplay(consumerDateFilter(4));//Display defaulter consumer in descending order
                    break;

                case 5:
                    consumerListDisplay(consumerDateFilter(5));//Display Regular consumer in ascending order 
                    break;

                case 6:
                    consumerListDisplay(consumerDateFilter(6));//Display Regular consumer in descending order
                    break;

                case 7:
                    consumerListDisplay(consumerBillAmountFilter(7));//Display consumer having > bill Amount in ascending order 
                    break;

                case 8:
                    consumerListDisplay(consumerBillAmountFilter(8));//Display consumer having > bill Amount in descending order
                    break;

                case 9:
                    consumerListDisplay(consumerBillAmountFilter(9));//Display consumer having <= bill Amount in ascending order
                    break;

                case 10:
                    consumerListDisplay(consumerBillAmountFilter(10));//Display consumer having <= bill amount in descending order
                    break;
                case 11:
                    consumerSearchByField(); //Performs serach operation based on selected field
                    break;
                case 12:
                    consumerListDisplay(cpyConsumer); //Displays the master Database
                    break;
            }
        } while (intChoice != 0);
    }

    public static int searchChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Search by consumer ID"
                + "\n2. Search by first name"
                + "\n3. Search by last name"
                + "\n4. Search by city"
                + "\n0. Exit");
                System.out.print("Choice(0-4) : ");

        return scanner.nextInt();
    }

    public static Consumer[] searchByFirstName(String searchFirstName) {
        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        int count = 0;
        for (int i = 0; i < cpyConsumer.length; i++) {
            if (searchFirstName.equals(cpyConsumer[i].getConsumerFirst())) {
                tempConsumer[count++] = cpyConsumer[i];
            }
        }
        if (count > 0) {
            return Arrays.copyOf(tempConsumer, count);
        } else {
            return null;
        }
    }

    public static Consumer[] searchByLastName(String searchLastName) {
        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        int count = 0;
        for (int i = 0; i < cpyConsumer.length; i++) {
            if (searchLastName.equals(cpyConsumer[i].getConsumerLast())) {
                tempConsumer[count++] = cpyConsumer[i];
            }
        }
        if (count > 0) {
            return Arrays.copyOf(tempConsumer, count);
        } else {
            return null;
        }
    }

    public static Consumer[] searchByAddress(String searchAddress) {
        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        int count = 0;
        for (int i = 0; i < cpyConsumer.length; i++) {
            if (searchAddress.equals(cpyConsumer[i].getConsumerAddress())) {
                tempConsumer[count++] = cpyConsumer[i];
            }
        }
        if (count > 0) {
            return Arrays.copyOf(tempConsumer, count);
        } else {
            return null;
        }
    }

    public static void consumerSearchByField() {
        int usrChoice = searchChoice();
        int[] index = new int[cpyConsumer.length];
        Scanner scanner = new Scanner(System.in);
        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        String cNum;
        while (usrChoice != 0) {
            switch (usrChoice) {
                case 1:
                    System.out.print("Enter 11-dig consumer number : ");
                    do {
                        scanner = new Scanner(System.in);
                        cNum = scanner.nextLine();
                        if (!validateConsumerID(cNum)) {
                            System.out.print("Consumer ID must be 11 digits and numeric type : ");
                        } else {
                            break;
                        }
                    } while (true);
                    if (checkByID(cNum, index)) {
                        tempConsumer = new Consumer[1];
                        tempConsumer[0] = cpyConsumer[index[0]];
                        consumerListDisplay(tempConsumer);
                    } else {
                        System.out.println("No records found.");
                    }
                    break;
                case 2:
                    do {
                        scanner = new Scanner(System.in);
                        System.out.printf("\nConsumerFirstName : ");
                        cNum = scanner.nextLine().toUpperCase();
                        if (validateString(cNum)) {
                            break;
                        } else {
                            System.out.println("Only alphabets accepted");
                        }
                    } while (true);
                    consumerListDisplay(searchByFirstName(cNum));
                    break;
                case 3:
                    do {
                        scanner = new Scanner(System.in);
                        System.out.printf("\nConsumerLastName : ");
                        cNum = scanner.nextLine().toUpperCase();
                        if (validateString(cNum)) {
                            break;
                        } else {
                            System.out.println("Only alphabets accepted");
                        }
                    } while (true);
                    consumerListDisplay(searchByLastName(cNum));
                    break;
                case 4:
                    do {
                        scanner = new Scanner(System.in);
                        System.out.printf("\nAddress : ");
                        cNum = scanner.nextLine().toUpperCase();
                        if (validateString(cNum)) {
                            break;
                        } else {
                            System.out.println("Only alphabets accepted");
                        }
                    } while (true);
                    consumerListDisplay(searchByAddress(cNum));
                    break;
            }
            usrChoice = searchChoice();
        }
    }

    public static int editChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Delete a record"
                + "\n2. Change first name"
                + "\n3. Change last name"
                + "\n4. Change city"
                + "\n0. Exit");
        System.out.print("Choice(0-4) : ");

        return scanner.nextInt();
    }

    public static boolean checkByID(String strStr, int[] index) {
        boolean find = false;

        for (int i = 0; i < cpyConsumer.length; i++) {
            if (cpyConsumer[i].getConsumerID() == Long.parseLong(strStr)) {
                find = true;
                index[0] = i;
                break;
            }
        }
        return find;
    }

    public static Consumer[] consumerSearch(String strStr, int col) {
        int[] index = new int[1];
        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        switch (col) {
            case 1:

                if (checkByID(strStr, index)) {
                    for (int i = index[0]; i < cpyConsumer.length - 1; i++) {
                        cpyConsumer[i] = cpyConsumer[i + 1];
                    }
                    System.out.println("\nOne Record Deleted");
                    cpyConsumer = Arrays.copyOf(cpyConsumer, cpyConsumer.length - 1);
                    //tempConsumer = new Consumer[cpyConsumer.length];
                    tempConsumer = Arrays.copyOf(cpyConsumer, cpyConsumer.length);
                } else {
                    tempConsumer = null;
                }
                break;
            case 2:
        }
        return tempConsumer;
    }

    public static void deleteRecord() {
        Scanner scanner = new Scanner(System.in);
        String cNum;

        System.out.print("Enter 11-dig consumer number : ");
        do {
            scanner = new Scanner(System.in);
            cNum = scanner.nextLine();
            if (!validateConsumerID(cNum)) {
                System.out.print("Consumer ID must be 11 digits and numeric type : ");
            } else {
                break;
            }
        } while (true);

        consumerListDisplay(consumerSearch(cNum, 1));
    }

    public static void changeField(int usrChoice) {

        Scanner scanner = new Scanner(System.in);
        String cNum;

        System.out.print("Enter 11-dig consumer number : ");
        do {
            scanner = new Scanner(System.in);
            cNum = scanner.nextLine();
            if (!validateConsumerID(cNum)) {
                System.out.print("Consumer ID must be 11 digits and numeric type : ");
            } else {
                break;
            }
        } while (true);

        int[] index = new int[1];
        String tempString;
        if (checkByID(cNum, index)) {
            switch (usrChoice) {
                case 2:
                    System.out.println("Record No. : " + (index[0] + 1) + "  \"" + cpyConsumer[index[0]].getConsumerFirst() + "\" is being replaced");
                    do {
                        scanner = new Scanner(System.in);
                        System.out.printf("\nConsumerFirstName[%d] : ", index[0] + 1);
                        tempString = scanner.nextLine();
                        if (validateString(tempString)) {
                            break;
                        } else {
                            System.out.println("Only alphabets accepted");
                        }
                    } while (true);
                    cpyConsumer[index[0]].setConsumerFirst(tempString.toUpperCase());

                    break;
                case 3:
                    System.out.println("Record No. : " + (index[0] + 1) + " \"" + cpyConsumer[index[0]].getConsumerLast() + "\" is being replaced");
                    do {
                        scanner = new Scanner(System.in);
                        System.out.printf("\nConsumerLastName[%d] : ", index[0] + 1);
                        tempString = scanner.nextLine();
                        if (validateString(tempString)) {
                            break;
                        } else {
                            System.out.println("Only alphabets accepted");
                        }
                    } while (true);
                    cpyConsumer[index[0]].setConsumerLast(tempString.toUpperCase());

                    break;
                case 4:
                    System.out.println("Record No. : " + (index[0] + 1) + " \"" + cpyConsumer[index[0]].getConsumerAddress() + "\" is being replaced");
                    do {
                        scanner = new Scanner(System.in);
                        System.out.printf("\nAddress : [%d] : ", index[0] + 1);
                        tempString = scanner.nextLine();
                        if (validateString(tempString)) {
                            break;
                        } else {
                            System.out.println("Only alphabets accepted");
                        }
                    } while (true);
                    cpyConsumer[index[0]].setConsumerAddress(tempString.toUpperCase());
                    break;
            }
        } else {
            System.out.println("No records found");
        }
    }

    public static void consumerEdit() {
        int usrChoice = editChoice();
        while (usrChoice != 0) {
            switch (usrChoice) {
                case 1:
                    deleteRecord();
                    break;
                case 2:
                case 4:
                case 3:
                    changeField(usrChoice);
                    break;
            }
            usrChoice = editChoice();
        }
    }

    public static int operationChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("1.  Add new consumer record "
                + "\n2.  Edit existing consumer record "
                + "\n3.  Display consumers whose duedate has exceeded"
                + "\n4.  List details in descending order of due date excedded"
                + "\n5.  to display consumers whose due date has NOT yet exceeded"
                + "\n6.  List details in descending order of due date NOT yet exceeded "
                + "\n7.  Display consumers whose payment exceeds entered amount"
                + "\n8.  Display in descending order based on exceeding entered amount"
                + "\n9.  Display consumers whose payment is less than or equal to entered amount"
                + "\n10. Display in descending order based on less than or equal to entered amount"
                + "\n11. Display record of consumer based on your choice of filter "
                + "\n12. Display master database"
                + "\n0.  Exit");
                System.out.print("Choice(0-12) : ");

        return scanner.nextInt();
    }

    public static void printBorder(char pChr, int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(pChr);
        }
    }

    public static void consumerListDisplay(Consumer... consumerList) {
        System.out.println();
        if (consumerList != null) {
            printBorder('+', 115);
            System.out.printf("\n%s", "+");
            System.out.printf("%7s", "Sr.No.");
            System.out.printf("%2s", "|");
            System.out.printf("%12s", "Consumer_ID");
            System.out.printf("%4s", "|");
            System.out.printf("%13s", "First Name");
            System.out.printf("%6s", "|");
            System.out.printf("%12s", "Last Name");
            System.out.printf("%4s", "|");
            System.out.printf("%7s", "City");
            System.out.printf("%10s", "|");
            System.out.printf("%17s", "Bill Amount");
            System.out.printf("%4s", "|");
            System.out.printf("%11s", "Due Date");
            System.out.printf("%5s", "+");

            for (int i = 0; i < consumerList.length; i++) {
                System.out.println();
                printBorder('+', 1);
                printBorder('-', 113);
                printBorder('+', 1);
                System.out.printf("\n%s", "+");
                System.out.printf("%4d", i + 1);
                System.out.printf("%5s", "|");
                System.out.print(consumerList[i]);
                System.out.printf("%2s", " ");
                printBorder('+', 1);
            }
            System.out.println();
            printBorder('+', 115);
            System.out.println();
        } else {
            System.out.println("No records to display");
        }
    }

    public static Consumer[] sortDescendingYYYY(Consumer... C) {
        int max = C[0].getDueDateYYYY();

        for (int i = 1; i < C.length; i++) {
            for (int j = 1; j < C.length; j++) {
                if (C[j].getDueDateYYYY() > max) {
                    Consumer temp = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = temp;
                }
                max = C[j].getDueDateYYYY();
            }
            max = C[0].getDueDateYYYY();
        }
        return C;
    }

    public static Consumer[] sortDescendingMM(Consumer... C) {
        int sIndex = 0;
        int yY = C[sIndex].getDueDateYYYY();
        for (int i = sIndex + 1; i < C.length; i++) {
            if (yY != C[i].getDueDateYYYY()) {
                int max = C[sIndex].getDueDateMM();
                for (int k = sIndex + 1; k < i; k++) {
                    for (int j = sIndex + 1; j < i; j++) {
                        if (C[j].getDueDateMM() > max) {
                            Consumer temp = C[j - 1];
                            C[j - 1] = C[j];
                            C[j] = temp;
                        }
                        max = C[j].getDueDateMM();
                    }
                    max = C[sIndex].getDueDateMM();
                }
                sIndex = i;
                yY = C[sIndex].getDueDateYYYY();
            }
        }
        int max = C[sIndex].getDueDateMM();
        for (int k = sIndex + 1; k < C.length; k++) {
            for (int j = sIndex + 1; j < C.length; j++) {
                if (C[j].getDueDateMM() > max) {
                    Consumer temp = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = temp;
                }
                max = C[j].getDueDateMM();
            }
            max = C[sIndex].getDueDateMM();
        }
        return C;
    }

    public static Consumer[] sortDescendingDD(Consumer... C) {
        int sIndex = 0;
        int mM = C[sIndex].getDueDateMM();
        for (int i = sIndex + 1; i < C.length; i++) {
            if (mM != C[i].getDueDateMM()) {
                int max = C[sIndex].getDueDateDD();
                for (int k = sIndex + 1; k < i; k++) {
                    for (int j = sIndex + 1; j < i; j++) {
                        if (C[j].getDueDateDD() > max) {
                            Consumer temp = C[j - 1];
                            C[j - 1] = C[j];
                            C[j] = temp;
                        }
                        max = C[j].getDueDateDD();
                    }
                    max = C[sIndex].getDueDateDD();
                }
                sIndex = i;
                mM = C[sIndex].getDueDateMM();
            }
        }
        int max = C[sIndex].getDueDateDD();
        for (int k = sIndex + 1; k < C.length; k++) {
            for (int j = sIndex + 1; j < C.length; j++) {
                if (C[j].getDueDateDD() > max) {
                    Consumer temp = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = temp;
                }
                max = C[j].getDueDateDD();
            }
            max = C[sIndex].getDueDateDD();
        }
        return C;
    }

    public static Consumer[] sortAscendingYYYY(Consumer... C) {
        int min = C[0].getDueDateYYYY();

        for (int i = 1; i < C.length; i++) {
            for (int j = 1; j < C.length; j++) {
                if (C[j].getDueDateYYYY() < min) {
                    Consumer temp = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = temp;
                }
                min = C[j].getDueDateYYYY();
            }
            min = C[0].getDueDateYYYY();
        }
        return C;
    }

    public static Consumer[] sortAscendingMM(Consumer... C) {
        int sIndex = 0;
        int yY = C[sIndex].getDueDateYYYY();
        for (int i = sIndex + 1; i < C.length; i++) {
            if (yY != C[i].getDueDateYYYY()) {
                int min = C[sIndex].getDueDateMM();
                for (int k = sIndex + 1; k < i; k++) {
                    for (int j = sIndex + 1; j < i; j++) {
                        if (C[j].getDueDateMM() < min) {
                            Consumer temp = C[j - 1];
                            C[j - 1] = C[j];
                            C[j] = temp;
                        }
                        min = C[j].getDueDateMM();
                    }
                    min = C[sIndex].getDueDateMM();
                }
                sIndex = i;
                yY = C[sIndex].getDueDateYYYY();
            }
        }
        int min = C[sIndex].getDueDateMM();
        for (int k = sIndex + 1; k < C.length; k++) {
            for (int j = sIndex + 1; j < C.length; j++) {
                if (C[j].getDueDateMM() < min) {
                    Consumer temp = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = temp;
                }
                min = C[j].getDueDateMM();
            }
            min = C[sIndex].getDueDateMM();
        }
        return C;
    }

    public static Consumer[] sortAscendingDD(Consumer... C) {
        int sIndex = 0;
        int mM = C[sIndex].getDueDateMM();
        for (int i = sIndex + 1; i < C.length; i++) {
            if (mM != C[i].getDueDateMM()) {
                int min = C[sIndex].getDueDateDD();
                for (int k = sIndex + 1; k < i; k++) {
                    for (int j = sIndex + 1; j < i; j++) {
                        if (C[j].getDueDateDD() < min) {
                            Consumer temp = C[j - 1];
                            C[j - 1] = C[j];
                            C[j] = temp;
                        }
                        min = C[j].getDueDateDD();
                    }
                    min = C[sIndex].getDueDateDD();
                }
                sIndex = i;
                mM = C[sIndex].getDueDateMM();
            }
        }
        int min = C[sIndex].getDueDateDD();
        for (int k = sIndex + 1; k < C.length; k++) {
            for (int j = sIndex + 1; j < C.length; j++) {
                if (C[j].getDueDateDD() < min) {
                    Consumer temp = C[j - 1];
                    C[j - 1] = C[j];
                    C[j] = temp;
                }
                min = C[j].getDueDateDD();
            }
            min = C[sIndex].getDueDateDD();
        }
        return C;
    }

    public static boolean validateConsumerID(String iD) {
        boolean isNumber = true;
        if (iD.length() == 11) {
            for (int k = 0; k < iD.length(); k++) {
                if (!Character.isDigit(iD.charAt(k))) {
                    isNumber = false;
                    break;
                }
            }
            if (isNumber) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean validateString(String cName) {

        boolean isName = true;

        for (int i = 0; i < cName.length(); i++) {
            if (!Character.isAlphabetic(cName.charAt(i))) {
                isName = false;
                break;
            }
        }
        if (!isName) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateBillAmount(String billAmount) {
        boolean moreDecimal = false;
        boolean isDig = true;
        int countDecimal = 0;
        for (int i = 0; i < billAmount.length(); i++) {
            if (billAmount.charAt(i) == '.') {
                countDecimal++;
                if (countDecimal > 1) {
                    moreDecimal = true;
                    break;
                }
            }
            if (!Character.isDigit(billAmount.charAt(i))) {
                if (billAmount.charAt(i) != '.') {
                    isDig = false;
                    break;
                }
            }
        }
        if (moreDecimal) {
            return false;
        } else if (!isDig) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean validateDate(String strDate, int... varDate) {
        boolean isDate = true;

        switch (strDate.length()) {
            case 10:
                if (strDate.charAt(4) == '/' && strDate.charAt(7) == '/') {
                    if (!(Character.isDigit(strDate.charAt(0)) && Character.isDigit(strDate.charAt(1))
                            && Character.isDigit(strDate.charAt(2)) && Character.isDigit(strDate.charAt(3))
                            && Character.isDigit(strDate.charAt(5)) && Character.isDigit(strDate.charAt(6))
                            && Character.isDigit(strDate.charAt(8)) && Character.isDigit(strDate.charAt(9)))) {
                        isDate = false;
                    }
                } else {
                    isDate = false;
                }
                break;
            case 9:
                if (strDate.charAt(4) == '/') {
                    if (strDate.charAt(7) == '/') {
                        if (!(Character.isDigit(strDate.charAt(0)) && Character.isDigit(strDate.charAt(1))
                                && Character.isDigit(strDate.charAt(2)) && Character.isDigit(strDate.charAt(3))
                                && Character.isDigit(strDate.charAt(5)) && Character.isDigit(strDate.charAt(6))
                                && Character.isDigit(strDate.charAt(8)))) {
                            isDate = false;
                        }
                    } else if (strDate.charAt(6) == '/') {
                        if (!(Character.isDigit(strDate.charAt(0)) && Character.isDigit(strDate.charAt(1))
                                && Character.isDigit(strDate.charAt(2)) && Character.isDigit(strDate.charAt(3))
                                && Character.isDigit(strDate.charAt(5)) && Character.isDigit(strDate.charAt(7))
                                && Character.isDigit(strDate.charAt(8)))) {
                            isDate = false;
                        }
                    }
                } else {
                    isDate = false;
                }
                break;
            case 8:
                if (strDate.charAt(4) == '/' && strDate.charAt(6) == '/') {
                    if (!(Character.isDigit(strDate.charAt(0)) && Character.isDigit(strDate.charAt(1))
                            && Character.isDigit(strDate.charAt(2)) && Character.isDigit(strDate.charAt(3))
                            && Character.isDigit(strDate.charAt(5)) && Character.isDigit(strDate.charAt(7)))) {
                        isDate = false;
                    }
                } else {
                    isDate = false;
                }
                break;
            default:
                isDate = false;
        }
        if (isDate) {
            varDate[0] = Integer.parseInt(strDate.substring(0, strDate.indexOf('/')));
            varDate[1] = Integer.parseInt(strDate.substring(strDate.indexOf('/') + 1, strDate.lastIndexOf('/')));
            varDate[2] = Integer.parseInt(strDate.substring(strDate.lastIndexOf('/') + 1));
        }
        return isDate;
    }

    public static boolean isDuplicate(String cNum){
        boolean duplicate = false;
        long conNum = Long.parseLong(cNum);
        for(int i = 0 ; i < cpyConsumer.length ; i++){
            if(conNum == cpyConsumer[i].getConsumerID()){
                duplicate = true;
            }
        }
        return duplicate;
    }
    
    public static void consumerAdd() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter # of consumers to Add : ");
        int conSize = scanner.nextInt();
        String tempString;
        Consumer[] tempConsumer = new Consumer[conSize];
        for (int i = 0; i < tempConsumer.length; i++) {
            tempConsumer[i] = new Consumer();
        }
        System.out.println("\nFill the records for " + conSize + " consumers.");
        for (int i = 0; i < tempConsumer.length; i++) {
            System.out.printf("\nEntry#[%d]", i + 1);
            do {
                scanner = new Scanner(System.in);
                System.out.printf("\nConsumerId[%d] : ", i + 1);
                tempString = scanner.nextLine();
                if (!validateConsumerID(tempString)) {
                    System.out.println("Consumer ID must be 11 digits and numeric type");
                } else {
                    if(isDuplicate(tempString))
                        System.out.println("Consumer ID should be unique");
                    else
                        break;
                }
            } while (true);
            tempConsumer[i].setConsumerID(Long.parseLong(tempString));
            do {
                scanner = new Scanner(System.in);
                System.out.printf("\nConsumerFirstName[%d] : ", i + 1);
                tempString = scanner.nextLine();
                if (validateString(tempString)) {
                    break;
                } else {
                    System.out.println("Only alphabets accepted");
                }
            } while (true);
            tempConsumer[i].setConsumerFirst(tempString.toUpperCase());
            do {
                scanner = new Scanner(System.in);
                System.out.printf("\nConsumerLastName[%d] : ", i + 1);
                tempString = scanner.nextLine();
                if (validateString(tempString)) {
                    break;
                } else {
                    System.out.println("Only alphabets accepted");
                }
            } while (true);
            tempConsumer[i].setConsumerLast(tempString.toUpperCase());
            do {
                scanner = new Scanner(System.in);
                System.out.printf("\nAddress : [%d] : ", i + 1);
                tempString = scanner.nextLine();
                if (validateString(tempString)) {
                    break;
                } else {
                    System.out.println("Only alphabets accepted");
                }
            } while (true);
            tempConsumer[i].setConsumerAddress(tempString.toUpperCase());
            do {
                scanner = new Scanner(System.in);
                System.out.printf("\nBillAmount[%d] : ", i + 1);
                tempString = scanner.nextLine();
                if (validateBillAmount(tempString)) {
                    break;
                } else {
                    System.out.println("Numeric digits allowed with optional[but only 1] decimal point");
                }
            } while (true);
            tempConsumer[i].setBillAmount(Double.parseDouble(tempString));
            int[] varDate = new int[3];
            do {
                scanner = new Scanner(System.in);
                System.out.printf("\nDueDate(YYYY/MM/DD)[%d] : ", i + 1);
                tempString = scanner.nextLine();
                if (validateDate(tempString, varDate)) {
                    break;
                } else {
                    System.out.println("Enter date in YYYY/MM/DD");
                }
            } while (true);
            tempConsumer[i].setDueDateYYYY(varDate[0]);
            tempConsumer[i].setDueDateMM(varDate[1]);
            tempConsumer[i].setDueDateDD(varDate[2]);
        }

        int count = cpyConsumer.length + tempConsumer.length;
        Consumer[] dupConsumer = new Consumer[cpyConsumer.length];
        for (int i = 0; i < cpyConsumer.length; i++) {
            dupConsumer[i] = cpyConsumer[i];
        }
        cpyConsumer = new Consumer[count];
        for (int i = 0; i < count - tempConsumer.length; i++) {
            cpyConsumer[i] = dupConsumer[i];
        }
        for (int i = count - tempConsumer.length; i < count; i++) {
            cpyConsumer[i] = tempConsumer[i - count + tempConsumer.length];
        }

        consumerListDisplay(cpyConsumer);
    }

    public static Consumer[] consumerBillAmountFilter(int sort) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter bill Amount to be compared : ");
        double dblAmount = scanner.nextDouble();
        System.out.println();
        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        int countEntry = 0;
        for (int i = 0; i < tempConsumer.length; i++) {
            if (cpyConsumer[i].billAmount > dblAmount && (sort == 7 || sort == 8)) {
                tempConsumer[countEntry++] = cpyConsumer[i];
            } else if (cpyConsumer[i].billAmount <= dblAmount && (sort == 9 || sort == 10)) {
                tempConsumer[countEntry++] = cpyConsumer[i];
            }
        }
        if (countEntry != 0) {
            if (sort == 8 || sort == 10) {
                //consumerListDisplay(sortDescendingDD(sortDescendingMM(sortDescendingYYYY(Arrays.copyOf(tempConsumer, countEntry)))));
                return sortDescendingDD(sortDescendingMM(sortDescendingYYYY(Arrays.copyOf(tempConsumer, countEntry))));
            } else {
                //consumerListDisplay(sortAscendingDD(sortAscendingMM(sortAscendingYYYY(Arrays.copyOf(tempConsumer, countEntry)))));
                return sortAscendingDD(sortAscendingMM(sortAscendingYYYY(Arrays.copyOf(tempConsumer, countEntry))));
            }
        } else {
            return null;
        }

    }

    public static Consumer[] consumerDateFilter(int sort) {
        int[] varDate = new int[3];
        Calendar calendar = Calendar.getInstance();
        varDate[2] = calendar.get(Calendar.DATE);
        varDate[1] = calendar.get(Calendar.MONTH);
        varDate[0] = calendar.get(Calendar.YEAR);

        Scanner objScanner = new Scanner(System.in);
        System.out.println("Press C for customised date filter, "
                + "\nany other key for system default");

        if (objScanner.next().equalsIgnoreCase("C")) {
            do{
            objScanner = new Scanner(System.in);
            System.out.println("Enter date(YYYY/MM/DD):");
            String strDate = objScanner.nextLine();
            if (validateDate(strDate, varDate)) {
                    break;
                } else {
                    System.out.println("Enter date in YYYY/MM/DD");
                }
            }while(true);
            varDate[1]--;
            /*year = Integer.parseInt(strDate.substring(0, strDate.indexOf('/')));//varDate[0]
            month = Integer.parseInt(strDate.substring(strDate.indexOf('/') + 1, strDate.lastIndexOf('/')));
            day = Integer.parseInt(strDate.substring(strDate.lastIndexOf('/') + 1));//varDate[2]
            month -= 1;*/
        }
        Date sysDate = new Date(varDate[0], varDate[1], varDate[2]);

        Consumer[] tempConsumer = new Consumer[cpyConsumer.length];
        for (int i = 0; i < tempConsumer.length; i++) {
            tempConsumer[i] = new Consumer();
        }
        int entryCount = 0;
        for (int i = 0; i < cpyConsumer.length; i++) {
            Date date = new Date(cpyConsumer[i].getDueDateYYYY(), cpyConsumer[i].getDueDateMM() - 1, cpyConsumer[i].getDueDateDD());
            if ((sort == 3 || sort == 4) && date.before(sysDate)) {
                tempConsumer[entryCount++] = cpyConsumer[i];
            } else if ((sort == 5 || sort == 6) && (date.after(sysDate) || date.compareTo(sysDate) == 0)) {
                tempConsumer[entryCount++] = cpyConsumer[i];
            }
        }
        if (entryCount != 0) {
            if (sort == 4 || sort == 6) {
                //consumerListDisplay(sortDescendingDD(sortDescendingMM(sortDescendingYYYY(Arrays.copyOf(tempConsumer, entryCount)))));
                return sortDescendingDD(sortDescendingMM(sortDescendingYYYY(Arrays.copyOf(tempConsumer, entryCount))));
            } else {
                //consumerListDisplay(sortAscendingDD(sortAscendingMM(sortAscendingYYYY(Arrays.copyOf(tempConsumer, entryCount)))));
                return sortAscendingDD(sortAscendingMM(sortAscendingYYYY(Arrays.copyOf(tempConsumer, entryCount))));
            }
        } else {
            return null;
        }
    }
}
