/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datastructuresandalgorithmsca2;
/**
 *
 * @author craig
 */
public class main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 4 lists to be sorted - swaps calculated and time take for analysis later
        MyArrayList<Integer> list = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>();
        MyArrayList<Integer> list3 = new MyArrayList<>();
        MyArrayList<Integer> list4 = new MyArrayList<>();

        // String list to show data displayed and sorted in descemding order.
        MyArrayList<String> namesListString = new MyArrayList<>();

        //The ten elements. (required according to description)
        namesListString.add("Amy");
        namesListString.add("Stacy");
        namesListString.add("Grace");
        namesListString.add("Tony");
        namesListString.add("Richard");
        namesListString.add("Phil");
        namesListString.add("Steve");
        namesListString.add("Larry");
        namesListString.add("Sam");
        namesListString.add("Homer");
        // With the 4 araylists to graph complexity., 4 new arraylists were created so the number of 
        //insersts could be automated easily into arraylist allowing random numbers to be added usig a for loop
        //on first 500 iterations numebers are added to all lists
        //the next 500 teration add numbers to list 2,3,4
        //the next 1000 teration add numbers to list 3,4
         //the next 2000 teration add numbers to list 4
        
        for (int i = 0; i < 4000; i++) {
            // generate a random number to populate in the array lists to sort later.
            int number = (int) (Math.random() * 1000000);
            if (i < 500) {
                list.add(number);
                list2.add(number);
                list3.add(number);
                list4.add(number);
            }
            if (i > 499 && i < 1000) {
                list2.add(number);
                list3.add(number);
                list4.add(number);
            }
            if (i > 999 && i < 2000) {
                list3.add(number);
                list4.add(number);
            }
            if (i > 1999 && i < 4000) {
                list4.add(number);
            }
        }
       
        //loop to print out string unsorted array list
        System.out.println("The elements of the list are as follows\n ");
        for (int i = 0; i < namesListString.size(); i++) {
            System.out.println(namesListString.get(i));
        }
        // make long variable to got time in milliseconds
        long startTime = System.currentTimeMillis();
        // call the bubble sort method.
       //The emthod was changed to return the number of comparisons were done when this method was executed and passed an arralist of unsorted elements 
        int numberOfComparisons = namesListString.bubbleSort();
        //make long variable to got time in milliseconds
        long finishTime = System.currentTimeMillis();
        //start the finish time is subtracted from the start time and resuts with the total execution time of the sort.

     //for loop to print out list sorted in descending order
        System.out.println("\nThe list of Elements After The List Was Sorted in Descending Order:\n ");
        for (int i = 0; i < namesListString.size(); i++) {
            System.out.println(namesListString.get(i));
        }
        // System prints to show the stats of the method excecution
        System.out.println("\nThe sort took " + (finishTime - startTime) + " millisecond/s to complete");
        System.out.println("\n" + numberOfComparisons + " comarisons were completed to sort the Name List\n");
        System.out.println("\nQuestion 6 3 diffrerent arraylist sizes - 500,1000,2000");

        
        // The rest of the code repeats the same process to sort all other unsorted arraylist
        startTime = System.currentTimeMillis();
        numberOfComparisons = list.bubbleSort();
        finishTime = System.currentTimeMillis();
        System.out.println("\nThe sort took " + (finishTime - startTime) + " millisecond/s to complete");
        System.out.println("\n" + numberOfComparisons + " comarisons were completed to sort the Name List for 500 elements");

        startTime = System.currentTimeMillis();
        numberOfComparisons = list2.bubbleSort();
        finishTime = System.currentTimeMillis();
        System.out.println("\nThe sort took " + (finishTime - startTime) + " millisecond/s to complete");
        System.out.println("\n" + numberOfComparisons + " comarisons were completed to sort the Name List for 1000 elements");

        startTime = System.currentTimeMillis();
        numberOfComparisons = list3.bubbleSort();
        finishTime = System.currentTimeMillis();
        System.out.println("\nThe sort took " + (finishTime - startTime) + " millisecond/s to complete");
        System.out.println("\n" + numberOfComparisons + " comarisons were completed to sort the Name List for 2000 elements");

        startTime = System.currentTimeMillis();
        numberOfComparisons = list4.bubbleSort();
        finishTime = System.currentTimeMillis();
        System.out.println("\nThe sort took " + (finishTime - startTime) + " millisecond/s to complete");
        System.out.println("\n" + numberOfComparisons + " comarisons were completed to sort the Name List for 4000 elements");

    }

}
