package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchFilesByParametr {

    private static List<Path> list = new ArrayList<>();

    public static List<Path> getList() {
        return list;
    }

    public static void setList(List<Path> list) {
        SearchFilesByParametr.list = list;
    }

    public static void main(String[] args) throws IOException {
      if (!validKey(args)) {
          throw new IllegalArgumentException("Please check and  enter valid arguments");
      }
      SearchFiles searchFiles = typeOfFind(args);
      if (searchFiles == null) {
         throw new IllegalArgumentException("Please check the arguments");
      }
      Files.walkFileTree(Paths.get(getArgument(args, 0)), searchFiles);
      list = searchFiles.getList();
      RecAndcreateZip recAndcreateZip = new RecAndcreateZip();
      //Записываем данные в файл
      recAndcreateZip.recordInTheFIle(list);
      File destination = new File(getArgument(args, 3));
      // создаем зип архив
      recAndcreateZip.createZipFile(destination, list);
    }

    private static SearchFiles typeOfFind(String[] array) {
        if (getArgument(array, 2).equals("name")) {
            SearchFiles searcher = new SearchFiles(x -> x.endsWith(getArgument(array, 1)));
            return searcher;
        }
        if (getArgument(array, 2).equals("mask")) {
            Pattern pattern = Pattern.compile(getArgument(array, 1));
            //Matcher matcher = pattern.matcher();
            SearchFiles searcher = new SearchFiles(x -> pattern.matcher(x.toString()).find());
            return searcher;
        }
        return null;
    }

    private static boolean validKey(final String[] array) {
        boolean result = false;
        if (array.length != 4) {
            return result;
        } else if (array[0] != null || array[1] != null || array[2] != null || array[3] != null) {
            result = true;
        }
        return result;
    }

    private static String getArgument(String[] array, int value) {
        return array[value];
    }
}
