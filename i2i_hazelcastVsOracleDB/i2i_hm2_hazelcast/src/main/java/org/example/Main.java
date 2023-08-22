package org.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<Integer, Integer> map = hz.getMap("numbers");
        Random rand = new Random();
        int numCountToInsert = 100000;
        int[] randomNumbers = new int[numCountToInsert];

        // Generate random numbers outside the loop
        for (int i = 0; i < numCountToInsert; i++) {
            randomNumbers[i] = rand.nextInt();
        }

        // Insert 20,000 random numbers and measure the time
        long startInsertion = System.currentTimeMillis();
        for (int i = 0; i < numCountToInsert; i++) {
            map.put(i, randomNumbers[i]);
        }
        long endInsertion = System.currentTimeMillis();

        System.out.println("Insertion of "+numCountToInsert+ " numbers took: " + (endInsertion - startInsertion) + " milliseconds");

        // Retrieve 20,000 random numbers and measure the time
        long startRetrieval = System.currentTimeMillis();
        for (int i = 0; i < numCountToInsert; i++) {
            map.get(rand.nextInt(numCountToInsert));
        }
        long endRetrieval = System.currentTimeMillis();

        System.out.println("Retrieval of " + numCountToInsert + " numbers took: " + (endRetrieval - startRetrieval) + " milliseconds");

        // Follow the same pattern for 100,000 numbers
        // ...
    }
}
