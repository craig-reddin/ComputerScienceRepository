/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructuresandalgorithmsca2;

import java.util.ArrayList;

/**
 *
 * @author craig
 *
 */
public class MyArrayList<ElementType> extends ArrayList<ElementType> {
    // this is a relatively efficient Bubble Sort algorithm (as Bubble Sorts go).
    //It recognises that after each pass of the outer loop an additional right-most element
    //is in the correct position and can be ignored

    public int bubbleSort() {
        // integer to keep count of the comparisons were completed.
        int comparisonCounter = 0;
        //Comparable objects, used to compare the value of one index to next Index value (0 to 1)((1 to 2) of the arrayList
        Comparable elementAtKIndex;
        Comparable elementAtKIndexPlusOne;
        int i, k;
        //for loop to iterate list size
        for (i = 0; i < size(); i++) {
            //this loop iterates 1 time less on each increment of the outer loop.
            //because the lowest value will be sent to the back, it is know that this value is the lowest so the last index will not need to be checked.
            
            for (k = 0; k <size() - 1 - i; k++) {
                //gets index value
                elementAtKIndex = (Comparable) get(k);
                //gets next index value
                elementAtKIndexPlusOne = (Comparable) get(k + 1);
                //compare two comparables and if the index value of k is less than the value of k+1, then swap them
                if (elementAtKIndex.compareTo(elementAtKIndexPlusOne) < 0) {
                    //The element on postion k is swapped with element on position k + 1;
                    //call swap method, passing indexs to be swapped
                    swap(k, k + 1);
                }
                // increment the comarisons done.
                comparisonCounter++;
            }
        }
        return comparisonCounter;
    }

    //this mehtod takes two integer values and swaps the elements within its of the given integer values(array list Indexes) 
    public void swap(int firstPosition, int secondPosition) {
        //Two ElementType Objects are used to hold the index values
        ElementType firstIndex = get(firstPosition);
        ElementType secondIndex = get(secondPosition);
        //Remove element from position 1
        remove(firstPosition);
        //Insert secondIndex into position 1
        add(firstPosition, secondIndex);
        //Remove element from position 2
         remove(secondPosition);
        // Insert firstIndex into position 2
        add(secondPosition, firstIndex);
    }

}
