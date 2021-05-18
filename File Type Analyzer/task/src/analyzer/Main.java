package analyzer;

import analyzer.algorithm.search.KMPAlgorithm;
import analyzer.algorithm.search.SearchAlgorithm;
import analyzer.model.Signature;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    private static final int COUNT_THREAD = 10;
    private static final String SPLITTER = ";";

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println();
            return;
        }

        File dir = new File(args[0]);
        File[] files = dir.listFiles();
        File fileSignatures = new File(args[1]);
        ExecutorService executor = Executors.newFixedThreadPool(COUNT_THREAD);
        SearchAlgorithm searchAlgorithm = new KMPAlgorithm();
        //SortAlgorithm<Signature> sortAlgorithm = new MergeSort<>();
        List<Callable<String>> tasks = new ArrayList<>();
        List<Signature> signatures;

        try {
            signatures = getSignatures(fileSignatures);
        } catch (FileNotFoundException ex) {
            System.out.println();
            return;
        }

        signatures = reverse(signatures);
        //sortAlgorithm.sort(signatures);

        if ((files == null) || (files.length == 0)) {
            System.out.println();
            return;
        }

        for (File file : files) {
            try (BufferedInputStream in =
                         new BufferedInputStream(new FileInputStream(file))) {

                String text = getText(in);
                SearchTask task =
                        new SearchTask(searchAlgorithm, text, signatures);

                tasks.add(task);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        List<Future<String>> results;

        try {
            results = executor.invokeAll(tasks);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        executor.shutdown();

        for (int i = 0; i < files.length; i++) {
            try {
                String title = results.get(i).get();
                String fileName = files[i].getName();

                System.out.println(fileName + ": " + title);

            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static String getText(BufferedInputStream in) throws IOException {
        int next = in.read();
        StringBuilder input = new StringBuilder();

        while (next != -1) {

            input.append((char) next);

            next = in.read();
        }

        return input.toString();
    }

    public static List<Signature> getSignatures(File input) throws FileNotFoundException {
        Scanner in = new Scanner(input);
        List<Signature> signatures = new ArrayList<>();

        while (in.hasNextLine()) {
            signatures.add(getSignature(in.nextLine()));
        }

        in.close();
        return signatures;
    }

    public static Signature getSignature(String input) {
        String[] param = input.split(SPLITTER);

        if (param.length != 3) {
            throw new IllegalArgumentException("");
        }

        int priority = Integer.parseInt(param[0]);
        String signature = param[1].substring(1, param[1].length() - 1);
        String title = param[2].substring(1, param[2].length() - 1);

        return new Signature(priority, title, signature);
    }

    public static List<Signature> reverse(List<Signature> input) {
        List<Signature> output = new ArrayList<>();

        for (int i = input.size() - 1; i > -1; i--) {
            output.add(input.get(i));
        }

        return output;
    }

}
